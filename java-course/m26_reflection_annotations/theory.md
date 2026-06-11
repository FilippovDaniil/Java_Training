# Модуль 26. Reflection API и аннотации

**Reflection** (рефлексия) — способность программы исследовать и изменять саму себя во время выполнения: узнавать классы, поля, методы объекта и вызывать их динамически. **Аннотации** — метаданные, которыми помечают код.

## Объект `Class`

У каждого класса есть объект `Class`, описывающий его структуру. Получить можно тремя способами:

```java
Class<?> c1 = String.class;            // через литерал класса
Class<?> c2 = "текст".getClass();      // через объект
Class<?> c3 = Class.forName("java.lang.String");  // по имени (бросает исключение)
```

```java
c1.getName();         // "java.lang.String" — полное имя
c1.getSimpleName();   // "String" — короткое имя
c1.getSuperclass();   // класс-родитель
c1.getPackageName();  // пакет
```

---

## Исследование полей и методов

```java
import java.lang.reflect.Field;
import java.lang.reflect.Method;

Class<?> c = Person.class;

// все объявленные поля
for (Field f : c.getDeclaredFields()) {
    System.out.println(f.getType().getSimpleName() + " " + f.getName());
}

// все объявленные методы
for (Method m : c.getDeclaredMethods()) {
    System.out.println(m.getName());
}
```

---

## Динамическое создание и вызов

```java
import java.lang.reflect.Constructor;

Class<?> c = Person.class;

// создать объект (вызвать конструктор)
Constructor<?> ctor = c.getConstructor(String.class, int.class);
Object obj = ctor.newInstance("Иван", 30);

// вызвать метод по имени
Method method = c.getMethod("getName");
Object result = method.invoke(obj);     // как obj.getName()
System.out.println(result);             // Иван

// доступ к private-полю
Field field = c.getDeclaredField("age");
field.setAccessible(true);              // обойти private
field.set(obj, 99);
```

> Reflection мощна, но: медленнее обычных вызовов, обходит инкапсуляцию и теряет проверку типов на компиляции. Применяйте, когда без неё нельзя (фреймворки, библиотеки).

---

## Где используется рефлексия

| Технология | Как использует |
|------------|----------------|
| JUnit | находит методы с `@Test` и вызывает их |
| Spring | внедряет зависимости в поля с `@Autowired` |
| Jackson/Gson | читает поля объекта для JSON |
| IDE | автодополнение, анализ кода |

---

## Аннотации

**Аннотация** — метка над классом, методом, полем, не влияющая напрямую на логику, но несущая информацию для компилятора или фреймворков.

### Встроенные аннотации

```java
@Override            // метод переопределяет родительский
public String toString() { ... }

@Deprecated          // элемент устарел, не используйте
public void oldMethod() { ... }

@SuppressWarnings("unchecked")   // подавить предупреждения компилятора
```

---

## Собственные аннотации

```java
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)   // доступна во время выполнения
@Target(ElementType.METHOD)           // применима к методам
@interface Test {
    String name() default "";          // параметр аннотации
}
```

| Мета-аннотация | Назначение |
|----------------|-----------|
| `@Retention` | как долго хранится (`SOURCE`/`CLASS`/`RUNTIME`) |
| `@Target` | к чему применима (`TYPE`/`METHOD`/`FIELD`...) |
| `@Documented` | включать в JavaDoc |
| `@Inherited` | наследуется потомками |

> Чтобы читать аннотацию через рефлексию, нужен `@Retention(RUNTIME)`.

### Применение

```java
class MyTests {
    @Test(name = "Проверка сложения")
    public void testAdd() { ... }
}
```

---

## Чтение аннотаций через Reflection

Связка рефлексии и аннотаций — основа фреймворков вроде JUnit:

```java
for (Method m : MyTests.class.getDeclaredMethods()) {
    if (m.isAnnotationPresent(Test.class)) {   // есть ли аннотация @Test
        Test annotation = m.getAnnotation(Test.class);
        System.out.println("Запуск теста: " + annotation.name());
        m.invoke(new MyTests());               // вызвать тестовый метод
    }
}
```

```
сканируем методы класса ─▶ находим @Test ─▶ вызываем через invoke()
                  (именно так работает JUnit, см. модуль 28)
```

---

## Подводные камни

| Ошибка | Объяснение |
|--------|-----------|
| `@Retention(SOURCE)` + чтение в runtime | аннотация недоступна; нужен `RUNTIME` |
| рефлексия в горячем коде | медленнее прямых вызовов |
| `setAccessible(true)` повсеместно | ломает инкапсуляцию; только при необходимости |
| `ClassNotFoundException` в `forName` | проверяйте полное имя класса |
| необработанные исключения рефлексии | методы бросают checked-исключения |

---

## Что дальше

В [модуле 27](../m27_maven/theory.md) — система сборки Maven.
