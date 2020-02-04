import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TripList } from 'src/app/models/trips-models';

@Injectable({
  providedIn: 'root'
})
export class TripService {

  private baseUrl: string = "//localhost:8080/trips"
  constructor(private _http: HttpClient) { }

  getAll(): Observable<TripList> {
    return this._http.get<TripList>(this.baseUrl)
  }

  public registerTrip(obj): Observable<any> {
    return this._http.post(this.baseUrl, obj, {
      headers: new HttpHeaders({
        'ContentType': 'application/json',
      })
    });
  }

}
