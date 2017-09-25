package hello;

import javax.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String country;
    @Column
    private String city;
    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;

    protected Address() {
    }

    public Address(String country, String city) {
        this.country = country;
        this.city = city;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
