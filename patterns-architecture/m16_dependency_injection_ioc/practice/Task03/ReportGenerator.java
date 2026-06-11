package m16_dependency_injection_ioc.practice.task03;

class ReportGenerator {
    // TODO: поле final DataSource source (обязательная зависимость)
    // TODO: поле Logger logger (опциональная, по умолчанию null)

    // TODO: конструктор ReportGenerator(DataSource source)

    public void setLogger(Logger logger) {
        // TODO: задать опциональный логгер
    }

    public String generate() {
        // TODO: result = "Отчёт: " + source.data();
        //       если logger != null → logger.log("сгенерирован отчёт");
        //       вернуть result
        return "";
    }
}
