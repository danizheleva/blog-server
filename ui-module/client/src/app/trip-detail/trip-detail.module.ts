import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TripDetailMainComponent } from './trip-detail-main/trip-detail-main.component';
import { TripResolverService } from './trip-resolver.service';



@NgModule({
  declarations: [TripDetailMainComponent],
  imports: [
    CommonModule
  ],
  exports: [
    TripDetailMainComponent
  ],
  providers: [
    TripResolverService
  ]
})
export class TripDetailModule { }
