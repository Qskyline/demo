package com.skyline.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "User")
public class User {
    private String id; 
	private String username;  
    private String password; 
    private String mobilephoneNumber;
    private String telephoneNumber;
    private Date registerTime;
    private Date activeTime;
    private Date updateTime;
    private boolean isLocked;
    private boolean isActive;
    private boolean isFrozen;
    private List<UserRole> userRole;
    private UserLoginLog userLoginLog;
    
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "user_id")
    @GenericGenerator(name = "user_id", strategy = "uuid")
    public String getId() {
        return id;  
    }
    public void setId(String id) {  
        this.id = id;  
    }
    
    @Type(type = "string")
    @Column(name = "username", length = 20, nullable = false, unique = true)
	public String getUsername() {  
	    return username;  
	}  
	public void setUsername(String username) {  
	    this.username = username;  
	}  
	
	@Type(type = "string")
	@Column(name = "password", length = 100, nullable = false)
	public String getPassword() {
	    return password;
	}  
	public void setPassword(String password) {  
	    this.password = password;  
	}
	
	@Type(type = "string")
	@Column(name = "mobilephoneNumber", length = 11, nullable = false, unique = true)
	public String getMobilephoneNumber() {  
	    return mobilephoneNumber;
	}  
	public void setMobilephoneNumber(String mobilephoneNumber) {  
	    this.mobilephoneNumber = mobilephoneNumber;  
	}  
	
	@Type(type = "string")
	@Column(name = "telephoneNumber", length = 12)
	@ColumnDefault(value = "null")
	public String getTelephoneNumber() {
	    return telephoneNumber;  
	}
	public void setTelephoneNumber(String telephoneNumber) {  
	    this.telephoneNumber = telephoneNumber;  
	}
	
	@Type(type = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)  //define the precision of returning value
	@Column(name = "registerTime", updatable = false, nullable = false)
	@ColumnDefault(value = "CURRENT_TIMESTAMP")
	public Date getRegisterTime() {
		return registerTime;
	}	
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	
	@Type(type = "timestamp")
	@Temporal(TemporalType.TIMESTAMP) 
	@Column(name = "activeTime", insertable = false)
	@ColumnDefault(value = "null")
	public Date getActiveTime() {
		return activeTime;
	}	
	public void setActiveTime(Date activeTime) {
		this.activeTime = activeTime;
	}
	
	@Type(type = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updateTime", nullable = false)
	@ColumnDefault(value = "CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@Column(name = "isLocked", nullable = false)
	@ColumnDefault(value = "false")
	public boolean getIsLocked() {  
	    return isLocked;  
	}  
	public void setIsLocked(boolean isLocked) {  
	    this.isLocked = isLocked;  
	}
	
	@Column(name = "isActive", nullable = false)
	@ColumnDefault(value = "false")
	public boolean getIsActive() {
	    return isActive;  
	}  
	public void setIsActive(boolean isActive) {  
	    this.isActive = isActive;  
	}
	
	@Column(name = "isFrozen", nullable = false)
	@ColumnDefault(value = "false")
	public boolean getIsFrozen() {
	    return isFrozen;  
	}  
	public void setIsFrozen(boolean isFrozen) {  
	    this.isFrozen = isFrozen;
	}
	
	@OneToMany(targetEntity=UserRole.class, cascade=CascadeType.ALL, mappedBy="user", fetch=FetchType.LAZY) 
	public List<UserRole> getUserRole(){
		return userRole;
	}
	public void setUserRole(List<UserRole> userRole) {
		this.userRole = userRole;
	}
	
	@OneToOne(targetEntity=UserLoginLog.class, cascade=CascadeType.ALL, mappedBy="user", fetch=FetchType.LAZY) 
	public UserLoginLog getUserLoginLog(){
		return userLoginLog;
	}
	public void setUserLoginLog(UserLoginLog userLoginLog) {
		this.userLoginLog = userLoginLog;
	}
}
