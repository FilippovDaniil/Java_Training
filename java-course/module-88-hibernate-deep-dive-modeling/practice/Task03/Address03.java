import jakarta.persistence.*;

@Embeddable
class Address03 {
    private String city;
    private String street;
    protected Address03() {}
    public Address03(String city, String street) { this.city = city; this.street = street; }
    public String getCity() { return city; }
    public String getStreet() { return street; }
}
