package com.vinz.pollingapp.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.vinz.pollingapp.db.Option;
import com.vinz.pollingapp.db.Poll;

public class PollDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String question;
	private List<OptionDto> options;
	
	public PollDto() {}
	
	public PollDto(Poll poll) {
		id = poll.getId();
		question = poll.getQuestion();
		
		options = new ArrayList<>();
		for (Option o : poll.getOptions()) {
			options.add(new OptionDto(o));
		}
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public List<OptionDto> getOptions() {
		return options;
	}
	public void setOptions(List<OptionDto> options) {
		this.options = options;
	}
	
	
}
