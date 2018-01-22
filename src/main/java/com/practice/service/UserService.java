package com.practice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.mapper.UserMapper;
import com.practice.model.User;
import com.github.pagehelper.PageHelper;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;
	
	public List<User> getAll(User user){
		if (user.getPage() != null && user.getRows() != null) {
            PageHelper.startPage(user.getPage(), user.getRows(), "id");
        }
		return userMapper.selectAll();
	}
	
	public User getUserById(Integer id){
		return userMapper.selectByPrimaryKey(id);
	}
	
	public List<User> getUserList(User user){
		return userMapper.findUser(user);
	}
	
	public void delUser(User user){
		userMapper.delUser(user);
	}
	
	public void addUser(User user){
		userMapper.addUser(user);
	}
	
	public void updateUser(User user){
		userMapper.updateUser(user);
	}
}

