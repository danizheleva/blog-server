import { Injectable } from '@angular/core';
import { Trip } from '../models/trips-models';

import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';
import { TripService } from '../shared/trips/trip.service';

@Injectable({
  providedIn: 'root'
})
export class TripResolverService implements Resolve<Trip>{

  constructor(private _tripService: TripService ) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Trip> | any {
    console.log("id is: " + route.paramMap.get('id'))
    const id = route.paramMap.get('id');

    if(id) {
      return this._tripService.getTripById(Number(id));
    }
  }
}
