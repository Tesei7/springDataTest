package hello;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Address> addresses = new ArrayList<>();

    protected Customer() {
    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Customer addAddress(Address address) {
        addresses.add(address);
        address.setCustomer(this);
        return this;
    }

    public Customer removeAddress(Address address) {
        addresses.remove(address);
        address.setCustomer(null);
        return this;
    }

    @Override
    public String toString() {
        String delimiter = "                                                                                                       ";
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s';%n" + delimiter + "%s]",
                id, firstName, lastName, addresses.stream().map(Object::toString).collect(joining("\n" + delimiter)));
    }
}
