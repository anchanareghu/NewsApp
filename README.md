# Android News App

The Android News App provides users with access to a wide range of news articles from various sources. It utilizes the News API to fetch news data, Retrofit for making network requests, and Picasso for loading and caching images.

## Features

- **Browse Top Headlines**: View the latest top headlines from around the world.
- **Browse by Category**: Explore news articles categorized by topics such as business, entertainment, health, science, sports, technology, and more.
- **Read Full Articles**: Read the full content of news articles within the app's interface.

## Dependencies
1. **Retrofit**: HTTP client library, to handle network requests and integrate with the News API;
```
 implementation 'com.squareup.retrofit:retrofit:2.10.0'

 implementation 'com.squareup.retrofit:converter-gson:2.10.0'
```
3. **Picasso**: To render images from URLs into ImageViews efficiently.
```

implementation ("com.squareup.picasso:picasso:2.8")

```

5. **News API** : Provides a simple and easy-to-use RESTful API to fetch news data programmatically.

   [https://newsapi.org/](https://newsapi.org/)

## Screenshots
<img src="app/src/main/res/drawable/screenshot_01.png" width="200" /> <img src="app/src/main/res/drawable/screenshot07.png" width="200" /><img src="app/src/main/res/drawable/screenshot03.png" width="200" /> <img src="app/src/main/res/drawable/screenshot02.png" width="200" /> <img src="app/src/main/res/drawable/screenshot06.png" width="200" />
