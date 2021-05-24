[hilt]: https://dagger.dev/hilt/
[base-architecture]: https://github.com/kiketen/jeff-pizza/pull/1
[glide]: https://github.com/bumptech/glide
[room]: https://developer.android.com/jetpack/androidx/releases/room
[navigation]: https://github.com/kiketen/jeff-pizza/pull/3

# Jeff Technical Android Test by Enrique Ten Bru

## Introduction
This repo holds the Jeff technical android test, done by Enrique Ten Bru.

## Architecture
The architecture used in the app is a multi modular MVVM pattern with Clean Architecture.
- There is a main activity who call a navigation graph, which will be the responsible of the navigation between flows. When a feature has more than one fragments, the navigation between them will be placed in the feature navigation graph (as for example in the products feature). [Check this PR to get more info][navigation]
- The dependency injection has been done using [Hilt][hilt], which is a new library based in dagger but so much easier to configure it.
- Using hilt, we declare any Fragment with the annotation @AndroidEntryPoint and the ViewModels with @HiltViewModel.
- The use cases will be inject in the ViewModel scope.
- All the other dependencies will be on the Application scope, such us Room, Retrofit and others.
- To make the asynchronous calls to the use cases, it is being used coroutines.
- It has been created a feature module for each feature, one for the navigation and another for core shared classes.

For an extended explanation of the Clean Architecture layers and flows, check the Pull Request of the [base architecture][base-architecture]

## Testing
The app has been tested using acceptance tests that are mocking the endpoint response and checking that the UI is showing the expected behavior with espresso.
It has been added a unit test to the products resource too.

## Libraries
- [Glide][glide]: Used for load images from an url
- [Room][room]: Used for store data locally
