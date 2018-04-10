package dao;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.core.RowMapper;

import model.Login;

import model.User;

public class UserDaoImpl implements UserDao {

	@Autowired

	DataSource datasource;

	@Autowired

	JdbcTemplate jdbcTemplate;

	public void register(User user) {
		String sql = "insert into users values(?,?,?)";
		if (user.getAccounttype().equals("Reporter")) {
			String reportersSql = "insert into reporters values(?,?,?)";
			jdbcTemplate.update(reportersSql, new Object[] { user.getUsername(), user.getfield1(), user.getfield2() });
		} else {
			String resolversSql = "insert into resolvers values(?,?)";
			jdbcTemplate.update(resolversSql, new Object[] { user.getUsername(), user.getfield1() });
			String serviceSql = "insert into service values(?,?)";
			jdbcTemplate.update(serviceSql, new Object[] { user.getfield1(), user.getfield2() });
		}

		jdbcTemplate.update(sql, new Object[] { user.getUsername(), user.getPassword(), user.getAccounttype() });

	}

	public User validateUser(Login login) {

		String sql = "select * from users where username='" + login.getUsername() + "' and password='"
				+ login.getPassword()

				+ "'";

		List<User> users = jdbcTemplate.query(sql, new UserMapper());

		return users.size() > 0 ? users.get(0) : null;

	}

	public boolean checkIfUsernameExists(String username) {

		String sql = "select * from users where username='" + username + "'";

		List<User> users = jdbcTemplate.query(sql, new UserMapper());

		if (!users.isEmpty()) {

			return true;

		}

		else {

			return false;

		}

	}
	
	public boolean checkIfCompanyExists(String company) {

		String sql = "select * from resolvers where companyname='" + company + "'";

		List<User> companies = jdbcTemplate.query(sql, new ResolverMapper());

		if (!companies.isEmpty()) {

			return true;

		}

		else {

			return false;

		}

	}

}

class UserMapper implements RowMapper<User> {

	public User mapRow(ResultSet rs, int arg1) throws SQLException {

		User user = new User();

		user.setUsername(rs.getString("username"));

		user.setPassword(rs.getString("password"));
		
		user.setAccounttype(rs.getString("accounttype"));

		return user;

	}

}

class ResolverMapper implements RowMapper<User> {

	public User mapRow(ResultSet rs, int arg1) throws SQLException {

		User user = new User();
		
		user.setUsername(rs.getString("username"));

		user.setfield1(rs.getString("companyname"));

		return user;

	}

}