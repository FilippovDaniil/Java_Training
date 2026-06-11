package m05_abstract_factory_builder.practice.task07;

import java.util.ArrayList;
import java.util.List;

// Месячная выписка, собирается пошагово через Builder.
class AccountStatement {
    // TODO: неизменяемые поля clientName, accountType, список строк операций

    private AccountStatement(Builder b) {
        // TODO: скопировать поля из билдера (список — в неизменяемую копию)
    }

    // TODO: print()/describe(): шапка "Выписка: <client> / <type>",
    //       строки операций и итог (сумма amountCents)

    static class Builder {
        private String clientName;
        private String accountType;
        private final List<String> lines = new ArrayList<>();
        // TODO: при желании храните суммы отдельно для подсчёта итога

        public Builder clientName(String clientName) {
            // TODO
            return this;
        }

        public Builder accountType(String accountType) {
            // TODO
            return this;
        }

        public Builder addLine(String description, long amountCents) {
            // TODO: добавить строку операции (описание + сумма), вернуть this
            return this;
        }

        public AccountStatement build() {
            // TODO: проверить, что clientName и accountType заданы (иначе IllegalStateException),
            //       вернуть new AccountStatement(this)
            return new AccountStatement(this);
        }
    }
}
