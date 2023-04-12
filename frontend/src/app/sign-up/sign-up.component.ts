import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { ButtonComponent } from '../shared/components/button/button.component';
import { AbstractControl, FormControl, FormGroup, ReactiveFormsModule, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { AuthService } from '../shared/services/auth.service';

@Component({
  selector: 'app-sign-up',
  standalone: true,
  imports: [CommonModule, RouterModule, ReactiveFormsModule, ButtonComponent],
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent {

  formGroup: FormGroup;
  private authService: AuthService = inject(AuthService)

  constructor() {
    this.formGroup = new FormGroup({
      email: new FormControl('', [Validators.email, Validators.required]),
      name: new FormControl('', Validators.required),
      password: new FormControl('', [Validators.required, this.matchValidator('confirmPassword', true)]),
      confirmPassword: new FormControl('', [Validators.required, this.matchValidator('password')])
    })
  }

  f(name: string): AbstractControl | null {
    return this.formGroup.get(name);
  }

  matchValidator(
    matchTo: string,
    reverse?: boolean
  ): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      if (control.parent && reverse) {
        const c = (control.parent?.controls as any)[matchTo] as AbstractControl;
        if (c) {
          c.updateValueAndValidity();
        }
        return null;
      }

      return !!control.parent && !!control.parent.value && control.value ===
        (control.parent?.controls as any)[matchTo].value ? null : { matching: true };
    };
  }

  submit() {
    console.log('calling backend')
    this.authService.signIn(this.formGroup.value);
  }
}
