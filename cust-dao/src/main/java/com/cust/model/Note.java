package com.cust.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;

/**
 * Entity Pojo with fields which maps to Notes table in Database.
 * 
 * @author murali
 *
 */
@Entity
@Table(name = "Notes")
public class Note {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
	@SequenceGenerator(name="sequence_generator", sequenceName = "sequence_generator")
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;

	private String message;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdTime", nullable = false)
	private Date createdTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updatedTime", nullable = false)
	private Date updatedTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@PrePersist
	protected void onCreate() {
		createdTime = new Date(System.currentTimeMillis());
	}

	@PreUpdate
	protected void onUpdate() {
		updatedTime = new Date(System.currentTimeMillis());
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
}
