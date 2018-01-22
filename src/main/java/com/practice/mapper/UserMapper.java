package com.practice.mapper;

import java.util.List;

import com.practice.model.User;
import com.practice.util.MyMapper;

public interface UserMapper extends MyMapper<User> {
	public List<User> findUser(User user);
	public void addUser(User user);
	public void delUser(User user);
	public void updateUser(User user);
}

