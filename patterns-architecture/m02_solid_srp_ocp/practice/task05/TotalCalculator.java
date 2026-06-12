package m02_solid_srp_ocp.practice.task05;

// ТОЛЬКО считает (SRP). Зависит от абстракции TaxRule (OCP/DIP).
class TotalCalculator {

    // TODO: поле TaxRule taxRule + конструктор TotalCalculator(TaxRule taxRule)

    public long totalWithTax(long baseCents) {
        // TODO: base + taxRule.taxCents(base)
        return baseCents;
    }
}
