package com.skyline.service;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.skyline.controller.Authentication;
import com.skyline.dao.RoleDao;
import com.skyline.dao.UserDao;
import com.skyline.dao.UserLoginLogDao;
import com.skyline.dao.UserRoleDao;
import com.skyline.entity.Role;
import com.skyline.entity.User;
import com.skyline.entity.UserLoginLog;
import com.skyline.entity.UserRole;
import com.skyline.util.TimeUtil;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private UserRoleDao userRoleDao;
	@Autowired
	private UserLoginLogDao userLoginLogDao;
	@Autowired
	private LogService logService;
	
	private final Logger logger = LoggerFactory.getLogger(Authentication.class);
	private String initialRoleName = "user";
	private int tryCount = 3;
	
	public enum RegisterStatus {
		success, usernameAlreadyExist, mobilephoneNumberAlreadyExist, unknowError
	}
	
	@Transactional
	public RegisterStatus register(
			String userName, 
			String mobilephoneNumber,
			String password,
			String telephoneNumber
			) {
		if(userDao.findByUsername(userName) == null) {
			if(userDao.findByMobilephoneNumber(mobilephoneNumber).size() == 0) {
				User user = new User();
				user.setUsername(userName);
				user.setMobilephoneNumber(mobilephoneNumber);
				user.setPassword(password);
				if(telephoneNumber != null) {
					user.setTelephoneNumber(telephoneNumber);
				}
				Date date = TimeUtil.getDateNow();
				user.setRegisterTime(date);
				user.setUpdateTime(date);
				
				Role role = roleDao.findByRoleName(initialRoleName);
				
				UserRole userRole = new UserRole();
				userRole.setUser(user);
				userRole.setRole(role);
				
				UserLoginLog userLoginLog = new UserLoginLog();
				userLoginLog.setUser(user);

				try {
					userDao.save(user);
					userRoleDao.save(userRole);
					userLoginLogDao.save(userLoginLog);
				} catch (Exception e) {
					logService.dbErrorLog(logger, e);
					return RegisterStatus.unknowError;
				}
//				success
				return RegisterStatus.success;
			} else {
//				mobilephoneNumber is registered
				return RegisterStatus.mobilephoneNumberAlreadyExist;
			}
		}
//		username is used
		return RegisterStatus.usernameAlreadyExist;
	}
	
	@Transactional
	public User getUser(String userName) {
		if(userName == null) return null;
		return userDao.findByUsername(userName);
	}
	
	@Transactional
	public boolean updateSuccessUserLoginlog(String userName) {
		User user = userDao.findByUsername(userName);
		if(user != null) {
			UserLoginLog userLoginLog = user.getUserLoginLog();
			if(userLoginLog != null) {
				userLoginLog.setLatestLoginTime(TimeUtil.getDateNow());
				userLoginLog.setCount(0);
				userLoginLog.setLatestAttemptLoginTime(null);
				try {
					if(user.getIsLocked()) {
						user.setIsLocked(false);
						userDao.save(user);				
					}				
					userLoginLogDao.save(userLoginLog);
					return true;
				} catch (Exception e) {
					logService.dbErrorLog(logger, e);
				}
			}
		}
		return false;
	}
	
	@Transactional
	public boolean updateFailedUserLoginlog(String userName) {
		User user = userDao.findByUsername(userName);
		if(user != null) {
			UserLoginLog userLoginLog = user.getUserLoginLog();
			if(userLoginLog != null) {
				int c = userLoginLog.getCount();
				if(c >= tryCount - 1) {
					user.setIsLocked(true);
				}
				userLoginLog.setCount(++c);
				userLoginLog.setLatestAttemptLoginTime(TimeUtil.getDateNow());
				try {
					userDao.save(user);
					userLoginLogDao.save(userLoginLog);
					return true;
				} catch (Exception e) {
					logService.dbErrorLog(logger, e);
				}
			}
		}
		return false;
	}
	
	public boolean unlockUser(User user) {
		if(user == null) return false;
		UserLoginLog userLoginLog = user.getUserLoginLog();
		if(userLoginLog == null)  return false;
		user.setIsLocked(false);
		userLoginLog.setCount(0);
		userLoginLog.setLatestAttemptLoginTime(null);
		try {
			userDao.save(user);
			userLoginLogDao.save(userLoginLog);
			return true;
		} catch (Exception e) {
			logService.dbErrorLog(logger, e);
		}
		return false;
	}
	
	
	/**
	 * 锁定超时,尝试解锁
	 * @param user
	 * @return
	 */
	public void attemptUnlockUser(User user, int timeMinute) {
		Date d = user.getUserLoginLog().getLatestAttemptLoginTime();
		Date fix_d = TimeUtil.getOffsetDate(d, timeMinute, TimeUtil.Unit.minute);
		if(fix_d != null && fix_d.compareTo(TimeUtil.getDateNow()) <= 0) {
			unlockUser(user);
		}
	}
}
