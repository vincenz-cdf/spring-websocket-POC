import { Component, OnInit } from '@angular/core';
import { PollService } from '../services/poll.service';
import { Poll } from '../models/poll.model';
import { WebSocketService } from '../services/web-socket.service';

@Component({
  selector: 'app-poll',
  templateUrl: './poll.component.html',
  styleUrls: ['./poll.component.css']
})
export class PollComponent implements OnInit {

  public poll: Poll;

  public selectedAnswer: number = 0;
  public voted: boolean = false;

  constructor(private pollService: PollService,private webSocketService: WebSocketService) {
    this.poll = { id: 0, question: 'Loading...', options: []}
  }

  ngOnInit(): void {

    this.webSocketService.initializeWebSocketConnection();
    this.webSocketService.pollUpdateSubject.subscribe(updatedPoll => {
      this.poll = updatedPoll;
    });

    this.pollService.getPoll().then((response) => {
      this.poll = response.data;
    });
  }

  changeAnswer(id: number) {
    this.selectedAnswer = id;
  }

  sendVote() {
    this.pollService.sendVote(this.selectedAnswer);
    this.voted = true;
  }
}
