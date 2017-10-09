package rest;

import javax.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String country;
    private String city;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private Person person;

    protected Address() {
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", person=" + person +
                '}';
    }
}
