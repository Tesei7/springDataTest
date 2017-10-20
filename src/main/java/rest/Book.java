package rest;

import javax.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private String title;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    Book() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}