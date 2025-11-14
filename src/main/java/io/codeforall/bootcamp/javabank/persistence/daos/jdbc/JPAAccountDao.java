package io.codeforall.bootcamp.javabank.persistence.daos.jdbc;

import io.codeforall.bootcamp.javabank.model.account.AbstractAccount;
import io.codeforall.bootcamp.javabank.persistence.daos.AccountDao;
import io.codeforall.bootcamp.javabank.persistence.jdbc.JPASessionManager;
import io.codeforall.bootcamp.javabank.factories.AccountFactory;
import io.codeforall.bootcamp.javabank.model.account.Account;
import io.codeforall.bootcamp.javabank.model.account.AccountType;
import io.codeforall.bootcamp.javabank.persistence.TransactionException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class JPAAccountDao implements AccountDao {

    private EntityManager em;
    private JPASessionManager sm;
    private EntityManagerFactory emf;

    public void setConnectionManager(JPASessionManager JDBCSessionManager) {
        this.sm = JDBCSessionManager;
    }

    @Override
    public List<AbstractAccount> findAll() {
        em = emf.createEntityManager();

        try {

            TypedQuery<AbstractAccount> query =
                    em.createQuery("SELECT account FROM Accounts account", AbstractAccount.class);
            return query.getResultList();

        } finally {
            if (em != null) {
                em.close();
            }
        }

    }

    @Override
    public AbstractAccount findById(Integer id) {
        Account account = null;

        try {

            String query = "SELECT id, account_type, customer_id, balance, version FROM accounts WHERE id=?";
            PreparedStatement statement = sm.getCurrentSession().prepareStatement(query);

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                AccountType accountType = AccountType.valueOf(resultSet.getString("account_type"));

                account = AccountFactory.createAccount(accountType);
                account.setId(resultSet.getInt("id"));
                account.setCustomerId(resultSet.getInt("customer_id"));
                account.setVersion(resultSet.getInt("version"));
                account.credit(resultSet.getInt("balance"));
            }

            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return account;
    }

    @Override
    public AbstractAccount saveOrUpdate(AbstractAccount modelObject) {
        return null;
    }

    @Override
    public Account saveOrUpdate(Account modelObject) {
        try {

            Integer id = null;

            if (modelObject.getId() != null && (modelObject.getId()) != null) {
                id = update(modelObject);
            } else {
                id = insert(modelObject);
            }

            modelObject.setId(id);
            return modelObject;

        } catch (SQLException e) {
            throw new TransactionException();
        }
    }

    @Override
    public void delete(Integer id) {

        try {

            String query = "DELETE FROM accounts WHERE id = ?";

            PreparedStatement statement = sm.getCurrentSession().prepareStatement(query);

            statement.setInt(1, id);

            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            throw new TransactionException();
        }

    }

    private Integer update(Account account) throws SQLException {


        String query = "UPDATE accounts SET balance = ?, version = ? WHERE id = ?";

        PreparedStatement statement = sm.getCurrentSession().prepareStatement(query);

        statement.setDouble(1, account.getBalance());
        statement.setInt(2, account.getVersion() + 1);
        statement.setInt(3, account.getId());

        statement.executeUpdate();
        statement.close();

        return account.getId();

    }


    private Integer insert(Account account) throws SQLException {


        String query = "INSERT INTO accounts(account_type, balance, customer_id) " +
                "VALUES (?, ?, ?)";

        PreparedStatement statement = sm.getCurrentSession().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, account.getAccountType().name());
        statement.setDouble(2, account.getBalance());
        statement.setInt(3, account.getCustomer());

        statement.executeUpdate();

        ResultSet generatedKeys = statement.getGeneratedKeys();

        if (generatedKeys.next()) {
            account.setId(generatedKeys.getInt(1));
        }

        statement.close();
        return account.getId();

    }

}
