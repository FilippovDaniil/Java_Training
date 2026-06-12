# Модуль 17. Исключения

**Исключение** (exception) — это сигнал об ошибке во время выполнения программы. Вместо аварийного завершения мы можем **перехватить** ошибку и корректно её обработать.

## `try-catch`

```java
try {
    int result = 10 / 0;            // здесь возникнет ошибка
    System.out.println(result);     // эта строка не выполнится
} catch (ArithmeticException e) {
    System.out.println("Деление на ноль: " + e.getMessage());
}
System.out.println("Программа продолжает работу");
```

```
try { рискованный код }
   |
   +- ошибки нет -▶ блок catch пропускается
   |
   +- ошибка ----▶ выполнение прыгает в catch
```

> Без `try-catch` исключение «всплывает» вверх и завершает программу с трассировкой стека.

---

## Иерархия исключений

```
                Throwable
               /         \
          Error          Exception
       (фатальные,        /        \
        не ловим)   RuntimeException  (checked: IOException,
                    (unchecked:        SQLException...)
                     NPE, Arithmetic,
                     ArrayIndexOOB...)
```

| Группа | Примеры | Нужно ли обрабатывать |
|--------|---------|----------------------|
| `Error` | `OutOfMemoryError`, `StackOverflowError` | нет (фатальные) |
| `RuntimeException` (**unchecked**) | `NullPointerException`, `ArithmeticException`, `ArrayIndexOutOfBoundsException` | по желанию |
| Проверяемые (**checked**) | `IOException`, `FileNotFoundException` | **обязательно** (компилятор требует) |

---

## Несколько `catch` и multi-catch

```java
try {
    int[] arr = new int[3];
    arr[5] = Integer.parseInt("abc");
} catch (NumberFormatException e) {
    System.out.println("Неверный формат числа");
} catch (ArrayIndexOutOfBoundsException e) {
    System.out.println("Выход за границу массива");
}
```

Если обработка одинакова — multi-catch через `|`:

```java
catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
    System.out.println("Ошибка ввода: " + e.getMessage());
}
```

> Более конкретные исключения ставьте **выше** более общих, иначе общий перехватит первым.

---

## `finally` — выполняется всегда

Блок `finally` выполняется в любом случае: была ошибка или нет. Используется для освобождения ресурсов.

```java
try {
    System.out.println("Работаем");
    return;                      // даже при return!
} catch (Exception e) {
    System.out.println("Ошибка");
} finally {
    System.out.println("Закрываем ресурсы");   // выполнится всегда
}
```

---

## `throw` — выбросить исключение вручную

```java
public static void setAge(int age) {
    if (age < 0) {
        throw new IllegalArgumentException("Возраст не может быть отрицательным: " + age);
    }
}
```

> `throw` (выбросить, единственное число) ≠ `throws` (объявить, что метод может бросить).

---

## `throws` — объявление проверяемых исключений

Если метод может бросить **проверяемое** исключение и не обрабатывает его сам, он обязан объявить это через `throws`:

```java
public static void readFile(String path) throws IOException {
    // ... код, который может бросить IOException
}
```

Вызывающий обязан либо обработать (`try-catch`), либо тоже пробросить (`throws`).

---

## try-with-resources — автозакрытие

Ресурсы (файлы, потоки), реализующие `AutoCloseable`, закрываются автоматически:

```java
try (Scanner sc = new Scanner(System.in)) {
    int x = sc.nextInt();
    // sc.close() вызовется автоматически в конце блока
} catch (Exception e) {
    System.out.println("Ошибка ввода");
}
```

> Предпочтительнее, чем `finally { resource.close(); }`. Подробнее — в [модуле 18](../m18_io_streams/theory.md).

---

## Собственные исключения

```java
class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String message) {
        super(message);            // передаём сообщение родителю
    }
}

// использование:
throw new InsufficientFundsException("Недостаточно средств на счёте");
```

> Наследуйте от `RuntimeException` (unchecked) или `Exception` (checked) в зависимости от того, должен ли вызывающий обязательно обрабатывать ошибку.

---

## Полезные методы исключения

```java
catch (Exception e) {
    e.getMessage();        // текст сообщения
    e.printStackTrace();   // полная трассировка стека (для отладки)
}
```

---

## Подводные камни

| Ошибка | Объяснение |
|--------|-----------|
| пустой `catch {}` | ошибка «проглатывается» — теряется информация |
| `catch (Exception e)` для всего | ловит слишком много; ловите конкретное |
| общий catch выше конкретного | конкретный никогда не сработает (ошибка компиляции) |
| исключения для управления логикой | исключения — для ошибок, не для обычных ветвлений |
| забыли `throws` для checked | ошибка компиляции |

---

## Что дальше

В [модуле 18](../m18_io_streams/theory.md) — потоки ввода-вывода (где исключения обязательны).
