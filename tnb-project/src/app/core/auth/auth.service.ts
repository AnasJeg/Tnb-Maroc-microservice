import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable, of, switchMap, throwError} from 'rxjs';
import {environment} from "../../../environments/environment";
import { Redevable } from '../models/redevable.model';
import { RedevableService } from '../service/redevable.service';


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

    
    
    

    get accessToken(): string {
        return localStorage.getItem('accessToken') ?? '';
    }

    /**
     * Setter & getter for access token
     */
    set accessToken(token: string) {
        localStorage.setItem('accessToken', token);
    }

    
    
    

    /**
     * Forgot password
     *
     * @param cin
     */
    forgotPassword(cin: string): Observable<any> {
        return this._httpClient.post('api/auth/forgot-password', cin);
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
    
    signIn(credentials: { cin: string; password: string }): Observable<any> {
        
        if (this._authenticated) {
            return throwError('User is already logged in.');
        }

        return this._httpClient.post(`${this.host}/api/auth/login`, credentials
            , {observe: 'response'})
            .pipe(
                switchMap((response: any) => {
                    console.log(response)
                    
                    this.accessToken = response?.body?.accessToken;
                    console.log("log token", response?.body?.accessToken);
                    
                    
                    this._authenticated = true;

                    
                    return of(response?.body?.role);
                }),
            );
    }

    

    signOut(): Observable<any> {
        
        localStorage.removeItem('accessToken');

        
        this._authenticated = false;

        
        return of(true);
    }


    signUp(redevable: Redevable): Observable<any> {
        return this._httpClient.post(`${this.host}/api/auth/register`, redevable);
    }

}