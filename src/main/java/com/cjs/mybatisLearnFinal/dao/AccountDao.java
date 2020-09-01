package com.cjs.mybatisLearnFinal.dao;

import com.cjs.mybatisLearnFinal.domain.Account;

import java.util.List;

public interface AccountDao {
    List<Account> findAccountAll();

    List<Account> findAccountInnerUserAll();
}
