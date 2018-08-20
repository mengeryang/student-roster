package com.worksap.stm2017.Service;

import com.worksap.stm2017.dao.DaoFactory;
import com.worksap.stm2017.dao.UserDao;
import com.worksap.stm2017.dao.UserWorkTimeDao;
import com.worksap.stm2017.domain.User;
import com.worksap.stm2017.domain.UserWorkTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public class UserServiceImpl implements UserService {
//    private UserDao userDao;
//    private UserWorkTimeDao userWorkTimeDao;
//
//
//    @Autowired
//    public UserServiceImpl(DaoFactory daoFactory) {
//        this.userDao = daoFactory.getUserDao();
//        this.userWorkTimeDao = daoFactory.getUserWorkTimeDao();
//    }
//
//    public boolean login(User user) {
//        String userId = user.getUserId();
//        User res = userDao.findById(userId);
//        if(res == null)
//            return false;
//        return user.getPassword().equals(res.getPassword());
//    }
//
//    public boolean signup(User user) {
//        return userDao.insert(user);
//    }
//
//    public List<User> list() {
//        return userDao.list_all();
//    }
//
//    public void setIdleTime(List<UserWorkTime> timeList) {
//        timeList.stream().forEach( x -> userWorkTimeDao.insert(x) );
//    }
//
//    public String findname(String userid) {
//        return userDao.findById(userid).getUsername();
//    }
}
