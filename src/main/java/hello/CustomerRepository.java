package hello;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

public interface CustomerRepository extends CrudRepository<Customer, Long>, QueryDslPredicateExecutor<Customer> {

    List<Customer> findTop1ByAddressesCountry(String country);

    Optional<Customer> findTop1ByAddressesCity(String city);

    Future<Customer> findByLastName(String lastName);

    Page<Customer> findAll(Pageable pageable);

    @Query("select c from Customer c where c.firstName = ?1 or c.lastName = ?1")
    List<Customer> findByName(String firstName);
}
