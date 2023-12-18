package com.vinz.pollingapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinz.pollingapp.db.Poll;

public interface PollDao extends JpaRepository<Poll, Long> {

}
