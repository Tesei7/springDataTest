package hello;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface AddressRepository extends CrudRepository<Address, Long> {

}
