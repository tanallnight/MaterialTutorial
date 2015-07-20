# MaterialTutorial
An Android library that provides a simple Material-Designed tutorial.
This library is licensed under the Apache License. Check out the LICENSE.txt file for more information.

Video example : [YouTube link](https://youtu.be/WEjwE59k1oY "MaterialTutorial sample on YouTube")

# Features
- Follows the latest Material Design guidelines from Google.
- A very efficient way to create a tutorial. You just need to extend one class!
- Possibility for every user-created fragment to implement the CustomAction interface for providing custom actions in your tutorial (via Uris).
- Powerful built-it Fragment (TutorialFragment) included in the library for easily creating material-styled tutorial slides. The TutorialFragment supports user-defined CustomActions, animated images (AnimatedDrawable) and has 3 different layers for creating a parallax effect in your tutorial!
- A basic but nice PageIndicator you can use outside of the tutorial.

# How to install this project
This project is available on jCentral. To use the project on Android Studio, make sure you have the following in your project's build.gradle file :
```
repositories {
	    jcenter()
	}
```
and compile the project in your module's build.gradle :
```
dependencies {
	        compile 'com.alexandrepiveteau:material-tutorial:1.1.0'
	}
```

# Using the library
A short guide will come soon !

# Apps using the library
- [Zyden 4.0 (coming out soon...)](https://play.google.com/store/apps/details?id=com.zyden.activity "See on Play Store") (by Flax Studios)
- [Card Calculator 2.8](https://play.google.com/store/apps/details?id=com.james.calculator "See on Play Store") (by James Fenn)

I would be pleased to know if your app uses this library. Send me an e-mail if that's the case, I'll add you to the list of apps :)
