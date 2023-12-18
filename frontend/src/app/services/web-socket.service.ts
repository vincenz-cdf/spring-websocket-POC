import { EventEmitter, Injectable } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WebSocketService {

  private stompClient: any;
  public pollUpdateSubject: Subject<any> = new Subject();

  initializeWebSocketConnection(): void {
    const serverUrl = 'http://localhost:8080/polls';
    const ws = new SockJS(serverUrl);
    this.stompClient = Stomp.over(ws);
    this.stompClient.connect({}, () => {
      this.stompClient.subscribe('/topic/pollUpdate', (message: any) => {
        if (message.body) {
          let updatedPoll = JSON.parse(message.body);
          this.pollUpdateSubject.next(updatedPoll);
        }
      });
    });
  }
}
