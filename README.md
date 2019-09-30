dino-library

### project settings.gradle
```
include ':app'
include ':dino-library'
```

### project build.gradle
```
// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    apply from: 'dino-library/versions.gradle'
    repositories {
        google()
        jcenter()

    }
    dependencies {
        classpath deps.classpath.gradle
        classpath deps.classpath.kotlin
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()

    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}
```

### app module build.gradle
```
apply plugin: 'com.android.application'

apply plugin: 'kotlin-android'

apply plugin: 'kotlin-android-extensions'

apply plugin: 'kotlin-kapt'

android {
    compileSdkVersion versions.compile_sdk_version
    buildToolsVersion versions.build_tool_version
    defaultConfig {
        applicationId $application_id$
        minSdkVersion versions.min_sdk_version
        targetSdkVersion versions.target_sdk_version
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }

    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }

    dataBinding {
        enabled = true
    }
}

dependencies {
    api project(":dino-library")
}
```