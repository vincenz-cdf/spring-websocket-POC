package com.vinz.pollingapp.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinz.pollingapp.dto.PollDto;
import com.vinz.pollingapp.service.PollService;

@RestController
@RequestMapping("/api/polls")
public class PollUser {
    
    @Autowired
    private PollService pollService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @GetMapping("/{id}")
    public ResponseEntity<?> getPoll(@PathVariable Long id) {
        return ResponseEntity.ok(pollService.getPoll(id));
    }
    
    @PostMapping("/{id}/vote/{oId}")
    public void sendVote(@PathVariable Long id, @PathVariable Long oId) {
        pollService.voteOption(id, oId);
        // Fetch the updated poll
        PollDto updatedPoll = pollService.getPoll(id);
        // Broadcast the updated poll to all subscribers
        messagingTemplate.convertAndSend("/topic/pollUpdate", updatedPoll);
    }
}
