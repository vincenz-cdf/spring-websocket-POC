package com.vinz.pollingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.vinz.pollingapp.dao.OptionDao;
import com.vinz.pollingapp.dao.PollDao;
import com.vinz.pollingapp.db.Option;
import com.vinz.pollingapp.db.Poll;
import com.vinz.pollingapp.dto.PollDto;
import com.vinz.pollingapp.exception.AppException;


@Service
public class PollService {
	
	@Autowired
	private PollDao pollDao;
	
	@Autowired
	private OptionDao optionDao;


	public PollDto getPoll(Long pollId) {
		Poll poll = pollDao.findById(pollId).orElseThrow(() -> new AppException("Unknown poll", HttpStatus.NOT_FOUND));

		return new PollDto(poll);
	}
	
	public void voteOption(Long pollId, Long optionId) {
		Option option = optionDao.findById(optionId).orElseThrow(() -> new AppException("Unknown option", HttpStatus.NOT_FOUND));
		
		option.setVoteCount(option.getVoteCount() + 1);
		optionDao.save(option);
	}
	
}
