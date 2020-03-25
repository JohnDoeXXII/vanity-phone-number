import { TestBed } from '@angular/core/testing';

import { VanityPhoneNumberService } from './vanity-phone-number.service';
import { HttpClient, HttpClientModule } from '@angular/common/http';

describe('VanityPhoneNumberService', () => {
  let service: VanityPhoneNumberService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientModule],
      providers: [
        HttpClient,
      ]
    });
    service = TestBed.inject(VanityPhoneNumberService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('requestNumbers should make a the correct call', () => {
    const spy = spyOn(TestBed.get(HttpClient), 'get');
    service.requestNumbers('abc');
    expect(spy).toHaveBeenCalledWith(`/api/vanity-number?phone=abc&amount=10`)
  });

  it('getTotalCombinations should make a the correct call', () => {
    const spy = spyOn(TestBed.get(HttpClient), 'get');
    service.getTotalCombinations('abc');
    expect(spy).toHaveBeenCalledWith(`/api/vanity-number/count?phone=abc`)
  });
});
