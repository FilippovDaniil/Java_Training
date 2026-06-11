package m03_solid_lsp_isp_dip.practice.task02;

// Роль «умеет летать» — отдельная от Bird. Нелетающие птицы её просто не реализуют.
interface Flyer {
    void fly();
}
