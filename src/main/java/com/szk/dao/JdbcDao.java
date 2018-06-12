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
	 * queryForObject()方法的测试
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
		System.out.println("queryForObject()得到的name:"+user.getName());
	}
	
	/**
	 * Lambda表达式来优化上一个方法
	 */
	public void testRowLambda() {
		Integer id = 1;
		String sql = "select id, name from spring_jdbc where id = " + id;
		user = this.getJdbcTemplate().queryForObject(sql, (ResultSet rs, int rowNum) ->{
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			return user;
		});
		System.out.println("queryForObject()使用Lambda表达式得到的name:"+user.getName());
	}
	
	/**
	 * 增  ： 增加一个User
	 * @param jdbcTemplate
	 * @return     影响条数
	 */
	public int insertUser(JdbcTemplate jdbcTemplate) {
		String name = "zlq";
		String sql = "insert into spring_jdbc(name) values(?)";
		return jdbcTemplate.update(sql, name);
	}
	
	/**
	 * 删  ： 删除一个User
	 * @param jdbcTemplate
	 * @param id        User的id
	 * @return     影响条数
	 */
	public int deleteUser(JdbcTemplate jdbcTemplate, Integer id) {
		String sql = "delete from spring_jdbc where id = ? ";
		return jdbcTemplate.update(sql, id);
	}
	
	/**
	 * 改  ：更新一个用户的name
	 * @param jdbcTemplate
	 * @param user    
	 * @return    影响条数
	 */
	public int updateUser(JdbcTemplate jdbcTemplate, User user) {
		String sql = "update spring_jdbc set name = ? where id = ? ";
		return jdbcTemplate.update(sql, user.getName(), user.getId());
	}
	
	/**
	 * 查  ：模糊查询姓名（这里用到了Lambda表达式）
	 * @param jdbcTemplate
	 * @param userName     姓名（支持部分）
	 * @return             User列表
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
	 * 查  ：查询所有的User
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
