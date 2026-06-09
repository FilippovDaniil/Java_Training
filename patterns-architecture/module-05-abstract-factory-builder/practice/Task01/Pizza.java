class Pizza {
    // TODO: неизменяемые поля size, cheese, pepperoni

    private Pizza(Builder b) {
        // TODO: скопировать поля из билдера
    }

    // TODO: describe()/toString() для вывода "Pizza{size=.., cheese=.., pepperoni=..}"

    static class Builder {
        // TODO: те же поля, что у Pizza (изменяемые)

        public Builder size(int size) {
            // TODO: задать и вернуть this
            return this;
        }

        public Builder cheese(boolean cheese) {
            // TODO: задать и вернуть this
            return this;
        }

        public Builder pepperoni(boolean pepperoni) {
            // TODO: задать и вернуть this
            return this;
        }

        public Pizza build() {
            // TODO: вернуть new Pizza(this)
            return new Pizza(this);
        }
    }
}
