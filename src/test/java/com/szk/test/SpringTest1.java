package com.szk.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.szk.dao.UserDao;
import com.szk.vo.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SpringTest1 {

	@Autowired
	@Qualifier("userDao")
	private UserDao UserDao;
	
	@Autowired
	@Qualifier("user")
	private User user;
	 
	@Test
	public void test1() {
		user.setName("smy");
		UserDao.add(user);
	}
	
}
