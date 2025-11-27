package io.codeforall.bootcamp.javabank.controller;

import io.codeforall.bootcamp.javabank.mapper.CustomerMapper;
import io.codeforall.bootcamp.javabank.persistence.dto.CustomerDto;
import io.codeforall.bootcamp.javabank.persistence.model.Customer;
import io.codeforall.bootcamp.javabank.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller responsible for rendering {@link Customer} related views
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    private CustomerMapper mapper= new CustomerMapper();

    private CustomerService customerService;
    /**
     * Sets the customer service
     *
     * @param customerService the customer service to set
     */
    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Renders a view with a list of customers
     *
     * @param model the model object
     * @return the view to render
     */
    @RequestMapping(method = RequestMethod.GET, path = {"/list", "/", ""})
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerService.list());
        return "customer/list";
    }

    /**
     * Renders a view with customer details
     *
     * @param id    the customer id
     * @param model the model object
     * @return the view to render
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public String showCustomer(@PathVariable Integer id, Model model) {
        model.addAttribute("customer", customerService.get(id));
        model.addAttribute("recipients", customerService.listRecipients(id));
        return "customer/show";
    }

    /**
     * Deletes a customer
     *
     * @param id the customer id
     * @return the view to render
     */
    @RequestMapping(method = RequestMethod.GET, path = "{id}/delete")
    public String deleteCustomer(@PathVariable Integer id) {
        customerService.delete(id);
        return "redirect:/customer/list";
    }

    /**
     * Deletes a recipient from a customer
     *
     * @param cid the customer id
     * @param rid the recipient id
     * @return the view to render
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{cid}/recipient/{rid}/delete/")
    public String deleteRecipient(@PathVariable Integer cid, @PathVariable Integer rid) {
        customerService.removeRecipient(cid, rid);
        return "redirect:/customer/" + cid;
    }






    @RequestMapping(method = RequestMethod.GET, path = "/create")
    public String showForm(Model model) {
        model.addAttribute("customerDTO", new CustomerDto());
        return "customer/create";
    }


    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public String saveCustomer(@Valid @ModelAttribute CustomerDto newCustomer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "customer/create";
        }

        customerService.save(mapper.toEntity(newCustomer));

        return "redirect:/customer/list";
    }



//    @RequestMapping(path = "/editor", method = RequestMethod.GET)
//    public String editCustomer() {
//    }

}
