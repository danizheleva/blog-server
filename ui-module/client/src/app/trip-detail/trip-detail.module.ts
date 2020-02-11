import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TripDetailMainComponent } from './trip-detail-main/trip-detail-main.component';



@NgModule({
  declarations: [TripDetailMainComponent],
  imports: [
    CommonModule
  ],
  exports: [
    TripDetailMainComponent
  ]
})
export class TripDetailModule { }
