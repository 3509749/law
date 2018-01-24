package com.sc.aqjl.admin.service;

 
import com.sc.aqjl.admin.model.UUser;
import com.sc.aqjl.admin.model.User;
import com.sc.aqjl.base.model.PageBean;
 
public interface UserService {
	public UUser getUserByLoninName(Object map);
	public PageBean getUserByPage(User u, int page, int limit);
	public int insertUser(User u);
	public int deleteUsers(String[] ids);
	public User getUserById(String id);
	public int updateUser(User u);
	public int updatePass(User u);
}
