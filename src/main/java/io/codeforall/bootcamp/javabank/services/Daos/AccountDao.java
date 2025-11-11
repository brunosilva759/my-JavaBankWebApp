package io.codeforall.bootcamp.javabank.services.Daos;

import io.codeforall.bootcamp.javabank.model.account.Account;

import java.util.List;

public interface AccountDao {

    List<Account> findAll();

    Account findById(Integer id);

    Account saveOrUpdate(Account account);

    void delete(Integer id);

}
