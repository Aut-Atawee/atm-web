package th.ac.ku.atm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import th.ac.ku.atm.model.Customer;
import th.ac.ku.atm.service.CustomerService;

@Controller
@RequestMapping("/login")
public class LoginController {

    private CustomerService customerService ;

    public LoginController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String getLoginPage() {
        return "login" ;
    }

    @PostMapping
    public String login(@ModelAttribute Customer customer, Model model) {

        // 1.) Check to see if Id and Pin matched customer info
        Customer matchingCustomer = customerService.checkPin(customer) ;
        // 2.) If match, Welcome customer
        if (matchingCustomer != null) {
            model.addAttribute("greeting", "Welcome, " + matchingCustomer.getName()) ;
        } else {
            // 3.) Not match, display that customer info its incorrect
            model.addAttribute("greeting", "Can't find customer") ;
        }

        return "home" ;
    }
}
