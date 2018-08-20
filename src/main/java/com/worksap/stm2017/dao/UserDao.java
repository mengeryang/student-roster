package com.worksap.stm2017.dao;

import com.worksap.stm2017.domain.User;

import java.util.List;

public interface UserDao {
    public int getMatchIdCount(String id);
    public boolean insert(User user);
    public List<User> findByName(String name);
    public User findById(String id);
    public List<User> list_all();
}
