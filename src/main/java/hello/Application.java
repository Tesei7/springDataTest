package hello;

import com.querydsl.core.types.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Optional;
import java.util.function.Supplier;

@SpringBootApplication
@EnableWebMvc
@EnableSpringDataWebSupport
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CustomerService customerService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    private void init() {
        Address address1 = addressRepository.save(new Address("USA", "LA"));
        Address address2 = addressRepository.save(new Address("USA", "SanFran"));
        Address address3 = addressRepository.save(new Address("UK", "London"));
        Address address4 = addressRepository.save(new Address("Russia", "Saint-Petersburg"));
        Address address5 = addressRepository.save(new Address("Canada", "Toronto"));
        Address address6 = addressRepository.save(new Address("USA", "Chi"));

        customerRepository.save(new Customer("Jack", "Bauer").addAddress(address1));
        customerRepository.save(new Customer("Chloe", "O'Brian").addAddress(address2));
        customerRepository.save(new Customer("Kim", "Bauer").addAddress(address3));
        customerRepository.save(new Customer("David", "Palmer").addAddress(address4));
        customerRepository.save(new Customer("Michelle", "Dessler").addAddress(address5).addAddress(address6));
    }

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {
            init();
            query("All Customers", customerRepository::findAll);
//            query("findTop1ByAddressesCountry", () -> customerRepository.findTop1ByAddressesCountry("USA"));
//            query("findByName", () -> customerRepository.findByName("%a%", new Sort(Sort.Direction.DESC, "lastName")));
//            queryOne("findTop1ByAddressesCity", () -> customerRepository.findTop1ByAddressesCity("Moscow"));

            log.info("=== setFirstNameByLastName ===\n");
            customerService.setFirstNameByLastName("NEW NAME", "Bauer");

            query("All Customers", customerRepository::findAll);

//            log.info("=== deleteByLastName ===\n");
//            customerRepository.deleteByLastName("Bauer");

//            query("All Customers", customerRepository::findAll);

//            QCustomer customer = QCustomer.customer;
//            Predicate predicate = customer.firstName.equalsIgnoreCase("david")
//                    .and(customer.lastName.startsWithIgnoreCase("palmer"));
//            queryPredicate("findByPredicate", predicate);

            queryNamesOnly("Names Only", () -> customerRepository.findByFirstName("Jack"));
        };
    }

    private void query(String label, Supplier<Iterable<Customer>> supplier) {
        log.info(label);
        for (Customer customer : supplier.get()) {
            log.info(customer.toString());
        }
        log.info("");
    }

    private void queryNamesOnly(String label, Supplier<Iterable<CustomerProjection>> supplier) {
        log.info(label);
        for (CustomerProjection customer : supplier.get()) {
            log.info(customer.toString());
        }
        log.info("");
    }

    private void queryPredicate(String label, Predicate predicate) {
        log.info(label);
        for (Customer customer : customerRepository.findAll(predicate)) {
            log.info(customer.toString());
        }
        log.info("");
    }

    private void queryOne(String label, Supplier<Optional<Customer>> supplier) {
        log.info(label);
        Optional<Customer> optional = supplier.get();
        if (optional.isPresent()) {
            log.info(optional.get().toString());
        } else {
            log.info("No results");
        }
        log.info("");
    }
}
