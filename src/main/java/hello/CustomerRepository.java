package hello;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

public interface CustomerRepository extends CrudRepository<Customer, Long>, QueryDslPredicateExecutor<Customer> {

    List<Customer> findTop1ByAddressesCountry(String country);

    Optional<Customer> findTop1ByAddressesCity(String city);

    Future<Customer> findByLastName(String lastName);
}
