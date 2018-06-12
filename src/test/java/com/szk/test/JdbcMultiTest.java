package com.szk.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.szk.dao.JdbcMultiDao;
import com.szk.vo.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JdbcMultiTest {

	@Autowired
	@Qualifier("multiDao")
	private JdbcMultiDao dao;
	
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	@Qualifier("user")
	private User user;
	
	@Test
	public void test1() {
		
		user = dao.getUserByConnectionCallback(jdbcTemplate, 1);
		System.out.println(user);
		
	}
	
	@Test
	public void test2() {
		List<User> list = new ArrayList<User>();
		list = dao.getUserByStatementCallback(jdbcTemplate);
		System.out.println(list);
	}
	
}
