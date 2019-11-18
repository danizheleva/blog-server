import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormMainComponent } from './form-main/form-main.component';
import { ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    FormMainComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule
  ]
})
export class TripFormModule { }
