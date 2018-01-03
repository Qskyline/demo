package com.skyline.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skyline.entity.User;
import com.skyline.entity.UserRole;

@Repository
public interface UserRoleDao extends JpaRepository<UserRole, String> {
	public List<UserRole> findByUser(User user);
}
