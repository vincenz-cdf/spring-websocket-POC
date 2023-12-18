package com.vinz.pollingapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.http.HttpStatus;

import com.vinz.pollingapp.dao.OptionDao;
import com.vinz.pollingapp.dao.PollDao;
import com.vinz.pollingapp.db.Option;
import com.vinz.pollingapp.db.Poll;
import com.vinz.pollingapp.dto.PollDto;
import com.vinz.pollingapp.exception.AppException;
import com.vinz.pollingapp.service.PollService;

@ExtendWith(MockitoExtension.class)
public class PollServiceTest {

    @Mock
    private PollDao pollDao;

    @Mock
    private OptionDao optionDao;

    @InjectMocks
    private PollService pollService;

    @Test
    public void getPoll_ShouldReturnPollDto_WhenPollExists() {
        // Given
        Long pollId = 1L;
        Poll mockPoll = new Poll(); 
        when(pollDao.findById(pollId)).thenReturn(Optional.of(mockPoll));

        // When
        PollDto result = pollService.getPoll(pollId);

        // Then
        assertNotNull(result);
        assertEquals(new PollDto(mockPoll), result);
    }

    @Test
    public void getPoll_ShouldThrowAppException_WhenPollDoesNotExist() {
        // Given
        Long pollId = 1L;
        when(pollDao.findById(pollId)).thenReturn(Optional.empty());

        // Then
        Exception exception = assertThrows(AppException.class, () -> {
            pollService.getPoll(pollId);
        });

        assertEquals("Unknown poll", exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, ((AppException)exception).getStatus());
    }

    @Test
    public void voteOption_ShouldIncrementVoteCount_WhenOptionExists() {
        // Given
        Long optionId = 1L;
        Option mockOption = new Option();
        mockOption.setVoteCount(0);
        when(optionDao.findById(optionId)).thenReturn(Optional.of(mockOption));

        // When
        pollService.voteOption(1L, optionId);

        // Then
        assertEquals(1, mockOption.getVoteCount());
        verify(optionDao).save(mockOption);
    }

    @Test
    public void voteOption_ShouldThrowAppException_WhenOptionDoesNotExist() {
        // Given
        Long optionId = 1L;
        when(optionDao.findById(optionId)).thenReturn(Optional.empty());

        // Then
        Exception exception = assertThrows(AppException.class, () -> {
            pollService.voteOption(1L, optionId);
        });

        assertEquals("Unknown option", exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, ((AppException)exception).getStatus());
    }
}

