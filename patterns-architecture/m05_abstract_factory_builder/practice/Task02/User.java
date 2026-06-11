package m05_abstract_factory_builder.practice.task02;

class User {
    // TODO: неизменяемые поля name, email, age, city

    private User(Builder b) {
        // TODO: скопировать поля из билдера
    }

    // TODO: describe()/toString() для вывода

    static class Builder {
        // TODO: поля name, email (обязательные), age=0, city="" (опциональные)

        public Builder name(String name) {
            // TODO
            return this;
        }

        public Builder email(String email) {
            // TODO
            return this;
        }

        public Builder age(int age) {
            // TODO
            return this;
        }

        public Builder city(String city) {
            // TODO
            return this;
        }

        public User build() {
            // TODO: проверить, что name и email непустые (иначе IllegalStateException),
            //       затем вернуть new User(this)
            return new User(this);
        }
    }
}
