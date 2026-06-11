package m03_solid_lsp_isp_dip.practice.task05;

// Модуль верхнего уровня. Зависит ТОЛЬКО от абстракции OrderRepository.
class RevenueReportService {

    // TODO: поле OrderRepository repository + конструктор
    //       RevenueReportService(OrderRepository repository)

    public long totalRevenue() {
        // TODO: просуммировать repository.findAllAmountsCents()
        return 0;
    }
}
