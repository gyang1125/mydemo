import { Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Guertel } from '../Guertel';

@Component({
  selector: 'app-guertel-list',
  templateUrl: './guertel-list.component.html',
  styleUrls: ['./guertel-list.component.css'],
})
export class GuertelListComponent implements OnChanges, OnInit{
  @Input() key?: String;
  selectedGuertel!: Guertel;
  guertelArray: Guertel[] = [];
  findArray: Guertel[] = [];
  pageOfItems?: Array<any>;
  
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

  /*
   * only one time run, but before constructor
  */
  ngOnInit(): void {
    //console.log('init get the key: ' + this.key);
    this.findByKey();
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.findArray = [];
    //console.log('change get the key: ' + this.key);
    if(this.key === "*") {
      this.findArray = this.guertelArray;
    } else {
      this.findByKey();
    }

  }
  
  onChangePage(pageOfItems: Array<any>) {
    // update current page of items
    this.pageOfItems = pageOfItems;
}

  private findByKey() {
    
    for (var val of this.guertelArray) {
      //console.log(val.id);
      let k:string = this.key?.toUpperCase() as string;
      if (val.nummer.toUpperCase() === this.key?.toUpperCase()) {
        this.findArray.push(val);
      } else if (val.nummer.toUpperCase().search(k) != -1) {
        this.findArray.push(val);
      } else if (val.objekt.toUpperCase().search(k) != -1) {
        this.findArray.push(val);
      }
    }
  }

  onSelect(guertel: Guertel): void {
    console.log(guertel);
    this.selectedGuertel = guertel;
  }
}

