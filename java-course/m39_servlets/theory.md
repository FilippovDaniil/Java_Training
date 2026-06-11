# Модуль 39. Сервлеты. Java Servlet API

> ⚠️ **Только теория.** Практика будет добавлена позже (требует сервлет-контейнера).

**Сервлет** — Java-класс, обрабатывающий HTTP-запросы на стороне сервера. Это фундамент серверной Java-разработки: на сервлетах построены JSP, Spring MVC и большинство Java-веб-фреймворков.

## Где живёт сервлет

```
Браузер ──HTTP запрос──▶ Сервлет-контейнер (Tomcat) ──▶ Сервлет
                                                          │ обрабатывает
Браузер ◀──HTTP ответ─── Сервлет-контейнер ◀──────────────┘
```

Сервлет не запускается сам — им управляет **сервлет-контейнер** (см. [модуль 40](../module-40-servlet-containers/theory.md)).

---

## Простейший сервлет

С Jakarta EE пакет называется `jakarta.servlet` (раньше — `javax.servlet`).

```java
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello")               // URL, по которому доступен сервлет
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<h1>Привет из сервлета!</h1>");
    }
}
```

При запросе `GET /hello` контейнер вызовет `doGet`.

---

## Методы по HTTP-методам

| HTTP-метод | Метод сервлета |
|------------|----------------|
| GET | `doGet()` |
| POST | `doPost()` |
| PUT | `doPut()` |
| DELETE | `doDelete()` |

```java
protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    String name = req.getParameter("name");   // параметр формы/запроса
    // ... обработка
}
```

---

## Request и Response

### `HttpServletRequest` — данные запроса

```java
req.getParameter("id");        // параметр ?id=5 или из формы
req.getParameterValues("tag"); // несколько значений
req.getHeader("User-Agent");   // заголовок
req.getMethod();               // GET/POST
req.getRequestURI();           // путь
req.getSession();              // сессия пользователя
```

### `HttpServletResponse` — формирование ответа

```java
resp.setStatus(200);
resp.setContentType("application/json; charset=UTF-8");
resp.getWriter().write("{...}");
resp.sendRedirect("/login");   // перенаправление
```

---

## Жизненный цикл сервлета

Контейнер управляет сервлетом по этапам:

```
   init()      ──▶  service()  ──▶  destroy()
 (один раз     (на каждый      (один раз при
  при загрузке) запрос →        выгрузке)
                doGet/doPost)
```

| Метод | Когда | Сколько раз |
|-------|-------|-------------|
| `init()` | при первой загрузке | один раз |
| `service()` → `doGet/doPost` | на каждый запрос | много |
| `destroy()` | при остановке | один раз |

> ⚠️ Один экземпляр сервлета обслуживает **много** запросов в разных потоках. Поэтому поля сервлета — общие; не храните в них данные конкретного запроса (проблема потокобезопасности, см. [модуль 25](../module-25-multithreading-concurrency/theory.md)).

---

## Маппинг URL

### Через аннотацию (современно)

```java
@WebServlet("/users/*")
```

### Через `web.xml` (классически)

```xml
<web-app>
    <servlet>
        <servlet-name>hello</servlet-name>
        <servlet-class>com.example.HelloServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>hello</servlet-name>
        <url-pattern>/hello</url-pattern>
    </servlet-mapping>
</web-app>
```

`web.xml` — дескриптор развёртывания, лежит в `WEB-INF/`.

---

## Сессии и cookies

HTTP не хранит состояние между запросами. Чтобы «узнавать» пользователя, используют **сессии**:

```java
HttpSession session = req.getSession();
session.setAttribute("user", "Иван");          // сохранить
String user = (String) session.getAttribute("user");  // прочитать
session.invalidate();                           // завершить сессию (logout)
```

Контейнер связывает сессию с клиентом через cookie `JSESSIONID`.

---

## Фильтры (Filter)

Перехватывают запросы до/после сервлета (аутентификация, логирование, кодировки):

```java
@WebFilter("/*")
public class EncodingFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) {
        req.setCharacterEncoding("UTF-8");
        chain.doFilter(req, resp);   // передать дальше по цепочке
    }
}
```

---

## Глоссарий

| Термин | Значение |
|--------|----------|
| Сервлет | серверный компонент обработки HTTP |
| Контейнер | среда выполнения сервлетов (Tomcat) |
| web.xml | дескриптор развёртывания |
| Сессия | состояние пользователя между запросами |
| Фильтр | перехватчик запросов/ответов |
| WAR | формат упаковки веб-приложения |

## Дополнительные источники

- Jakarta Servlet Specification.
- Документация Apache Tomcat.
- «Head First Servlets and JSP».

## Что дальше

В [модуле 40](../module-40-servlet-containers/theory.md) — контейнеры сервлетов и развёртывание.
