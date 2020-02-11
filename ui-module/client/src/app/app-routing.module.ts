import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { TripFormModule } from './trip-form/trip-form.module';
import { FormMainComponent } from './trip-form/form-main/form-main.component';
import { TripListComponent } from './trip-list/trip-list.component';
import { TripDetailModule } from './trip-detail/trip-detail.module';
import { TripDetailMainComponent } from './trip-detail/trip-detail-main/trip-detail-main.component';


const routes: Routes = [
  {path: '', component: TripListComponent},
  {path: 'upload-form', component: FormMainComponent},
  {path: 'trip-detail', component: TripDetailMainComponent}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes,
    {
      enableTracing: true,
      useHash: true
    }),
    TripFormModule,
    TripDetailModule
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
