package com.sc.aqjl.admin.mapper;
 
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sc.aqjl.admin.model.UUser;
import com.sc.aqjl.admin.model.User;
@Mapper  
public interface UserMapper {
	public UUser getUserByLoninName(Object map);
	public List<User> getUserByPage(User u);
	public int getUserCount(User u);
	public int insertUser(User u);
	public int deleteUsers(String[] ids);
	public int deleteRoles(String[] ids);
	public User getUserById(String id);
	public int updateUser(User u);
	public int updatePass(User u);
	public int insertRole(User u);
	public int deleteRole(User u);
	
}

