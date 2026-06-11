package m88_hibernate_deep_dive_modeling.practice.task04;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity @Table(name = "price_list_lines")
class PriceListLine04 {
    // TODO: @EmbeddedId
    private PriceListLineId04 id;
    private int price;
    protected PriceListLine04() {}
    public PriceListLine04(PriceListLineId04 id, int price) { this.id = id; this.price = price; }
    public int getPrice() { return price; }
}
