import { Component } from '@angular/core';
import { FormGroup, FormBuilder, FormArray } from '@angular/forms';
import { TripService } from 'src/app/shared/trips/trip.service';

@Component({
  selector: 'app-form-main',
  templateUrl: './form-main.component.html',
  styleUrls: ['./form-main.component.scss']
})
export class FormMainComponent {

  tripDetailsForm: FormGroup;

  constructor(private fb: FormBuilder, private _tripService: TripService) {
    this.tripDetailsForm = this.fb.group({
      tripTitle: [''],
      tripStartDate: [''],
      listOfDays: this.fb.array([]),
      postCreationDate: [new Date()]
    })
   }

   addNewDay() {
     let control = <FormArray>this.tripDetailsForm.controls.listOfDays;

     control.push(
       this.fb.group({
        dayTitle: [''],
        dayNumber: [0],
        country: [''],
        city: [''],
        postText: ['']
       })
     )
   }

   deleteDay(index){
    let control = <FormArray>this.tripDetailsForm.controls.listOfDays;
    control.removeAt(index);
  }

  onSubmit() {
    this.registerTrip(this.tripDetailsForm.value);
  }

  private registerTrip(formData) {
    console.log("form data details " + this.tripDetailsForm.value);
    console.log("sending data: " + formData);
    this._tripService.registerTrip(formData).subscribe((response) => {
      console.log(response);
    });
  }


}
