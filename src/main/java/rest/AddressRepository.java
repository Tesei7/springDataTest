package rest;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource(collectionResourceRel = "addresses", path = "people")
public interface AddressRepository extends CrudRepository<Address, Long> {
}
