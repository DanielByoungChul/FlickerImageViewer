# FlickerImageViewer
FlickerImageViewer App is an Android application that shows a list of images from Flickr in a 3-column scrollable view.


## Architecture

```
MVVM (ModelView ViewModel)
  - Fragment
  - ViewModel
  - Repository
  - Remote Data Source (Retrofit)

```

***MainFragment*** shows a list of ***photos*** fetched from REST API


For MVVM, the following classes are linked :
```
MainActivity <--> MainViewModel <--> MainRepository (Use CoroutineScope to run API service in the background thread)

```

## Dependencies

Retrofit
```
    implementation 'com.squareup.retrofit2:retrofit:2.6.2'
```

Retrofit Gson Converter
```
    implementation 'com.squareup.retrofit2:converter-gson:2.6.2'
```

RecyclerView
```
    implementation 'androidx.recyclerview:recyclerview:1.0.0'

```

Glide
```
    implementation 'com.github.bumptech.glide:glide:4.10.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.10.0'
```
    
    

