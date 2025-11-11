package io.codeforall.bootcamp.javabank.services.Daos;

import io.codeforall.bootcamp.javabank.model.Customer;
import io.codeforall.bootcamp.javabank.model.account.Account;
import io.codeforall.bootcamp.javabank.persistence.SessionManager;
import io.codeforall.bootcamp.javabank.persistence.TransactionManager;
import io.codeforall.bootcamp.javabank.services.AccountService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class JdbcCustomerDao implements CustomerDao {

    private AccountService accountService;
    private TransactionManager transactionManager;
    private SessionManager sessionManager;

    @Override
    public List<Customer> findAll() {

        Map<Integer, Customer> customers = new HashMap<>();

        try {
            String query = "SELECT customers.id AS cid, first_name, last_name, phone, email, customers.version as cVersion, accounts.id AS aid " +
                    "FROM customers " +
                    "LEFT JOIN accounts " +
                    "ON customers.id = accounts.customer_id";

            PreparedStatement statement = sessionManager.getCurrentSession().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                if (!customers.containsKey(resultSet.getInt("cid"))) {
                    Customer customer = buildCustomer(resultSet);
                    customers.put(customer.getId(), customer);
                }

                Account account = accountService.get(resultSet.getInt("aid"));
                if (account != null) {
                    customers.get(resultSet.getInt("cid")).addAccount(account);
                }
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new LinkedList<>(customers.values());

    }

    @Override
    public Customer findById(Integer id) {
        Customer customer = null;

        try {
            String query = "SELECT customers.id AS cid, first_name, last_name, phone, email, customers.version AS cVersion, accounts.id AS aid " +
                    "FROM customers " +
                    "LEFT JOIN accounts " +
                    "ON customers.id = accounts.customer_id " +
                    "WHERE customers.id = ?";

            PreparedStatement statement = sessionManager.getCurrentSession().prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                if (customer == null) {
                    customer = buildCustomer(resultSet);
                }

                int accountId = resultSet.getInt("aid");
                Account account = accountService.get(accountId);

                if (account == null) {
                    break;
                }

                customer.addAccount(account);
            }

            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer;
    }
//
//
    @Override
    public Customer saveOrUpdate(Customer customer) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    private Customer buildCustomer(ResultSet resultSet) throws SQLException {

        Customer customer = new Customer();

        customer.setId(resultSet.getInt("cid"));
        customer.setFirstName(resultSet.getString("first_name"));
        customer.setLastName(resultSet.getString("last_name"));
        customer.setPhone(resultSet.getString("phone"));
        customer.setEmail(resultSet.getString("email"));
        customer.setVersion(resultSet.getInt("cVersion"));

        return customer;
    }

}
