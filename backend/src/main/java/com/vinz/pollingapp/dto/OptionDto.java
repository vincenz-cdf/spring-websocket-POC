package com.vinz.pollingapp.dto;

import java.io.Serializable;

import com.vinz.pollingapp.db.Option;

public class OptionDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String description;
	private Integer voteCount;
	
	public OptionDto() {};
	
	public OptionDto(Option option) {
		id = option.getId();
		description = option.getDescription();
		voteCount = option.getVoteCount();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getVoteCount() {
		return voteCount;
	}
	public void setVoteCount(Integer voteCount) {
		this.voteCount = voteCount;
	}
	
	
}
