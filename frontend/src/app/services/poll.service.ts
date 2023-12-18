import { Injectable } from '@angular/core';
import { AxiosService } from './axios.service';

@Injectable({
  providedIn: 'root'
})
export class PollService {

  private pollId: number = 1;

  constructor(private axiosService: AxiosService) { }

  getPoll() {
    return this.axiosService.request("GET", "api/polls/" + this.pollId, {});
  }

  sendVote(optionId: number) {
    return this.axiosService.request("POST", "api/polls/" + this.pollId + "/vote/" + optionId, {});
  }
}
