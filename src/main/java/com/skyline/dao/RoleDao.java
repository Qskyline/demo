package com.skyline.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skyline.entity.Role;

public interface RoleDao extends JpaRepository<Role, String> {
	public Role findByRoleName(String roleName);

}
