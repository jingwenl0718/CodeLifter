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
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="successstories")
public class SuccessStory {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
	
    @NotNull
    @Size(min = 3, max = 200, message="Title must be at least 3 provided.")
    private String title;
    
    @NotNull
    @Size(min = 5, max = 200, message="Headline must be at least 5 provided.")
    private String headline;
    
    @NotNull
    @Size(min = 10, max = 1000, message="Description must be least 10 characters")
    private String description;
    
    @Column(nullable = true, length = 64)
    private String image;
    
    @Transient
    public String getPhotosImagePath() {
        if (image == null || id == null) return null;
        return "/successstory-image/" + id + "/" + image;
    }
    

	//  ----------------relationship with User-----------------------------
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="successstorycreator_id")
    private User successStoryCreator;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "commenters_successstories", 
        joinColumns = @JoinColumn(name = "successstory_id"), 
        inverseJoinColumns = @JoinColumn(name = "successstorycommenter_id")
    )
    private List<User> successStoryCommenters;
    
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
    
    public SuccessStory() {}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public String getHeadline() {
		return headline;
	}
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
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
	public User getSuccessStoryCreator() {
		return successStoryCreator;
	}
	public void setSuccessStoryCreator(User successStoryCreator) {
		this.successStoryCreator = successStoryCreator;
	}
	public List<User> getSuccessStoryCommenters() {
		return successStoryCommenters;
	}
	public void setSuccessStoryCommenters(List<User> successStoryCommenters) {
		this.successStoryCommenters = successStoryCommenters;
	}
}
