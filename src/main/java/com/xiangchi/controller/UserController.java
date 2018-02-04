package com.xiangchi.controller;

import com.github.pagehelper.PageInfo;
import com.xiangchi.model.User;
import com.xiangchi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping
    public PageInfo<User> getAll(User user) {
        List<User> userList = userService.getAll(user);
        return new PageInfo<User>(userList);
    }

    /**
     * to see the someone's details by userid
     * @param id: the id of this user
     * @return
     */
    @RequestMapping(value = "/view/{id}")
    public User getUserById(@PathVariable Integer id){
    	User user = new User();
        user.setId(id);
        user.setName("xiangchi");
        user.setAge("32");
        user.setPage(2);
        user.setRows(2);
        user.setPassword("fan");
    	return user;
    }
    
    @RequestMapping(value = "/list/{name}")
    public List<User> getUserList(@PathVariable String name,@RequestParam String age){
    	User user=new User();
    	user.setAge(age);
    	user.setName(name);
    	List<User> userList=userService.getUserList(user);
    	return userList;
    }
    
    @RequestMapping(value="/add")
    public void addUser(){
    	User user=new User();
    	int id = new Random().nextInt();
    	user.setId(id);
    	user.setName("N" + id);
    	user.setAge("12");
    	user.setPassword("P" + id);
    	userService.addUser(user);
    }
    
    @RequestMapping(value="/del/{id}")
    public void delUser(@PathVariable Integer id){
    	User user=new User();
    	user.setId(id);
    	userService.delUser(user);
    }
    
    @RequestMapping(value="/update/{id}")
    public void updateUser(@PathVariable Integer id,@RequestParam String name){
    	User user=new User();
    	user.setId(id);
    	user.setName(name);
    	userService.updateUser(user);
    }

}

