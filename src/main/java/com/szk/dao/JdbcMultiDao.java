package com.szk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.szk.vo.User;

public class JdbcMultiDao extends JdbcDaoSupport{

	@Autowired
	@Qualifier("user")
	private User user;
	
	/**
	 * ʹ��ConnectionCallback �ӿڽ��лص�
	 * @param jdbcTemplate
	 * @param id
	 * @return
	 */
	public User getUserByConnectionCallback(JdbcTemplate jdbcTemplate, Integer id) {
		
		user = jdbcTemplate.execute((Connection conn) -> {
			String sql = "select id, name from spring_jdbc where id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
			}
			return user;
		});
		return user;
		
	}
	
	/**
	 * ʹ��  StatementCallback �ӿڽ��лص�
	 * @param jdbcTemplate
	 * @param id
	 * @return
	 */
	public List<User> getUserByStatementCallback(JdbcTemplate jdbcTemplate) {
		List<User> users = new ArrayList<>();
		users = jdbcTemplate.execute((Statement stmt) ->{
			List<User> list = new ArrayList<User>();
			String sql = "select id, name from spring_jdbc";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				list.add(user);       // list ����ʱuser�ĵ�ַ��user��ַ��һ���������ڷ�����ͬһ����ͣ���µ�user��fuck
				System.out.println(user);
			}
			return list;
			
		});
		return users;
		
		
	}
	
	
}
