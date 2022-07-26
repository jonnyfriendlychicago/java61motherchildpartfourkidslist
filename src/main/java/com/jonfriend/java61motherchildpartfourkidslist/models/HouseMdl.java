package com.jonfriend.java61motherchildpartfourkidslist.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn; // JRF manually adding

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
//JRF: keep below OUT when building the autoJoinTbl solution
//import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="house")
public class HouseMdl {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;

	// begin: entity-specific table fields
	private String houseName;
    
    // end: entity-specific table fields
    
    // start: code for joins

	// join twinone
    @OneToMany(mappedBy="houseMdl", fetch = FetchType.LAZY)
    private List<TwinoneMdl> twinoneList; 
    
    // join user table
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="createdby_id")
	private UserMdl userMdl;  
	
    // end: code for joins
	
    // instantiate the model: 
    public HouseMdl() {}
    
    // add methods to populate maintain createdAt/UpdatedAt
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

    // begin: getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}

	public List<TwinoneMdl> getTwinoneList() {
		return twinoneList;
	}

	public void setTwinoneList(List<TwinoneMdl> twinoneList) {
		this.twinoneList = twinoneList;
	}

	public UserMdl getUserMdl() {
		return userMdl;
	}

	public void setUserMdl(UserMdl userMdl) {
		this.userMdl = userMdl;
	}

    
    // end: getters and setters
    
// end mdl
}
