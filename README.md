# Crypto List

Proof of concept for an Android application using [rozkminiacz/MVX](https://github.com/rozkminiacz/MVX) microframework with databinding.

Application is consuming API: https://api.coinlore.com/api/ with request every 30 seconds.

Projects is not using any specific clean architecture approach, but instead tries to follow SOLID principles when it's convenient.

Custom views are extending base and support view classes.

I am not using Dagger or any dependency injection framework - dependencies are provided to classes manually.

Databinding is used in each where view properties must be modified.

Tests are created with KotlinTest and Mockito with Kotlin extensions. There are some unit tests, there are some functional tests.

![Demo GIF](demo.gif)
