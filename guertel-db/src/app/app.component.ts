import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  title = 'Datenbank Vielteiliger Gürtelgarnituren und Pferdegeschirr';
  inputKey?: String;

  ngOnInit(): void {
    this.search('0');
  }

  search(val: String) {
    if (!val) {
      val = '0';
    } 
    this.inputKey = val;
    
    console.log('search key: ', val);
  }

  onKey(event: any) {
    console.log("onkey");
    this.inputKey = event.target.value;
  }
}
