package com.skyline.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.skyline.entity.UserLoginLog;

@Repository
public interface UserLoginLogDao extends JpaRepository<UserLoginLog, String>  {

}
