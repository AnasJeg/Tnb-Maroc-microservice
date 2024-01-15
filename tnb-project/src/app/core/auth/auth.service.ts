import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {catchError, Observable, of, switchMap, throwError} from 'rxjs';
import {environment} from "../../../environments/environment";
import { Redevable } from '../models/redevable.model';
import { RedevableService } from '../service/redevable.service';
import { AuthUtils } from './auth.utils';


@Injectable({providedIn: 'root'})
export class AuthService {
    public _redevable?: Redevable;
    public host = environment.AUTH_URL;
    private _authenticated: boolean = false;
    private _showAlert: boolean = false;


    /**
     * Constructor
     */
    constructor(
        private _httpClient: HttpClient,
        private _redevableService: RedevableService,
    ) {
    }

    // -----------------------------------------------------------------------------------------------------
    // @ Accessors
    // -----------------------------------------------------------------------------------------------------

    get accessToken(): string {
        return localStorage.getItem('accessToken') ?? '';
    }

    /**
     * Setter & getter for access token
     */
    set accessToken(token: string) {
        localStorage.setItem('accessToken', token);
    }

    // -----------------------------------------------------------------------------------------------------
    // @ Public methods
    // -----------------------------------------------------------------------------------------------------

    /**
     * Forgot password
     *
     * @param email
     */
    forgotPassword(email: string): Observable<any> {
        return this._httpClient.post('api/auth/forgot-password', email);
    }

    /**
     * Reset password
     *
     * @param password
     */
    resetPassword(password: string): Observable<any> {
        return this._httpClient.post('api/auth/reset-password', password);
    }

    /**
     * Sign in
     *
     * @param credentials
     */
    signIn(credentials: { email: string; password: string }): Observable<any> {
        // Throw error, if the user is already logged in
        if (this._authenticated) {
            return throwError('User is already logged in.');
        }

        return this._httpClient.post(`${this.host}/api/auth/login`, credentials
            , {observe: 'response'})
            .pipe(
                switchMap((response: any) => {
                    console.log(response)
                    // Store the access token in the local storage
                    this.accessToken = response?.body?.access_token;

                    // Set the authenticated flag to true
                    this._authenticated = true;

                    // Return a new observable with the response
                    return of(response?.body?.role);
                }),
            );
    }


    /**
     * Sign in using the access token
    
    signInUsingToken(): Observable<any> {
        // Sign in using the token
        return this._httpClient.post(`${this.host}/api/auth/sign-in-with-token`, {
            accessToken: this.accessToken,
        }).pipe(
            catchError(() =>
                // Return false
                of(false),
            ),
            switchMap((response: Redevable) => {
                console.log(response)
                // Set the authenticated flag to true
                this._authenticated = true;
                // Store the user on the user service
  
                this._redevableService.user = response;
                this._redevable = response;
                // Return true
                return of(true);
            }),
        );
    }
    */

    /**
     * Sign out
     */
    signOut(): Observable<any> {
        // Remove the access token from the local storage
        localStorage.removeItem('accessToken');

        // Set the authenticated flag to false
        this._authenticated = false;

        // Return the observable
        return of(true);
    }

    /**
     * Sign up
     *
     * @param user
     */
    signUp(redevable: Redevable): Observable<any> {
        return this._httpClient.post(`${this.host}/api/auth/register`, redevable);
    }


    /**
     * Check the authentication status
     */
  /*
    check(): Observable<boolean> {
        // Check if the user is logged in
        if (this._authenticated) {
            return of(true);
        }

        // Check the access token availability
        if (!this.accessToken) {
            return of(false);
        }

        // Check the access token expire date
        if (AuthUtils.isTokenExpired(this.accessToken)) {
            return of(false);
        }

        // If the access token exists, and it didn't expire, sign in using it
        return this.signInUsingToken();
    }
    */
}