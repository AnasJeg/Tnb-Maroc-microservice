import { Component } from '@angular/core';
import { AuthService } from '../core/auth/auth.service';
import { HttpErrorResponse } from '@angular/common/http';
import { AuthenticationRequest } from '../core/models/login.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  credentials: AuthenticationRequest = {
    cin: '',
    password: ''
  };

  constructor(private authService: AuthService) {}

  onSubmit(): void {
    console.log('Cin:', this.credentials.cin);
    console.log('Password:', this.credentials.password);

    this.authService.signIn(this.credentials).subscribe(
      (response) => {
        console.log('Login successful. Tokens:', response.body);
        const accessToken = response.body?.accessToken;
        const refreshToken = response.body?.refreshToken;

        console.log("accessToken", accessToken);
        console.log("refreshToken", refreshToken);

      },
      (error) => {
        console.error('Login failed:', error);
        if (error instanceof HttpErrorResponse) {
          console.log('Error Status:', error.status);
          console.log('Error Body:', error.error);
        }
      }
    );
  }
}
