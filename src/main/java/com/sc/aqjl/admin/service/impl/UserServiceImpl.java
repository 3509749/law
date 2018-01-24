package com.sc.aqjl.admin.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sc.aqjl.admin.mapper.UserMapper;
import com.sc.aqjl.admin.model.UUser;
import com.sc.aqjl.admin.model.User;
import com.sc.aqjl.admin.service.UserService;
import com.sc.aqjl.base.model.PageBean;

import com.github.pagehelper.PageHelper;
 
@Service
@Transactional
// 此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class UserServiceImpl  implements UserService {
	@Autowired
    private UserMapper userMapper;
	public UUser getUserByLoninName(Object map) {
		UUser user = userMapper.getUserByLoninName(map);
		return user;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PageBean getUserByPage(User u, int page, int limit) {
		PageHelper.startPage(page, limit);
		List list = userMapper.getUserByPage(u);
		int countNums = userMapper.getUserCount(u);
		PageBean<User> pageData = new PageBean<>(page, limit, countNums);
		pageData.setItems(list);
		return pageData;
	}
	public int insertUser(User u){
		int r=0;
		try {
			r=userMapper.insertUser(u);
			r+=userMapper.insertRole(u);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	@Override
	public int deleteUsers(String[] ids) {
		int r=0;
		try {
			userMapper.deleteRoles(ids);
			r= userMapper.deleteUsers(ids);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	@Override
	public User getUserById(String id) {
		// TODO Auto-generated method stub
		return userMapper.getUserById(id);
	}
	@Override
	public int updateUser(User u) {
		int r=0;
		try {
			r= userMapper.updateUser(u);
			   userMapper.deleteRole(u);
			r+=userMapper.insertRole(u);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	@Override
	public int updatePass(User u) {
		int r=0;
		try {
			r= userMapper.updatePass(u);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
}
