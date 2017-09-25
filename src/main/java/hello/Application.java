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
    private CustomerRepository cuetomerRepository;
    @Autowired
    private AddressRepository addressRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    private void init() {
        Address address1 = addressRepository.save(new Address("USA", "LA"));
        Address address2 = addressRepository.save(new Address("USA", "SanFran"));
        Address address3 = addressRepository.save(new Address("USA", "Chi"));
        Address address4 = addressRepository.save(new Address("USA", "NY"));
        Address address5 = addressRepository.save(new Address("USA", "San Diego"));
        Address address6 = addressRepository.save(new Address("USA", "Boston"));

        cuetomerRepository.save(new Customer("Jack", "Bauer").addAddress(address1));
        cuetomerRepository.save(new Customer("Chloe", "O'Brian").addAddress(address2));
        cuetomerRepository.save(new Customer("Kim", "Bauer").addAddress(address3));
        cuetomerRepository.save(new Customer("David", "Palmer").addAddress(address4));
        cuetomerRepository.save(new Customer("Michelle", "Dessler").addAddress(address5).addAddress(address6));
    }

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {
            init();
            query("All Customers", cuetomerRepository::findAll);
            query("Bauer", () -> cuetomerRepository.findByLastName("Bauer"));
        };
    }

    private void query(String label, Supplier<Iterable<Customer>> supplier) {
        log.info(label);
        log.info("-------------------------------");
        for (Customer customer : supplier.get()) {
            log.info(customer.toString());
        }
        log.info("");
    }

}
