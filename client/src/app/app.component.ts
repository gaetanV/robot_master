import { Component } from '@angular/core';

import { StartService } from './services/start.service'
import { cards } from './entity/cards';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [StartService]
})
export class AppComponent {

  cards:cards;
  
  constructor(private StartService: StartService)
  {
      this.cards = new cards([]) ;
      this.StartService.gameStart().then( val => {this.cards= val;});
  }
}
