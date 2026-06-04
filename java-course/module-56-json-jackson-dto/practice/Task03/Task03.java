/**
 * Задача 03 — Модуль 56: DTO-аннотации (@JsonProperty, @JsonIgnore, @JsonInclude)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   com.fasterxml.jackson.core:jackson-databind:2.17.x (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   Класс UserDto (ниже) содержит поля с TODO-аннотациями.
 *   1) Добавьте @JsonProperty("user_name") к полю userName — в JSON должно быть "user_name".
 *   2) Добавьте @JsonIgnore к полю password — пароль не должен попасть в JSON.
 *   3) Добавьте @JsonInclude(JsonInclude.Include.NON_NULL) на класс — null-поля не выводить.
 *   4) Сериализуйте объект UserDto и убедитесь:
 *      - ключ называется "user_name", не "userName";
 *      - поля "password" и "email" (null) отсутствуют в JSON.
 *   5) Десериализуйте JSON с ключом "user_name" обратно в UserDto.
 *
 * ПОДСКАЗКА:
 *   import com.fasterxml.jackson.annotation.JsonProperty;
 *   import com.fasterxml.jackson.annotation.JsonIgnore;
 *   import com.fasterxml.jackson.annotation.JsonInclude;
 *
 *   @JsonProperty("user_name") ставится над полем ИЛИ над геттером.
 *   @JsonIgnore применяется к полю ИЛИ геттеру.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

public class Task03 {

    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        // Создайте пользователя (email оставьте null)
        UserDto user = new UserDto();
        user.setUserName("alice");
        user.setPassword("secret123");
        user.setEmail(null);

        // 1. Сериализуйте и выведите JSON
        // TODO: String json = ...
        //       System.out.println(json);
        // Ожидается: {"user_name":"alice"}
        // (password — скрыт, email — null → не выводить)

        // 2. Десериализуйте JSON обратно в UserDto
        String incoming = "{\"user_name\":\"bob\"}";
        // TODO: UserDto parsed = ...
        //       System.out.println(parsed.getUserName());  // bob
        //       System.out.println(parsed.getPassword());  // null (не было в JSON)
    }
}
