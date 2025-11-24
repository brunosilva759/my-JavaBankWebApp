package io.codeforall.bootcamp.javabank.controller;

import io.codeforall.bootcamp.javabank.persistence.dao.CustomerDao;
import io.codeforall.bootcamp.javabank.persistence.dao.jpa.JpaAccountDao;
import io.codeforall.bootcamp.javabank.persistence.dao.jpa.JpaCustomerDao;
import io.codeforall.bootcamp.javabank.persistence.model.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {


    private final JpaCustomerDao customerDao;

    public CustomerController(JpaCustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/customers")
    public String listCustomers(Model model) {

        List<Customer> customers = customerDao.findAll();


        model.addAttribute("customer.id", customers);

        return "customers";
    }

}
