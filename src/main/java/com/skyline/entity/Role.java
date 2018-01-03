package com.skyline.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "Role")
public class Role {
	private String id;
	private String roleName;
	private List<UserRole> userRole;
	
	@Id
    @Column(name = "id")
    @GeneratedValue(generator = "role_id")
    @GenericGenerator(name = "role_id", strategy = "uuid")
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    
    @Type(type = "string")
	@Column(name = "roleName", length = 12, nullable = false, unique = true)
	public String getRoleName() {
	    return roleName;
	}
	public void setRoleName(String roleName) {
	    this.roleName = roleName;
	}
	
	@OneToMany(targetEntity=UserRole.class, cascade=CascadeType.ALL, mappedBy="role", fetch=FetchType.LAZY)
	public List<UserRole> getUserRole(){
		return userRole;
	}
	public void setUserRole(List<UserRole> userRole) {
		this.userRole = userRole;
	}
}
