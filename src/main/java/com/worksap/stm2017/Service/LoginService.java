package com.worksap.stm2017.Service;

import com.worksap.stm2017.entity.LoginInfo;

public interface LoginService {
    boolean checkLogin(LoginInfo loginInfo);
    boolean isAdmin(LoginInfo loginInfo);
}
