package hello;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.hateoas.PagedResources;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository repository;

    @RequestMapping("/{id}")
    public String customer(@PathVariable("id") Customer customer, Model model) {
        model.addAttribute("customer", customer);
        return "customer";
    }

    @RequestMapping
    public String customers(Model model, Pageable pageable) {
        Page<Customer> customers = repository.findAll(pageable);
        model.addAttribute("customers", customers);
        return "customers";
    }

    /*http://localhost:8080/customer/qdsl?addresses.country=USA*/
    @RequestMapping("/qdsl")
    public String customersQdsl(Model model, @QuerydslPredicate(root = Customer.class) Predicate predicate) {
        Iterable<Customer> customers = repository.findAll(predicate);
        model.addAttribute("customers", customers);
        return "customers";
    }

    @RequestMapping(value = "/hateoas", method = RequestMethod.GET, produces = {APPLICATION_JSON_VALUE})
    HttpEntity<PagedResources<Customer>> hateoas(Pageable pageable,
                                                 PagedResourcesAssembler assembler) {
        Page<Customer> customers = repository.findAll(pageable);
        return new ResponseEntity<>(assembler.toResource(customers), HttpStatus.OK);
    }
}
