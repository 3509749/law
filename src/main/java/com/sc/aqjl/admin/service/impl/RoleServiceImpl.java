package com.sc.aqjl.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sc.aqjl.admin.mapper.RoleMapper;
import com.sc.aqjl.admin.model.RoleResource;
import com.sc.aqjl.admin.model.UPermission;
import com.sc.aqjl.admin.model.URole;
import com.sc.aqjl.admin.model.User;
import com.sc.aqjl.admin.service.RoleService;
import com.sc.aqjl.base.model.PageBean;
import com.sc.aqjl.base.model.TreeModel;

import com.github.pagehelper.PageHelper;

@Service
@Transactional
// 此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class RoleServiceImpl  implements RoleService {
	@Autowired
    private RoleMapper roleMapper;
	public List<URole>  getRolesByUserId(String userId){
		return roleMapper.getUserRole(userId);
	}
	public List<UPermission> listByUserId(String userId){
		return roleMapper.listByUserId(userId);
	}
	@Override
	public List<UPermission> listParent() {
		// TODO Auto-generated method stub
		return roleMapper.listParent();
	}
	@Override
	public List<UPermission> listChild() {
		// TODO Auto-generated method stub
		return roleMapper.listChild();
	} 
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PageBean getRoleByPage(URole u, int page, int limit) {
		PageHelper.startPage(page, limit);
		List list = roleMapper.getRole(u);
		int countNums = roleMapper.getRoleCount(u);
		PageBean<User> pageData = new PageBean<>(page, limit, countNums);
		pageData.setItems(list);
		return pageData;
	}
	@Override
	public int insertRole(URole u) {
		int r=0;
		try {
			r=roleMapper.insertRole(u);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	@Override
	public int deleteRoles(String[] ids) {
		int r=0;
		try {
			roleMapper.deleteResourceRole(ids);
			r= roleMapper.deleteRoles(ids);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	@Override
	public URole getRole(URole u) {
		// TODO Auto-generated method stub
		return (URole) roleMapper.getRole(u).get(0);
	}
	@Override
	public int updateRole(URole u) {
		int r=0;
		try {
			r=roleMapper.updateRole(u);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	@Override
	public List<TreeModel> getResourceTree(String id) {
		List<UPermission> list = roleMapper.getAllResource();
		List<UPermission> currList = roleMapper.getResource(id);
		List<TreeModel> treeList = new ArrayList<>();
		for(UPermission temp : list){
			TreeModel t = new TreeModel();
			t.setId(temp.getId());
			t.setpId(temp.getParentId());
			t.setName(temp.getName());
			treeList.add(t);
		}
		for(TreeModel temp : treeList){
			for(UPermission u : currList){
				if(u.getId().equals(temp.getId()))
					temp.setChecked(true);
			}
		}
		return treeList; 
	}
	@Override
	public int insertResourceRole(String roleId,String resourceId) {
		int row=0;
		roleMapper.deleteResourceRole(roleId.split(","));
		try {
			for(String resId : resourceId.split(",")){
				RoleResource r = new RoleResource(roleId,resId);
				roleMapper.insertResourceRole(r);
				row++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return row;
	}
	
}
