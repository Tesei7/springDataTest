package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public void setFirstNameByLastName(String firstName, String lastName) {
        customerRepository.setFirstNameByLastName(firstName, lastName);
    }
}
