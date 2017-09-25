package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Supplier;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressRepository addressRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    private void init() {
        Address address1 = addressRepository.save(new Address("USA", "LA"));
        Address address2 = addressRepository.save(new Address("USA", "SanFran"));
        Address address3 = addressRepository.save(new Address("USA", "Chi"));
        Address address4 = addressRepository.save(new Address("UK", "London"));
        Address address5 = addressRepository.save(new Address("Russia", "Saint-Petersburg"));
        Address address6 = addressRepository.save(new Address("Canada", "Toronto"));

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
//            query("findByAddressesCountry", () -> customerRepository.findByAddressesCountry("USA"));
            query("findByAddressesCountry", () -> customerRepository.findByAddressesCountry("USA"));
        };
    }

    private void query(String label, Supplier<Iterable<Customer>> supplier) {
        log.info(label);
        for (Customer customer : supplier.get()) {
            log.info(customer.toString());
        }
        log.info("");
    }
}
