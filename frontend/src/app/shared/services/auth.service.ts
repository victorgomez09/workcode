import { Injectable, inject } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { SignIn } from '../models/auth.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private http: HttpClient = inject(HttpClient)

  signIn(data: SignIn) {
    this.http.post('http://localhost:8080/auth/login', data).subscribe(result => console.log('result', result))
  }
}
