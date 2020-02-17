import { Component, OnInit } from '@angular/core';
import { TripService } from '../shared/trips/trip.service';
import { TripList, Trip } from '../models/trips-models';

@Component({
  selector: 'app-trip-list',
  templateUrl: './trip-list.component.html',
  styleUrls: ['./trip-list.component.scss']
})
export class TripListComponent implements OnInit {

  trips: TripList;
  tripLength: number;

  constructor(private _tripService: TripService ) { }

  ngOnInit() {
    this._tripService.getAll().subscribe(data => {
      this.trips = data;
    })
  }

  public getTripLength(trip: Trip): number {
    return trip.listOfDays.length;
  }

}
