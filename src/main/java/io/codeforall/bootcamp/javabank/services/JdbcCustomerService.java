package io.codeforall.bootcamp.javabank.services;

import io.codeforall.bootcamp.javabank.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JdbcCustomerService implements CustomerService {

    private Connection dbConnection;

    @Override
    public Customer get(Integer id) {

        Customer customer = null;

        try {

            String query = "SELECT * FROM table WHERE id=?";
            PreparedStatement statement = dbConnection.prepareStatement(query);

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                customer = new Customer();
                resultSet.getInt("id");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return customer;
    }

    @Override
    public List<Customer> list() {
        try {

            String query = "SELECT * FROM customers";
            PreparedStatement statement = dbConnection.prepareStatement(query);
            List<Customer> list = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setName(resultSet.getString("first_name"));
                customer.setName(resultSet.getString("last_name"));
                list.add(customer);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public Set<Integer> listCustomerAccountIds(Integer id) {
        try {

            String query = "SELECT account_id FROM accounts WHERE customer_id=?";
            PreparedStatement statement = dbConnection.prepareStatement(query);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery(query);
            Set<Integer> accountIds = new HashSet<>();
            while (resultSet.next()) {
                accountIds.add(resultSet.getInt("id"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public double getBalance(int id) {
        try {

            String query = "SELECT SUM (balance) FROM acoounts WHERE customer_id=?";
            PreparedStatement statement = dbConnection.prepareStatement(query);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                return resultSet.getDouble("balance");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    @Override
    public void add(Customer customer) {
        try {

            dbConnection.setAutoCommit(false);

            String query = "INSERT INTO customers (first_name) VALUES (?)";
            PreparedStatement statement = dbConnection.prepareStatement(query);
            statement.setString(1, customer.getName());

            statement.executeUpdate();

            dbConnection.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
