![Banner Image](/app/src/main/res/drawable/mainactivity.jpg)
# Whats-In-The-Fridge
 [![standard-readme compliant](https://img.shields.io/badge/readme%20style-standard-brightgreen.svg)](https://github.com/RichardLitt/standard-readme)
![Dependencies](https://img.shields.io/badge/dependencies-up%20to%20date-brightgreen.svg)

## Short Description
> Epicodus Android Project which allows user to find recipes based on the ingredients in their fridge.

## Long Description
Every find yourself standing in front of the refrigerator wondering what you could make from the ingredients you have? Well this is the app for you. Simply enter 1-5 ingredients you have on hand and a list of recipes with images will appear. Don't quite have all the ingredients and need to run to the market? No problem, this app captures a shopping list for you as well.  

## Table of Contents
- [Sections](#sections)
    - [Install](#install)
    - [Usage](#usage)
    - [Screen Shots](#Screen-Shots)
    - [Demo](#Demo)
    - [Database](#database)
    - [Known Bugs](#known-bugs)
    - [Sources](#sources)
    - [Maintainer](#maintainer)
    - [Contribute](#contribute)
    - [License](#license)
    

***

## Install
**All installation instructions are for macOS**

1. You first will need the following things properly installed on your computer.
    * [Git](https://git-scm.com/)
    * [Android Studio](https://developer.android.com/studio/install.html)
2. With the above items installed you may go to your terminal and `git clone https://github.com/gstuart/AlwaysForTheBetter-Andriod`
3. Import project into Android Studio


## Usage
*  In Android Studio, `Run` project

### Screen Shots
Coming Soon.

### Demo
Coming Soon.

### Database
1. Create account on [Firebase](https://firebase.google.com/) and follow the instruction for creating a remote database.
2. Create `src/app/api-keys.ts` file. Add the following code, substituting the `xxxx` with your specific Firebase credentials.
```
export var masterFirebaseConfig = {
    apiKey: "xxxx",
    authDomain: "xxxx.firebaseapp.com",
    databaseURL: "https://xxxx.firebaseio.com",
    storageBucket: "xxxx.appspot.com",
    messagingSenderId: "xxxx"
  };
```

## Known Bugs
* :bug: = S1
* :ant: = S2
* :beetle: = S3

> 
>
> 
>
> 

## Sources
 * [Home activity image](https://unsplash.com/search/refrigerator)
 * [Api](http://www.recipepuppy.com/about/api/)


## Maintainer
[Grace Stuart](href="https://github.com/gstuart")


## Contribute
* Questions may be submitted to gstuart.:octocat:@gmail.com.
* Pull requests accepted.
* NOTE: If editing the Readme, please conform to the [standard-readme specification](https://github.com/RichardLitt/standard-readme/blob/master/spec.md).


## License
**_[MLP License](/LICENSE.md)_** :copyright: **2017 Grace Stuart**


***

**[:top:](#whats-in-the-fridge)**

