// Абстракция хранилища, заданная бизнес-слоем (DIP). Реализации подстраиваются.
interface AccountRepository {
    void save(Account account);
    Account findById(String id);
}
