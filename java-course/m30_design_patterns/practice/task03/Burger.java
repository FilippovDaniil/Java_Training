package m30_design_patterns.practice.task03;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class Burger {
    // TODO: финальные поля, приватный конструктор(Builder),
    //       вложенный static class Builder с fluent-методами и build(), toString
    private final String bulochka;
    private final String sir;
    private final String kotleta;
    private final String bekon;
    private final String sous;
}
