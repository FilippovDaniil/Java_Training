class RegistrationService {
    // TODO: поле EmailValidator validator (создать или получить в конструкторе)
    // TODO: приватное хранилище зарегистрированных e-mail (например, Set<String>)

    /**
     * Регистрирует пользователя, если e-mail корректен.
     * @return приветственное сообщение или null/пустую строку при отказе
     */
    public String register(String email) {
        // TODO: спросить validator.isValid(email);
        //       если ok — сохранить и вернуть приветствие; иначе — отказ
        return "";
    }

    public int registeredCount() {
        // TODO: сколько e-mail зарегистрировано
        return 0;
    }
}
