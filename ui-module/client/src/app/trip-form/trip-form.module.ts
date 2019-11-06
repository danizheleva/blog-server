import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormMainComponent } from './form-main/form-main.component';
import { TripDetailComponent } from './trip-detail/trip-detail.component';



@NgModule({
  declarations: [FormMainComponent, TripDetailComponent],
  imports: [
    CommonModule
  ]
})
export class TripFormModule { }
