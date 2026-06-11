package m02_solid_srp_ocp.practice.task07;

import java.util.List;

// ТОЛЬКО считает итог (SRP). Зависит от абстракций ShippingMethod и
// DiscountPolicy (OCP/DIP) — новые способы доставки/скидки не требуют его правок.
class OrderPricingService {

    public long grandTotal(List<OrderLine> lines, ShippingMethod shipping, DiscountPolicy discount) {
        // TODO: 1) сумма priceCents по lines;
        //       2) общий вес weightGrams по lines;
        //       3) итог = discount.apply(сумма) + shipping.costCents(вес)
        return 0;
    }
}
