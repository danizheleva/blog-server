import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Trip } from 'src/app/models/trips-models';

@Component({
  selector: 'app-trip-detail-main',
  templateUrl: './trip-detail-main.component.html',
  styleUrls: ['./trip-detail-main.component.scss']
})
export class TripDetailMainComponent implements OnInit {

  currentTrip: Trip;

  constructor(private _activatedRotue: ActivatedRoute) { }

  ngOnInit() {
    this._activatedRotue.data.subscribe((data: {component: Trip}) => {
      this.currentTrip = data.component;
      console.log(this.currentTrip)
    })
  }

}
