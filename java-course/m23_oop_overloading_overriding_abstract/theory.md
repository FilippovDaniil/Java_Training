# Модуль 23. ООП: перегрузка, переопределение, абстрактные классы

Завершаем ООП: разбираем перегрузку, переопределение методов и абстракцию.

## Перегрузка методов (overloading)

**Перегрузка** — несколько методов с **одним именем**, но **разными параметрами** (число, типы, порядок). Компилятор выбирает нужный по аргументам.

```java
class Printer {
    void print(int x)      { System.out.println("Целое: " + x); }
    void print(double x)   { System.out.println("Дробное: " + x); }
    void print(String x)   { System.out.println("Строка: " + x); }
    void print(int a, int b) { System.out.println("Два числа: " + a + ", " + b); }
}
```

```java
Printer p = new Printer();
p.print(5);        // Целое: 5
p.print(3.14);     // Дробное: 3.14
p.print("hi");     // Строка: hi
p.print(1, 2);     // Два числа: 1, 2
```

> Перегрузка различается **только по параметрам**. Разный тип возврата при одинаковых параметрах — НЕ перегрузка (ошибка компиляции).

---

## Переопределение методов (overriding)

**Переопределение** — потомок задаёт **новую реализацию** метода родителя с той же сигнатурой. Это основа полиморфизма.

```java
class Animal {
    void sound() { System.out.println("Животное издаёт звук"); }
}

class Dog extends Animal {
    @Override                          // аннотация: проверяет корректность
    void sound() { System.out.println("Гав!"); }
}
```

```java
Animal a = new Dog();
a.sound();   // Гав! — вызывается версия Dog (динамическая диспетчеризация)
```

### Ключевое слово `super`

Вызвать версию родителя из переопределённого метода:

```java
class Cat extends Animal {
    @Override
    void sound() {
        super.sound();                 // вызов метода родителя
        System.out.println("Мяу!");
    }
}
```

`super(...)` в конструкторе вызывает конструктор родителя:

```java
class Dog extends Animal {
    String breed;
    Dog(String name, String breed) {
        super(name);                   // конструктор Animal (первой строкой!)
        this.breed = breed;
    }
}
```

### Перегрузка vs переопределение

| | Перегрузка (overloading) | Переопределение (overriding) |
|--|--------------------------|------------------------------|
| Где | в одном классе | в потомке |
| Сигнатура | разные параметры | **та же** сигнатура |
| Выбор метода | при компиляции | во время выполнения |
| `@Override` | нет | да |

---

## Абстрактные классы

**Абстрактный класс** — это незавершённый класс-заготовка, который **нельзя инстанцировать**. Он может содержать абстрактные методы (без тела), которые потомки **обязаны** реализовать.

```java
abstract class Figure {
    String name;

    Figure(String name) { this.name = name; }     // обычный конструктор

    abstract double area();          // абстрактный метод — без тела
    abstract double perimeter();

    void describe() {                // обычный метод с реализацией
        System.out.printf("%s: площадь=%.2f, периметр=%.2f%n",
                          name, area(), perimeter());
    }
}
```

```java
class Circle extends Figure {
    double radius;
    Circle(double radius) {
        super("Круг");
        this.radius = radius;
    }
    double area()      { return Math.PI * radius * radius; }
    double perimeter() { return 2 * Math.PI * radius; }
}

class Rectangle extends Figure {
    double w, h;
    Rectangle(double w, double h) {
        super("Прямоугольник");
        this.w = w; this.h = h;
    }
    double area()      { return w * h; }
    double perimeter() { return 2 * (w + h); }
}
```

```java
// Figure f = new Figure(...);   // ❌ нельзя — класс абстрактный
Figure[] figures = { new Circle(5), new Rectangle(3, 4) };
for (Figure f : figures) {
    f.describe();   // полиморфизм: вызовутся area()/perimeter() конкретного класса
}
```

```
            Figure (abstract)
          area(), perimeter() — abstract
           /                \
      Circle              Rectangle
   (реализуют abstract-методы)
```

---

## Абстрактный класс vs интерфейс

| | Абстрактный класс | Интерфейс |
|--|-------------------|-----------|
| Поля-состояние | да | только константы |
| Конструктор | да | нет |
| Множественность | один (`extends`) | много (`implements`) |
| Когда | общий базовый класс с состоянием | контракт поведения |

> Абстрактный класс отвечает «**кто я**» (общая суть), интерфейс — «**что умею**».

---

## `final` — запрет изменений

```java
final class Constants { }          // нельзя наследовать
class A {
    final void method() { }        // нельзя переопределить
    final int X = 10;              // нельзя переприсвоить (константа)
}
```

---

## Анонимные классы

Класс без имени, объявленный «на месте» — обычно для разовой реализации интерфейса/абстрактного класса:

```java
Animal cat = new Animal() {         // анонимная реализация прямо здесь
    void sound() { System.out.println("Мяу из анонимного класса"); }
};
cat.sound();
```

> Анонимные классы часто заменяют лямбдами (см. [модуль 24](../m24_stream_api_lambda/theory.md)), если интерфейс функциональный.

---

## Подводные камни

| Ошибка | Объяснение |
|--------|-----------|
| `new` абстрактного класса | нельзя; создавайте потомков |
| забыли реализовать abstract-метод | класс-потомок сам станет абстрактным |
| «переопределение» с другой сигнатурой | это перегрузка; используйте `@Override` для проверки |
| `super()` не первой строкой конструктора | ошибка компиляции |
| переопределение `final`-метода | запрещено |

---

## Что дальше

В [модуле 24](../m24_stream_api_lambda/theory.md) — лямбды и Stream API.
