import { Component } from '@angular/core';
import { VanityPhoneNumberService } from './services/vanity-phone-number.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.less']
})
export class AppComponent {
  private readonly sevenOrTenDigits = /^(?:\d{7}|\d{10})$/;
  title = 'vanity-phone-web-app';

  userEnteredPhoneNumber = '7732025862';
  showWarningMessage = false;
  totalCount: number;

  constructor(private vanityPhonenumberService: VanityPhoneNumberService) { }

  private numberSubscription: Subscription;
  private countSubscription: Subscription;
  numbers: string[] = [];

  submitNewNumberIfValid(): void {
    if (this.sevenOrTenDigits.test(this.userEnteredPhoneNumber)) {
      this.showWarningMessage = false;
      this.submitNewNumber();
    } else {
      this.showWarningMessage = true;
    }
  }

  getNextResults(): void {
    if (this.numberSubscription) {
      this.numberSubscription.unsubscribe();
    }
    const lastPhoneNumber: string = this.getLastPhoneNumber();
    this.numberSubscription = this.vanityPhonenumberService.requestNumbers(lastPhoneNumber)
      .subscribe((newNumbers: string[]) => this.numbers.push(...newNumbers));
  }

  private submitNewNumber(): void {
    this.numbers = [];
    this.getNextResults();
    if (this.countSubscription) {
      this.countSubscription.unsubscribe();
    }
    this.countSubscription = this.vanityPhonenumberService.getTotalCombinations(this.userEnteredPhoneNumber)
      .subscribe((newCount: number) => this.totalCount = newCount);
  }

  private getLastPhoneNumber(): string {
    return this.numbers.length == 0 ? this.userEnteredPhoneNumber : this.numbers[this.numbers.length - 1];
  }
}
