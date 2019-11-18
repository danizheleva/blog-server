import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-form-main',
  templateUrl: './form-main.component.html',
  styleUrls: ['./form-main.component.scss']
})
export class FormMainComponent {

  tripDetailsForm = new FormGroup({
    tripTitle: new FormControl(''),
    tripStartDate: new FormControl(''),

    day: new FormGroup({
      number: new FormControl(0),
      country: new FormControl(""),
      city: new FormControl(""),
      text: new FormControl("")
    })
  });

  constructor() { }

  onSubmit() {
    // TODO: Use EventEmitter with form value

    console.log(this.tripDetailsForm.value);
  }

}
