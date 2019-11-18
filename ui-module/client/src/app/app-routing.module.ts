import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { TripFormModule } from './trip-form/trip-form.module';
import { FormMainComponent } from './trip-form/form-main/form-main.component';
import { TripListComponent } from './trip-list/trip-list.component';


const routes: Routes = [
  {path: '', component: TripListComponent},
  {path: 'upload-form', component: FormMainComponent}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes,
    {
      enableTracing: true,
      useHash: true
    }),
    TripFormModule
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
