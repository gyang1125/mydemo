import { Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import { Guertel } from '../Guertel';

@Component({
  selector: 'app-guertel-list',
  templateUrl: './guertel-list.component.html',
  styleUrls: ['./guertel-list.component.css'],
})
export class GuertelListComponent implements OnChanges, OnInit{
  @Input() key?: String;
  selectedGuertel!: Guertel;
  @Input() guertelArray: Guertel[] = [];
  findArray: Guertel[] = [];
  pageOfItems?: Array<any>;
  displayDetails?:Boolean;

  /*
   * only one time run, but before constructor
  */
  ngOnInit(): void {
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.displayDetails = false;
    this.findArray = [];
    console.log('change get the key: ' + this.key);
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
      } else if (val.datierung.search(k) != -1) {
        this.findArray.push(val);
      }
    }
  }

  onSelect(guertel: Guertel): void {
    console.log(guertel);
    this.selectedGuertel = guertel;
    this.displayDetails = true;
  }
}

