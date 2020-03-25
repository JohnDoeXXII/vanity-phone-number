# vanity-phone-number
A small Angular/SpringBoot application which can generate all possible combination of AlphaNumeric strings from an input of the found characters on a touch tone phone.

To setup the dependencies for the Angular UI please run `npm install` from `./vanity-phone-web-app`
To run the Angular UI please run `npm start` from `./vanity-phone-web-app`
  * npm start has been given additional parameters to launch the UI with a proxy server for development

To run the SpringBoot Rest Service just run VanityPhoneAssessmentApplication as JavaApp or run as `mvn spring-boot:run` 
  * Also make sure to Junit4 to build path

# Notes from Applicant:
	Hey-o thanks for taking the time to peruse the README.
	This implementation relied on the SpringBootInitializer and the AngularCLI to do all the project setup
	I've forgone styling of the UI pieces, long lists of PhoneNumbers are no fun to style
		If I had the time I'd prefer to remove all `Get More Numbers` buttons and replace the functionality
		with a scrollbar which triggers next pull when the scroll cursor reaches a position on the bar for an
		infinite result style of smooth pagination.
		
	Known Issues:
		There can be duplicate PhoneNumbers displayed as a tail condition before the Get More Numbers get disabled.
