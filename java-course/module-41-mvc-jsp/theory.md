# Модуль 41. MVC и JSP

> ⚠️ **Только теория.** Практика будет добавлена позже.

**JSP** (JavaServer Pages) — технология для генерации HTML-страниц с вставками Java-логики. **MVC** — архитектурный паттерн, разделяющий приложение на модель, представление и контроллер (см. также [модуль 37](../module-37-software-architecture/theory.md)).

## Паттерн MVC в вебе

```
   Браузер
      │ запрос
      ▼
 ┌─────────────┐   1. обрабатывает запрос
 │ CONTROLLER  │   (Сервлет)
 │ (Servlet)   │
 └──────┬──────┘
        │ 2. вызывает логику, готовит данные
        ▼
 ┌─────────────┐   3. бизнес-логика и данные
 │   MODEL     │   (POJO, сервисы, БД)
 └──────┬──────┘
        │ 4. данные кладутся в request
        ▼
 ┌─────────────┐   5. формирует HTML
 │    VIEW      │   (JSP)
 │   (JSP)      │
 └──────┬──────┘
        │ HTML
        ▼
   Браузер
```

| Компонент | В вебе | Ответственность |
|-----------|--------|-----------------|
| Model | POJO, сервисы | данные и бизнес-логика |
| View | JSP | отображение HTML |
| Controller | Servlet | приём запроса, координация |

> Идея: **сервлет не генерирует HTML**, а готовит данные и перенаправляет на JSP. JSP не содержит бизнес-логики — только отображение. Это разделение ответственности.

---

## Поток «Servlet → JSP» (Model 2)

```java
@WebServlet("/products")
public class ProductController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 1. получить данные (Model)
        List<Product> products = productService.findAll();
        // 2. положить в request
        req.setAttribute("products", products);
        // 3. перенаправить на JSP (View)
        req.getRequestDispatcher("/WEB-INF/views/products.jsp").forward(req, resp);
    }
}
```

---

## Синтаксис JSP

### Директивы и выражения (классический стиль)

```jsp
<%@ page contentType="text/html; charset=UTF-8" %>

<%-- скриптлет: Java-код (устаревший подход) --%>
<% int x = 5; %>

<%-- выражение: вывод значения --%>
<%= x %>

<%-- объявление --%>
<%! private int counter = 0; %>
```

> ⚠️ Скриптлеты `<% ... %>` считаются **устаревшими** — они смешивают логику и разметку. Современный подход — **EL + JSTL** (ниже).

### EL (Expression Language) — современный вывод данных

```jsp
${product.name}          <%-- вызывает product.getName() --%>
${user.age > 18}         <%-- выражения --%>
${param.id}              <%-- параметр запроса --%>
```

### JSTL — теги для логики (без Java в разметке)

```jsp
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:if test="${not empty products}">
    <ul>
        <c:forEach var="p" items="${products}">
            <li>${p.name} — ${p.price} руб.</li>
        </c:forEach>
    </ul>
</c:if>
```

| Тег JSTL | Назначение |
|----------|-----------|
| `<c:forEach>` | цикл |
| `<c:if>` | условие |
| `<c:choose>/<c:when>` | switch |
| `<c:out>` | безопасный вывод |
| `<c:set>` | присвоение |

---

## Как работает JSP «под капотом»

```
products.jsp ──компилируется──▶ Servlet (._jsp.java) ──▶ .class ──▶ HTML
            (контейнером, один раз)
```

> JSP — это, по сути, **сервлет наизнанку**: HTML с вкраплениями кода, который контейнер превращает в сервлет.

---

## Scope (области видимости) атрибутов

```jsp
${pageScope.x}        <%-- в пределах страницы --%>
${requestScope.x}     <%-- в пределах запроса --%>
${sessionScope.x}     <%-- в пределах сессии пользователя --%>
${applicationScope.x} <%-- для всего приложения --%>
```

| Scope | Живёт |
|-------|-------|
| page | одна страница |
| request | один запрос |
| session | сессия пользователя |
| application | всё приложение |

---

## Современная альтернатива

Сегодня вместо JSP чаще используют:
- **Шаблонизаторы**: Thymeleaf, FreeMarker (популярны в Spring).
- **REST + SPA**: бэкенд отдаёт JSON, фронтенд (React/Vue) рисует UI.

> JSP всё ещё встречается в legacy-проектах, и понимать его полезно, но новые проекты редко начинают на JSP.

---

## Глоссарий

| Термин | Значение |
|--------|----------|
| JSP | страница с генерацией HTML и Java |
| Скриптлет | вставка Java-кода `<% %>` (устарело) |
| EL | язык выражений `${...}` |
| JSTL | библиотека тегов для логики |
| forward | передача запроса от сервлета к JSP |
| Model 2 | MVC-вариант: Servlet (C) + JSP (V) + POJO (M) |

## Дополнительные источники

- Jakarta Server Pages Specification.
- «Head First Servlets and JSP».

## Что дальше

В [модуле 42](../module-42-web-services/theory.md) — веб-сервисы.
