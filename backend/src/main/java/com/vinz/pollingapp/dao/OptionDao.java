package com.vinz.pollingapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinz.pollingapp.db.Option;

public interface OptionDao extends JpaRepository<Option, Long> {

}
