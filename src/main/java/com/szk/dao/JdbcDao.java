package com.szk.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.szk.vo.User;

public class JdbcDao extends JdbcDaoSupport{
	
	@Autowired
	@Qualifier("user")
	private User user;
	
	
	/**
	 * queryForObject()�����Ĳ���
	 */
	public void testRow() {
		Integer id = 1;
		String sql = "select id, name from spring_jdbc where id = " + id;
		user = this.getJdbcTemplate().queryForObject(sql, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				return user;
			}
		});
		System.out.println("queryForObject()�õ���name:"+user.getName());
	}
	
	/**
	 * Lambda���ʽ���Ż���һ������
	 */
	public void testRowLambda() {
		Integer id = 1;
		String sql = "select id, name from spring_jdbc where id = " + id;
		user = this.getJdbcTemplate().queryForObject(sql, (ResultSet rs, int rowNum) ->{
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			return user;
		});
		System.out.println("queryForObject()ʹ��Lambda���ʽ�õ���name:"+user.getName());
	}
	
	/**
	 * ��  �� ����һ��User
	 * @param jdbcTemplate
	 * @return     Ӱ������
	 */
	public int insertUser(JdbcTemplate jdbcTemplate) {
		String name = "zlq";
		String sql = "insert into spring_jdbc(name) values(?)";
		return jdbcTemplate.update(sql, name);
	}
	
	/**
	 * ɾ  �� ɾ��һ��User
	 * @param jdbcTemplate
	 * @param id        User��id
	 * @return     Ӱ������
	 */
	public int deleteUser(JdbcTemplate jdbcTemplate, Integer id) {
		String sql = "delete from spring_jdbc where id = ? ";
		return jdbcTemplate.update(sql, id);
	}
	
	/**
	 * ��  ������һ���û���name
	 * @param jdbcTemplate
	 * @param user    
	 * @return    Ӱ������
	 */
	public int updateUser(JdbcTemplate jdbcTemplate, User user) {
		String sql = "update spring_jdbc set name = ? where id = ? ";
		return jdbcTemplate.update(sql, user.getName(), user.getId());
	}
	
	/**
	 * ��  ��ģ����ѯ�����������õ���Lambda���ʽ��
	 * @param jdbcTemplate
	 * @param userName     ������֧�ֲ��֣�
	 * @return             User�б�
	 */
	public List<User> listUserByName(JdbcTemplate jdbcTemplate, String userName){
		String sql = "select id, name from spring_jdbc where name like concat('%',?,'%')";
		Object[] params = {userName};
		List<User> list = jdbcTemplate.query(sql, params, (ResultSet rs, int rowNum) -> {
			
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			return user;
		});
		return list;
	}
	
	/**
	 * ��  ����ѯ���е�User
	 * @param jdbcTemplate
	 * @return
	 */
	public List<User> listUser(JdbcTemplate jdbcTemplate){
		String sql = "select id, name from spring_jdbc ";
		Object[] params = {};
		List<User> list = jdbcTemplate.query(sql, params, (ResultSet rs, int rowNum) -> {
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			return user;
		});
		return list;
	}
	
	
	
}
