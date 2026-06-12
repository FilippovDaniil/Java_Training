# Модуль 25. Многопоточность

**Поток (Thread)** — независимая последовательность выполнения внутри программы. Многопоточность позволяет выполнять задачи **параллельно**: пока один поток ждёт, другой работает.

```
Однопоточно:   [——задача A——][——задача B——]
Многопоточно:  [——задача A——]
               [——задача B——]   (одновременно)
```

## Два способа создать поток

### 1. Класс `Runnable` (предпочтительно)

```java
Runnable task = () -> {
    for (int i = 0; i < 3; i++) {
        System.out.println("Поток: " + i);
    }
};
Thread thread = new Thread(task);
thread.start();      // ЗАПУСК нового потока (НЕ run()!)
```

> ⚠️ Вызывайте `start()`, а не `run()`. `run()` выполнит код в **текущем** потоке без создания нового.

### 2. Наследование от `Thread`

```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Работаю в потоке " + getName());
    }
}
new MyThread().start();
```

> `Runnable` гибче: класс может наследоваться от другого класса, а Thread «занимает» наследование. Предпочитайте `Runnable`/лямбды.

---

## Управление потоком

```java
thread.start();           // запустить
thread.join();            // дождаться завершения потока
Thread.sleep(1000);       // усыпить ТЕКУЩИЙ поток на 1000 мс (бросает InterruptedException)
thread.getName();         // имя
thread.isAlive();         // жив ли поток
Thread.currentThread();   // текущий поток
```

```java
Thread t = new Thread(task);
t.start();
t.join();                 // главный поток ждёт, пока t закончит
System.out.println("t завершён");
```

---

## Проблема: состояние гонки (race condition)

Когда несколько потоков меняют **общие** данные одновременно — результат непредсказуем.

```java
class Counter {
    int count = 0;
    void increment() { count++; }   // НЕ атомарно: чтение → +1 → запись
}
// 2 потока по 10000 инкрементов → итог часто < 20000 (потеря обновлений)
```

```
поток A: читает count=5 --+
поток B: читает count=5 --+  оба пишут 6 — одно обновление потеряно!
```

---

## Решение: `synchronized`

Ключевое слово `synchronized` гарантирует, что блок/метод выполняет **только один поток** одновременно.

```java
class Counter {
    private int count = 0;

    synchronized void increment() {   // синхронизированный метод
        count++;
    }

    int get() { return count; }
}
```

Или синхронизированный блок:

```java
synchronized (lock) {
    // критическая секция — один поток за раз
}
```

> `synchronized` решает гонку, но снижает параллелизм (потоки ждут друг друга). Синхронизируйте только то, что действительно общее.

---

## `volatile` — видимость изменений

Каждый поток может кешировать переменную. `volatile` гарантирует, что изменение **сразу видно** всем потокам. Полезно для флагов:

```java
class Worker implements Runnable {
    private volatile boolean running = true;   // флаг остановки

    public void run() {
        while (running) { /* работа */ }
    }

    public void stop() { running = false; }    // другой поток видит сразу
}
```

> `volatile` обеспечивает **видимость**, но НЕ атомарность. Для `count++` он не поможет — нужен `synchronized` или `AtomicInteger`.

---

## `ExecutorService` — пул потоков

Создавать потоки вручную дорого. **Пул потоков** переиспользует фиксированное число потоков для множества задач.

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

ExecutorService pool = Executors.newFixedThreadPool(3);   // 3 потока

for (int i = 0; i < 10; i++) {
    int taskId = i;
    pool.submit(() -> System.out.println("Задача " + taskId));
}

pool.shutdown();   // не принимать новые задачи, завершить текущие
```

---

## `Callable` и `Future` — задача с результатом

`Runnable` ничего не возвращает. `Callable<T>` возвращает значение, а `Future<T>` — «обещание» получить его позже.

```java
import java.util.concurrent.*;

ExecutorService pool = Executors.newFixedThreadPool(2);

Callable<Integer> task = () -> {
    int sum = 0;
    for (int i = 1; i <= 100; i++) sum += i;
    return sum;                       // возвращает результат
};

Future<Integer> future = pool.submit(task);
System.out.println("Результат: " + future.get());   // 5050 (блокирует до готовности)

pool.shutdown();
```

```
submit(Callable) --▶ Future --future.get()--▶ результат (когда готов)
```

---

## Сводка инструментов

| Инструмент | Назначение |
|------------|-----------|
| `Thread` / `Runnable` | создание потока |
| `join()` | дождаться завершения |
| `synchronized` | защита общих данных от гонки |
| `volatile` | мгновенная видимость флага |
| `ExecutorService` | пул потоков |
| `Callable` / `Future` | задача с результатом |
| `AtomicInteger` | атомарные счётчики без блокировок |

---

## Подводные камни

| Ошибка | Объяснение |
|--------|-----------|
| вызов `run()` вместо `start()` | код выполнится в текущем потоке, без параллелизма |
| гонка при общем счётчике | используйте `synchronized` / `AtomicInteger` |
| `volatile` для `count++` | не помогает: операция не атомарна |
| забыли `pool.shutdown()` | программа не завершится (потоки живут) |
| `future.get()` без таймаута | может заблокировать навсегда |
| необработанный `InterruptedException` | `sleep`/`join` его бросают |

---

## Что дальше

В [модуле 26](../m26_reflection_annotations/theory.md) — Reflection API и аннотации.
