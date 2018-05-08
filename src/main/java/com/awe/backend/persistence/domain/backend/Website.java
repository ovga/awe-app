package com.awe.backend.persistence.domain.backend;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity(name="websites")
public class Website implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="websites_id_seq")
    @SequenceGenerator(name="websites_id_seq", sequenceName="websites_id_seq", allocationSize=1)
	private Long id;
	private String title;
	private String image;
	private String url;
	private String description;
	private String prettyname;
	private Boolean visible;

	@JsonBackReference
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            })
    @JoinTable(name = "web_cat",
            joinColumns = { @JoinColumn(name = "web_id") },
            inverseJoinColumns = { @JoinColumn(name = "cat_id") })
    private Set<Category> categories = new HashSet<>();
 
	/** Default constructor*/
	public Website() {
		
	}
	
    public void addCategory(Category category) {
    	categories.add(category);
    	category.getWebsites().add(this);
    }
 
    public void removeCategory(Category category) {
    	categories.remove(category);
        category.getWebsites().remove(this);
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrettyname() {
		return prettyname;
	}

	public void setPrettyname(String prettyname) {
		this.prettyname = prettyname;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Website other = (Website) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Website [id=");
		builder.append(id);
		builder.append(", title=");
		builder.append(title);
		builder.append(", image=");
		builder.append(image);
		builder.append(", url=");
		builder.append(url);
		builder.append(", description=");
		builder.append(description);
		builder.append(", prettyname=");
		builder.append(prettyname);
		builder.append(", visible=");
		builder.append(visible);
		builder.append("]");
		return builder.toString();
	}
	
}
