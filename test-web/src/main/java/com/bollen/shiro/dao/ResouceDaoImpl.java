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

import com.bollen.shiro.entity.Resource;

public class ResouceDaoImpl implements ResourceDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Resource createResource(final Resource resource) {
		final String sql = "insert into sys_resource(name, type, url, permission, parent_id, parent_ids, available) values(?,?,?,?,?,?,?)";
		GeneratedKeyHolder keyHolder = 
				new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement stmt = con.prepareStatement(sql, new String[]{"id"});
				int count = 1;
				stmt.setString(count++, resource.getName());
				stmt.setString(count++, resource.getType().name());
				stmt.setString(count++, resource.getUrl());
				stmt.setString(count++, resource.getPermission());
				stmt.setLong(count++, resource.getParentId());
				stmt.setString(count++, resource.getParentIds());
				stmt.setBoolean(count++, resource.getAvailable());
				
				return stmt;
				
			}
		},keyHolder);
		
		resource.setId(keyHolder.getKey().longValue());;
		
		return resource;
	}

	public Resource updateResource(Resource resource) {
		final String sql = "update sys_resource set name=?, type=?, url=?, permission=?, parent_id=?, parent_ids=?, available=? where id=?";
        jdbcTemplate.update(
                sql,
                resource.getName(), resource.getType().name(), resource.getUrl(), resource.getPermission(), resource.getParentId(), resource.getParentIds(), resource.getAvailable(), resource.getId());
        return resource;
	}

	public void deleteResource(Long resourceId) {
		Resource resource = findOne(resourceId);
        final String deleteSelfSql = "delete from sys_resource where id=?";
        jdbcTemplate.update(deleteSelfSql, resourceId);
        final String deleteDescendantsSql = "delete from sys_resource where parent_ids like ?";
        jdbcTemplate.update(deleteDescendantsSql, resource.makeSelfAsParentIds() + "%");
	}

	public Resource findOne(Long resourceId) {
		final String sql = "select id, name, type, url, permission, parent_id, parent_ids, available from sys_resource where id=?";
        List<Resource> resourceList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Resource.class), resourceId);
        if(resourceList.size() == 0) {
            return null;
        }
        return resourceList.get(0);
	}

	public List<Resource> findAll() {
		 final String sql = "select id, name, type, url, permission, parent_id, parent_ids, available from sys_resource order by concat(parent_ids, id) asc";
	        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(Resource.class));
	}

}
