import { Component } from '@angular/core';
import { FormGroup, FormBuilder, FormArray } from '@angular/forms';

@Component({
  selector: 'app-form-main',
  templateUrl: './form-main.component.html',
  styleUrls: ['./form-main.component.scss']
})
export class FormMainComponent {

  tripDetailsForm: FormGroup;

  constructor(private fb: FormBuilder) {
    this.tripDetailsForm = this.fb.group({
      tripTitle: [''],
      tripStartDate: [''],
      days: this.fb.array([])
    })
   }

   addNewDay() {
     let control = <FormArray>this.tripDetailsForm.controls.days;

     control.push(
       this.fb.group({
        number: [],
        country: [''],
        city: [''],
        text: ['']
       })
     )
   }

   deleteDay(index){
    let control = <FormArray>this.tripDetailsForm.controls.days;
    control.removeAt(index);
  }

  onSubmit() {
    // TODO: Use EventEmitter with form value
    console.log(this.tripDetailsForm.value);
  }


}
