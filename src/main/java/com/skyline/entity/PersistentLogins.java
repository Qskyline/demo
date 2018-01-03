package com.skyline.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "persistent_logins")
public class PersistentLogins {
	private String series;
	private String username;
	private String token;
	private Date last_used;
	
	@Id
    @Column(name = "series", length = 64)
	@GeneratedValue(generator = "persistentLogins_id")
    @GenericGenerator(name = "persistentLogins_id", strategy = "uuid")
	public String getSeries() {
		return series;
	}
	public void setSeries(String series) {
		this.series = series;
	}
	
	@Type(type = "string")
	@Column(name = "username", length = 64, nullable = false)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Type(type = "string")
	@Column(name = "token", length = 64, nullable = false)
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	@Type(type = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_used", nullable = false)
	public Date getLast_used() {
		return last_used;
	}
	public void setLast_used(Date last_used) {
		this.last_used = last_used;
	}
}
