package com.jingwenli.codelifter.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
public class User {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message="User name is required")
    @Size(min=3, message="User name must be greater than 3 characters")
    private String userName;
    
    @NotBlank(message="Email is required")
    @Email(message="Please enter a valid email!")
    private String email;
    
    @NotBlank(message="Password is required")
    @Size(min=8, message="Password must be greater than 8 characters")
    private String password;
    
    @Transient
    @NotEmpty(message="Confirm Password is required!")
    @Size(min=8, message="Confirm Password must be greater than 8 characters")
    private String confirm;
    
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "users_roles", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
    
    @Column(updatable=false)
    @OneToMany(mappedBy="lead", fetch = FetchType.LAZY)
    private List<StudyGroup> studyGroupsLed;
    
    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "users_study_groups",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "study_group_id")
	)
    private List<StudyGroup> studyGroups;
    
    public User() {
    }

//  getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
    
//  ----------------relationship with JobPost-----------------------------
    @OneToMany(mappedBy="jobPostCreator", fetch = FetchType.LAZY)
    private List<JobPost> createdJobPosts;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "commenters_jobPosts", 
        joinColumns = @JoinColumn(name = "jobPostCommenter_id"), 
        inverseJoinColumns = @JoinColumn(name = "jobPost_id")
    )
    private List<JobPost> commentededJobPosts;
    
//  ----------------relationship with InterviewPrepPost--------------------
    @OneToMany(mappedBy="InterviewPrepPostCreator", fetch = FetchType.LAZY)
    private List<InterviewPrepPost> createdInterviewPrepPosts;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "commenters_interviewPrepPosts", 
        joinColumns = @JoinColumn(name = "interviewPrepPostCommenter_id"), 
        inverseJoinColumns = @JoinColumn(name = "interviewPrepPost_id")
    )
    private List<InterviewPrepPost> commentedInterviewPrepPosts;
    
//  ----------------relationship with LifestylePost------------------------
    @OneToMany(mappedBy="lifestylePostCreator", fetch = FetchType.LAZY)
    private List<LifestylePost> createdLifestylePosts;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "commenters_lifestylePosts", 
        joinColumns = @JoinColumn(name = "lifestylePostCommenter_id"), 
        inverseJoinColumns = @JoinColumn(name = "lifestylePost_id")
    )
    private List<LifestylePost> commentedLifestylePosts;
    
//  ----------------relationship with SuccessStory-------------------------
    @OneToMany(mappedBy="successStoryCreator", fetch = FetchType.LAZY)
    private List<SuccessStory> createdSuccessStories;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "commenters_successStories", 
        joinColumns = @JoinColumn(name = "successStoryCommenter_id"), 
        inverseJoinColumns = @JoinColumn(name = "successStory_id")
    )
    private List<SuccessStory> commentededSuccessStories;

	public List<JobPost> getCreatedJobPosts() {
		return createdJobPosts;
	}
	
	public void setCreatedJobPosts(List<JobPost> createdJobPosts) {
		this.createdJobPosts = createdJobPosts;
	}
	
	public List<JobPost> getCommentededJobPosts() {
		return commentededJobPosts;
	}
	
	public void setCommentededJobPosts(List<JobPost> commentededJobPosts) {
		this.commentededJobPosts = commentededJobPosts;
	}
	
	public List<StudyGroup> getStudyGroupsLed() {
		return studyGroupsLed;
	}
	public void setStudyGroupsLed(List<StudyGroup> studyGroupsLed) {
		this.studyGroupsLed = studyGroupsLed;
	}
	public List<StudyGroup> getStudyGroups() {
		return studyGroups;
	}
	public void setStudyGroups(List<StudyGroup> studyGroups) {
		this.studyGroups = studyGroups;
	}
	public List<InterviewPrepPost> getCreatedInterviewPrepPosts() {
		return createdInterviewPrepPosts;
	}
	
	public void setCreatedInterviewPrepPosts(List<InterviewPrepPost> createdInterviewPrepPosts) {
		this.createdInterviewPrepPosts = createdInterviewPrepPosts;
	}
	
	public List<InterviewPrepPost> getCommentedInterviewPrepPosts() {
		return commentedInterviewPrepPosts;
	}
	
	public void setCommentedInterviewPrepPosts(List<InterviewPrepPost> commentedInterviewPrepPosts) {
		this.commentedInterviewPrepPosts = commentedInterviewPrepPosts;
	}
	
	public List<LifestylePost> getCreatedLifestylePosts() {
		return createdLifestylePosts;
	}
	
	public void setCreatedLifestylePosts(List<LifestylePost> createdLifestylePosts) {
		this.createdLifestylePosts = createdLifestylePosts;
	}
	
	public List<LifestylePost> getCommentedLifestylePosts() {
		return commentedLifestylePosts;
	}
	
	public void setCommentedLifestylePosts(List<LifestylePost> commentedLifestylePosts) {
		this.commentedLifestylePosts = commentedLifestylePosts;
	}
	
	public List<SuccessStory> getCreatedSuccessStories() {
		return createdSuccessStories;
	}
	
	public void setCreatedSuccessStories(List<SuccessStory> createdSuccessStories) {
		this.createdSuccessStories = createdSuccessStories;
	}
	
	public List<SuccessStory> getCommentededSuccessStories() {
		return commentededSuccessStories;
	}
	
	public void setCommentededSuccessStories(List<SuccessStory> commentededSuccessStories) {
		this.commentededSuccessStories = commentededSuccessStories;
	}
}
