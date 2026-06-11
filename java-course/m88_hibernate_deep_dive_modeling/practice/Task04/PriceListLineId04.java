package m88_hibernate_deep_dive_modeling.practice.task04;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

// TODO: @Embeddable
class PriceListLineId04 implements Serializable {
    private Long priceListId;
    private Long productId;
    protected PriceListLineId04() {}
    public PriceListLineId04(Long priceListId, Long productId) {
        this.priceListId = priceListId; this.productId = productId;
    }
    // TODO: equals/hashCode ПО ОБОИМ полям:
    // TODO: @Override public boolean equals(Object o) {
    // TODO:     return o instanceof PriceListLineId04 k
    // TODO:         && Objects.equals(priceListId, k.priceListId)
    // TODO:         && Objects.equals(productId, k.productId);
    // TODO: }
    // TODO: @Override public int hashCode() { return Objects.hash(priceListId, productId); }
}
