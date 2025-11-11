package io.codeforall.bootcamp.javabank.services.Daos;

import io.codeforall.bootcamp.javabank.model.account.Account;

import java.util.List;

public class JdbcAccountDao implements AccountDao {


    @Override
    public List<Account> findAll() {
        return List.of()
    }

    @Override
    public Account findById(Integer id) {
        return null;
    }

    @Override
    public Account saveOrUpdate(Account account) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
