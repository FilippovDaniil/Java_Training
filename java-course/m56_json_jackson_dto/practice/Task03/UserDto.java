package m56_json_jackson_dto.practice.task03;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

// Заготовка DTO — добавьте аннотации вместо TODO-комментариев
// TODO: добавьте @JsonInclude(JsonInclude.Include.NON_NULL) на класс
class UserDto {

    // TODO: добавьте @JsonProperty("user_name")
    private String userName;

    // TODO: добавьте @JsonIgnore
    private String password;

    private String email;

    // Геттеры и сеттеры (обязательны для Jackson без аннотации @JsonCreator)
    public String getUserName()  { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getPassword()  { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail()     { return email; }
    public void setEmail(String email) { this.email = email; }
}
