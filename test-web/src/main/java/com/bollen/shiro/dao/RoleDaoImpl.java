package com.bollen.shiro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.bollen.shiro.entity.Role;
import com.bollen.shiro.entity.User;

public class RoleDaoImpl implements RoleDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Role createRole(final Role role) {
		
		final String sql = "insert into sys_role(role, description, resource_ids, available) values (?,?,?,?)";
		
		GeneratedKeyHolder geKeyHolder = 
				new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ppst = con.prepareStatement(sql, new String[]{"id"});
				int count = 1;
				ppst.setString(count++, role.getRole());
				ppst.setString(count++, role.getDescription());
				ppst.setString(count++, role.getResourceIdsStr());
                ppst.setBoolean(count++, role.getAvailable());
                
                return ppst;
			}
		},geKeyHolder);
		
		role.setId(geKeyHolder.getKey().longValue());
		
		return role;
	}

	public Role updateRole(Role role) {
	    final String sql = "update sys_role set role=?, description=?, resource_ids=?, available=? where id=?";
        jdbcTemplate.update(
                sql,
                role.getRole(), role.getDescription(), role.getResourceIdsStr(), role.getAvailable(), role.getId());
        return role;
	}

	public void deleteRole(Long roleId) {
		 final String sql = "delete from sys_role where id=?";
	        jdbcTemplate.update(sql, roleId);
	}

	public Role findOne(Long roleId) {
		final String sql = "select id, role, description, resource_ids as resourceIdsStr, available from sys_role where id=?";
        List<Role> roleList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Role.class), roleId);
        if(roleList.size() == 0) {
            return null;
        }
        return roleList.get(0);
	}

	public List<Role> findAll() {
		final String sql = "select id, role, description, resource_ids as resourceIdsStr, available from sys_role";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(Role.class));
	}

}
