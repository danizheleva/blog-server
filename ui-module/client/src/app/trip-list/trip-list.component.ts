import { Component, OnInit } from '@angular/core';
import { TripService } from '../shared/trips/trip.service';

@Component({
  selector: 'app-trip-list',
  templateUrl: './trip-list.component.html',
  styleUrls: ['./trip-list.component.scss']
})
export class TripListComponent implements OnInit {

  trips: Array<any>;

  constructor(private _tripService: TripService ) { }

  ngOnInit() {
    this._tripService.getAll().subscribe(data => {
      this.trips = data;
    })
  }

}
