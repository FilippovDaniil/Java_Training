package m89_hibernate_deep_dive_inheritance.practice.task07;

import jakarta.persistence.*;
import java.util.List;

// TODO: @Entity @DiscriminatorValue("ONLINE")
class OnlinePayment07 extends Payment07 {
    private String provider;
    protected OnlinePayment07() {}
    public OnlinePayment07(long amount, String provider) { super(amount); this.provider = provider; }
    // TODO: @Override public String describe() { return "Онлайн: " + provider; }
}
