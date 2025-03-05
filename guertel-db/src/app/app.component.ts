import { Component, OnInit } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Guertel } from './Guertel';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  title = 'Datenbank Vielteiliger Gürtelgarnituren und Pferdegeschirr';
  inputKey?: String;
  guertelArray: Guertel[] = [];

  constructor(http: HttpClient){
    
    http.get('assets/data.csv', {responseType: 'text'})
    .subscribe(
        data => {
            let csvToRowArray = data.split("\n");
            for (let index = 1; index < csvToRowArray.length - 1; index++) {
              let row = csvToRowArray[index].split(",");
              this.guertelArray.push(new Guertel(
                row[0], 
                row[1], 
                row[2],
                row[3],
                row[4],
                row[5],
                row[6],
                row[7],));
            }
            console.log(this.guertelArray);
        },
        error => {
            console.log(error);
        }
    );


  }

  ngOnInit(): void {
  }

  search(val: String) {
    this.inputKey = val;
    console.log('search key: ', val);
  }

  onKey(event: any) {
    console.log('enter key: ', event.target.value);
    this.inputKey = event.target.value;
  }
}
