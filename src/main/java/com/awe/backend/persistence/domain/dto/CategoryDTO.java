package com.awe.backend.persistence.domain.dto;

import java.io.Serializable;

public class CategoryDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String name;

	public CategoryDTO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
