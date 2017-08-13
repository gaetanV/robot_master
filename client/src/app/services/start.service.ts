import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { cards } from '../entity/cards';

@Injectable()
export class StartService {
  private startUrl = 'http://localhost:8080/api/card/';
  constructor(private http: Http) {}
  
 
   gameStart(): Promise<cards> {
      return  this.http.get(this.startUrl)
          .toPromise()
          .then((response)=>{        
       
              return new cards(response.json());

              
          }
      )
          .catch(this.handleError);
    }

   private handleError(error: any): Promise<any> {
      console.error('An error occurred', error); 
      return Promise.reject(error.message || error);
   }
}
