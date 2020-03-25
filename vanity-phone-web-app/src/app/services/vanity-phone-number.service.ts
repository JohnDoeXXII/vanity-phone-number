import { Injectable } from '@angular/core';
import { Observable, ReplaySubject, Subject, Subscription } from 'rxjs';

import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class VanityPhoneNumberService {

  private readonly BASE_URL = "/api/vanity-number";
  private readonly FETCH_AMOUNT = 10;

  constructor(private http: HttpClient) { }

  requestNumbers(phoneNumber: string): Observable<string[]> {
    return this.http.get(`${this.BASE_URL}?phone=${phoneNumber}&amount=${this.FETCH_AMOUNT}`) as Observable<string[]>;
  }

  getTotalCombinations(phoneNumber: string): Observable<number> {
    return this.http.get(`${this.BASE_URL}/count?phone=${phoneNumber}`) as Observable<number>;
  }
}
