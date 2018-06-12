package com.szk.dao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import com.szk.vo.User;

@Component("userDao")
public class UserDao extends JdbcDaoSupport{

	public void add(User user) {
		String sql = "insert into spring_jdbc (name) values(?)";
		this.getJdbcTemplate().update(sql, user.getName());
	}
	
	public void update(User user) {
		
	}
	
	public void delete(User user) {
		
	}
	
	public void find(User user) {
		
	}
	
}
