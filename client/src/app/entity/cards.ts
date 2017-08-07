import {card} from "./card";

export class cards {
    
    list : Array<card> = [];
    
    constructor(a:Array<number>){
        a.map((b)=>{this.list.push(new card(b))});
    }
    getList(){
        return this.list;
    }
};