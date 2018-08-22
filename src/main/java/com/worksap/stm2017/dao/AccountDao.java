package com.worksap.stm2017.dao;

import com.worksap.stm2017.domain.Account;

public interface AccountDao {
    void insert(Account account);
    int matchCount(Account account);
    void update(Account account);
}
