package pl.karas.spring;

import java.io.Serializable;


@SuppressWarnings("serial")
public class Movie implements Serializable, Cloneable {

	private Long id;
	private String title;
	private String description;
	
	public Movie() {
	
	}
	
	public Movie(Long id, String title, String description) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
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
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public boolean isPersisted() {
		return id != null;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (this.id == null) {
			return false;
		}

		if (obj instanceof Movie && obj.getClass().equals(getClass())) {
			return this.id.equals(((Movie) obj).id);
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 43 * hash + (id == null ? 0 : id.hashCode());
		return hash;
	}

	@Override
	public Movie clone() throws CloneNotSupportedException {
		return (Movie) super.clone();
	}

	@Override
	public String toString() {
		return title;
	}
	
	
}
