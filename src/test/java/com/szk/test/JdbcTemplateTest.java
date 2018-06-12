package com.szk.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.szk.dao.JdbcDao;
import com.szk.vo.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JdbcTemplateTest {
	
	@Autowired
	@Qualifier("user")
	private User user;
	
	@Autowired
	@Qualifier("jdbcDao")
	private JdbcDao JdbcDao;
	
	@Test
	public void test() {
		//JdbcDao.testRow();
		JdbcDao.testRowLambda();
	}
	
	@Test
	public void test2() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		JdbcTemplate jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);
		
		// ��
		JdbcDao.insertUser(jdbcTemplate);
		printList(jdbcTemplate);
		// ɾ
		JdbcDao.deleteUser(jdbcTemplate, 5);
		printList(jdbcTemplate);
		// ��
		user.setId(1);
		user.setName("����٩");
		JdbcDao.updateUser(jdbcTemplate, user);
		printList(jdbcTemplate);
	}
	
	@Test
	public void test3() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		JdbcTemplate jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);
		List<User> users = JdbcDao.listUserByName(jdbcTemplate, "s");
		System.out.println(users);
	}
	
	/**
	 * ��ӡ��ǰ���е�User
	 * @param jdbcTemplate
	 */
	public void printList(JdbcTemplate jdbcTemplate) {
		// �����е�User
		List<User> list= JdbcDao.listUser(jdbcTemplate);
		list.forEach(x -> {
			System.out.print(x+" ");
		});
		System.out.println();
	}
	
}
