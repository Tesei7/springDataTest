package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class CustomerController {

    @RequestMapping("/{id}")
    public String customer(@PathVariable("id") Customer customer, Model model) {
        model.addAttribute("customer", customer);
        return "customer";
    }
}
