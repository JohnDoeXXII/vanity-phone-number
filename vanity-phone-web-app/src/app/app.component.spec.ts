import { TestBed, async } from '@angular/core/testing';
import { AppComponent } from './app.component';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { VanityPhoneNumberService } from './services/vanity-phone-number.service';
import { SpawnSyncOptions } from 'child_process';
import { of } from 'rxjs';
import { ITS_JUST_ANGULAR } from '@angular/core/src/r3_symbols';

describe('AppComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientModule],
      providers: [
        VanityPhoneNumberService,
      ],
      declarations: [
        AppComponent
      ],
    }).compileComponents();
  }));

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`Assign defatuls correctly`, () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app.title).toEqual('vanity-phone-web-app');
    expect(app.numbers).toEqual([]);
    expect(app.showWarningMessage).toBeFalse();
    expect(app.userEnteredPhoneNumber).toEqual('7732025862');
  });

  describe('tests with mocked service', () => {
    const mockResponseNumbers = ['18005882300EMPIRE', '8675309'];
    const mockResponseCount = 620;
    let appComponent: AppComponent;
    let newNumberSpy: jasmine.Spy;
    let getCount: jasmine.Spy;

    beforeEach(() => {
      newNumberSpy = spyOn(TestBed.get(VanityPhoneNumberService), 'requestNumbers').and.returnValue(of(mockResponseNumbers));
      getCount = spyOn(TestBed.get(VanityPhoneNumberService), 'getTotalCombinations').and.returnValue(of(mockResponseCount));
      const fixture = TestBed.createComponent(AppComponent);
      appComponent = fixture.componentInstance;
    });

    describe('submitNewNumberIfValid', () => {
      describe('when newNumber is not valid', () => {
        beforeEach(() => {
          appComponent.userEnteredPhoneNumber = 'a';
          appComponent.submitNewNumberIfValid();
          appComponent.numbers = ['Don\'t clear me please'];
          appComponent.totalCount = 49;
        });

        it('does not call for new numbers', () => expect(newNumberSpy).not.toHaveBeenCalled());
        it('does not call for new number of combinations', () => expect(getCount).not.toHaveBeenCalled());
        it('flips the warning message on', () => expect(appComponent.showWarningMessage).toBeTrue());
        it('does not clear the numbers', () => expect(appComponent.numbers).toEqual(['Don\'t clear me please']))
        it('does not clear the numbers', () => expect(appComponent.totalCount).toEqual(49));
      });

      describe('when newNumber is valid', () => {
        const testNumber = '8675309';

        beforeEach(() => {
          appComponent.userEnteredPhoneNumber = testNumber;
          appComponent.submitNewNumberIfValid();
        });

        it('does call for new numbers with input number', () => expect(newNumberSpy).toHaveBeenCalledWith(testNumber));
        it('does call for new number of combinations with input number', () => expect(getCount).toHaveBeenCalledWith(testNumber));
        it('flips the warning message off', () => expect(appComponent.showWarningMessage).toBeFalse());
        it('assigns the returned numbers', () => expect(appComponent.numbers).toEqual(mockResponseNumbers));
        it('assigns the count', () => expect(appComponent.totalCount).toEqual(mockResponseCount));
      });
    });

    describe('getNextResults', () => {

      it('uses latest number to fetch next', () => {
        appComponent.numbers = ['867530W', '867530X', '867530Y', '867530Z'];
        appComponent.getNextResults();
        expect(newNumberSpy).toHaveBeenCalledWith('867530Z');
      })

    });
  });
});
