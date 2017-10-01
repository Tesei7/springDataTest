package hello;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

public interface CustomerRepository extends CrudRepository<Customer, Long>, QueryDslPredicateExecutor<Customer> {

    List<Customer> findTop1ByAddressesCountry(String country);

    Optional<Customer> findTop1ByAddressesCity(String city);

    Future<Customer> findByLastName(String lastName);

    Page<Customer> findAll(Pageable pageable);

    @Query("select c from #{#entityName} c where c.firstName like ?1 or c.lastName like ?1")
    List<Customer> findByName(String firstName, Sort sort);

    @Modifying(clearAutomatically = true)
    @Query("update #{#entityName} c set c.firstName = :firstName where c.lastName = :lastName")
    void setFirstNameByLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);

    @Transactional
    void deleteByLastName(String lastName);
}
