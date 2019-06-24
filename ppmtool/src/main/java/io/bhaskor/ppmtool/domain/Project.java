package io.bhaskor.ppmtool.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Project Name is required")
	private String projectName;

	@NotBlank(message = "Project Identifier is required")
	@Size(min = 4, max = 5, message="Please use 4 to 5 characters")
	@Column(updatable = false, unique = true)
	private String projectIdentifier;

	@NotBlank(message = "Project description is required")
	private String description;

	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date startDate;

	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date endDate;

	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date created_At;
	
	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date updated_At;

	@PrePersist
	protected void onCreate() {
		this.created_At = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updated_At = new Date();
	}

	public Project() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectIdentifier() {
		return projectIdentifier;
	}

	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getCreated_At() {
		return created_At;
	}

	// public void setCreated_At(Date created_At) {
	// this.created_At = created_At;
	// }

	public Date getUpdated_At() {
		return updated_At;
	}

	// public void setUpdated_At(Date updated_At) {
	// this.updated_At = updated_At;
	// }

}