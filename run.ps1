#requires -Version 5.1
<#
.SYNOPSIS
    Run a single Task file from the training courses and show its output.

.DESCRIPTION
    Compiles and runs one .java file via the JDK single-file source launcher
    (no project build needed). Sibling files in a task folder are picked up
    automatically. The Spring/JUnit classpath from tools/deps/build/cp.txt is
    attached when present, so example mains that import Spring also compile.

    Prefers JDK 21 (matches the IDE; IntelliJ 2025.2 mis-resolves JDK 25),
    falls back to 25. File is ASCII-only to avoid the PowerShell 5.1
    "Cyrillic without BOM" trap; Russian text printed BY the task still shows
    correctly (JVM stdout.encoding=UTF-8 below).

.EXAMPLE
    .\run.ps1 java-course\m01_intro_first_program\practice\Task01.java
    .\run.ps1 m01_intro Task01
    .\run.ps1 Task05
#>
param([Parameter(ValueFromRemainingArguments = $true)][string[]]$Query)

$ErrorActionPreference = 'Stop'
try { [Console]::OutputEncoding = [Text.Encoding]::UTF8 } catch {}

$Root = $PSScriptRoot

function Find-Jdk {
    $candidates = @(
        'C:\Users\MaxxPC\.jdks\ms-21.0.10',
        'C:\Program Files\Java\jdk-21.0.10',
        'C:\Users\MaxxPC\.jdks\openjdk-25',
        $env:JAVA_HOME
    )
    foreach ($c in $candidates) {
        if ($c -and (Test-Path (Join-Path $c 'bin\javac.exe'))) {
            return (Join-Path $c 'bin\java.exe')
        }
    }
    Write-Host "No JDK with a compiler found. Add its path to Find-Jdk in run.ps1." -ForegroundColor Red
    exit 3
}

function Resolve-Target {
    param([string[]]$q)
    if (-not $q -or $q.Count -eq 0) {
        Write-Host "Usage: .\run.ps1 <file.java>   |   .\run.ps1 m01_intro Task01" -ForegroundColor Yellow
        exit 2
    }
    $joined = ($q -join ' ').Trim('"', ' ')

    if ((Test-Path -LiteralPath $joined -PathType Leaf) -and $joined.EndsWith('.java')) {
        return (Resolve-Path -LiteralPath $joined).Path
    }
    if (Test-Path -LiteralPath $joined -PathType Container) {
        $f = Get-ChildItem -LiteralPath $joined -Filter *.java -File | Select-Object -First 1
        if ($f) { return $f.FullName }
    }

    $courses = @('java-course', 'patterns-architecture', 'algorithms-course') |
        ForEach-Object { Join-Path $Root $_ } | Where-Object { Test-Path $_ }
    $all = Get-ChildItem -Path $courses -Recurse -Filter *.java -File -ErrorAction SilentlyContinue
    $terms = $q | Where-Object { $_ }
    $hits = $all | Where-Object {
        $p = $_.FullName; $ok = $true
        foreach ($t in $terms) { if ($p -notlike "*$t*") { $ok = $false; break } }
        $ok
    }
    if (-not $hits) {
        Write-Host "No .java file matches: $($terms -join ' ')" -ForegroundColor Yellow
        exit 2
    }
    if ($hits.Count -eq 1) { return $hits[0].FullName }

    $last = $terms[-1]
    $exact = $hits | Where-Object { $_.BaseName -eq $last }
    if ($exact.Count -eq 1) { return $exact[0].FullName }

    Write-Host "Multiple matches ($($hits.Count)) - add the course/module to disambiguate:" -ForegroundColor Yellow
    $hits | Select-Object -First 15 | ForEach-Object {
        Write-Host ("  " + $_.FullName.Substring($Root.Length + 1))
    }
    exit 2
}

function Test-HasMain([string]$file) {
    return [bool](Select-String -LiteralPath $file -Pattern 'void\s+main\s*\(' -Quiet)
}

$Java   = Find-Jdk
$target = Resolve-Target $Query
$dir    = Split-Path $target -Parent
$runFile = $target

if (-not (Test-HasMain $target)) {
    $siblings = Get-ChildItem -LiteralPath $dir -Filter *.java -File |
        Where-Object { Test-HasMain $_.FullName }
    if ($siblings) {
        $folder = Split-Path $dir -Leaf
        $pick = $siblings | Where-Object { $_.BaseName -eq $folder } | Select-Object -First 1
        if (-not $pick) { $pick = $siblings | Select-Object -First 1 }
        $runFile = $pick.FullName
    }
    else { $runFile = $null }
}

if ($null -eq $runFile) {
    if (Select-String -LiteralPath $target -Pattern '@Test' -Quiet) {
        Write-Host "This is a JUnit test class (no main method)." -ForegroundColor Yellow
        Write-Host "Test classes are run with Gradle, not the single-file launcher:"
        Write-Host "    .\gradlew test"
        exit 2
    }
    Write-Host "No main() method found in:`n  $target" -ForegroundColor Yellow
    Write-Host "Nothing to run. Write your solution in main() first."
    exit 2
}

$jvmArgs = @('-Dstdout.encoding=UTF-8', '-Dstderr.encoding=UTF-8', '-Dfile.encoding=UTF-8')
$cpFile  = Join-Path $Root 'tools\deps\build\cp.txt'
if (Test-Path $cpFile) {
    $cp = (Get-Content $cpFile -Raw).Trim()
    if ($cp) { $jvmArgs += @('-cp', $cp) }
}

$rel = $runFile
if ($runFile.StartsWith($Root)) { $rel = $runFile.Substring($Root.Length + 1) }
Write-Host ("> Running: " + $rel) -ForegroundColor Cyan
Write-Host ('-' * 64)

& $Java @jvmArgs $runFile
$code = $LASTEXITCODE

Write-Host ('-' * 64)
if ($code -eq 0) { Write-Host "Done (exit 0)" -ForegroundColor Green }
else { Write-Host ("Exited with code $code") -ForegroundColor Red }
exit $code
