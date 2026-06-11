package m03_solid_lsp_isp_dip.practice.task07;

// Абстракция хранилища, заданная бизнес-слоем (DIP). Реализации подстраиваются.
interface AccountRepository {
    void save(Account account);
    Account findById(String id);
}
