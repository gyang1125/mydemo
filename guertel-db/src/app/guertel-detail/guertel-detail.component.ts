import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { Guertel } from '../Guertel';

declare var $: any;  

@Component({
  selector: 'app-guertel-detail',
  templateUrl: './guertel-detail.component.html',
  styleUrls: ['./guertel-detail.component.css']
})
export class GuertelDetailComponent implements OnChanges, OnInit {

  @Input() guertel?: Guertel;
  imgPath!: String;
  
  constructor() {
  }

  ngOnInit(): void {
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.imgPath = "/assets/images/"+this.guertel?.nummer+".jpg";
    console.log(this.imgPath);
  }

}
