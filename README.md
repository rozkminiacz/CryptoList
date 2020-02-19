# Crypto List

Proof of concept for an Android application using [rozkminiacz/MVX](https://github.com/rozkminiacz/MVX) microframework with databinding.

Application is consuming API: https://api.coinlore.com/api/ with request every 30 seconds.

Projects is not using any specific clean architecture approach, but isntead tries to follow SOLID principles where it is convinient.

Custom views are extending some base and support view classes.

I am not using Dagger or any dependency injection framework - dependencies are provided to classes manually.

Databinding is used in each where view properties must be modified.

Tests are created with KotlinTest, Mockito-Kotlin. 

![Demo GIF](demo.gif)
