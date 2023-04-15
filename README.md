# Android App - Coffee Sample

This is an Android application that retrieves a list of hot drinks from an API and displays them in a list view. The application also caches the drinks for offline access in case of internet connectivity issues. Users can click on a drink to see more details, including a picture, the name, the description, and the ingredient list.

## Features
1. Displays a list of hot drinks retrieved from an API.
2. Caches the drinks for offline access.
3. Users can click on a drink to see more details.
4. Detail page includes a picture, name, description, and ingredient list.
5. Detail page includes CTAs for liking the drink and writing a review.
6. Review page includes input fields for name and text, a datepicker for when the drink was consumed, and a list of checkboxes for things the user liked about the drink.
7. Review page includes a button to send the review to an API.

## Technologies used
1. MVVM architecture pattern
2. Jetpack Compose for UI design
3. Room DB for local caching
4. Retrofit for API calls
5. Dagger Hilt for dependency injection
6. Glide for image loading

## Unit tests
Some unit tests have been added for the DrinkRepositoryImpl and can be found under ```app/src/test/java```
Because of time constraints not all unit tests are implemented.

## Installation
1. Clone the repository: ```git clone https://github.com/dm4rco/coffee_sample.git```
2. Open the project in Android Studio.
3. Build and run the application on an emulator or a physical device.
