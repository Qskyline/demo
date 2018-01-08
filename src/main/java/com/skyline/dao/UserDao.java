package com.skyline.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.skyline.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, String>  {
	public User findByUsername(String username);
	public List<User> findByMobilephoneNumber(String mobilephoneNumber);
	public List<User> findByUsernameAndPassword(String username, String password);
}
