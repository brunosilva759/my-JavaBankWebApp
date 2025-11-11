package io.codeforall.bootcamp.javabank.services.Daos;

import io.codeforall.bootcamp.javabank.model.Customer;

import java.util.List;

public interface CustomerDao {

    List<Customer> findAll();

    Customer findById(Integer id);

    Customer saveOrUpdate(Customer customer);

    void delete(Integer id);

}
