package com.worksap.stm2017.Service;

import com.worksap.stm2017.dao.AccountDao;
import com.worksap.stm2017.dao.AdminDao;
import com.worksap.stm2017.dao.DaoFactory;
import com.worksap.stm2017.domain.Account;
import com.worksap.stm2017.entity.LoginInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    private AccountDao accountDao;
    private AdminDao adminDao;

    @Autowired
    public LoginServiceImpl(DaoFactory daoFactory) {
        this.accountDao = daoFactory.getAccountDao();
        this.adminDao = daoFactory.getAdminDao();
    }

    public void createAccout(LoginInfo loginInfo) {
        accountDao.insert(new Account(loginInfo.getId(), loginInfo.getPassword()));
    }

    public boolean checkLogin(LoginInfo loginInfo) {
        return accountDao.matchCount(new Account(loginInfo.getId(), loginInfo.getPassword())) > 0;
    }

    public boolean isAdmin(LoginInfo loginInfo) {
        return adminDao.matchCount(loginInfo.getId()) > 0;
    }
}
