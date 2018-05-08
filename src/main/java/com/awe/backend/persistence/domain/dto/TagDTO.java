package com.awe.backend.persistence.domain.dto;

import java.io.Serializable;

public class TagDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String name;

	public TagDTO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
