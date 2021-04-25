# Qeshta Expenses
Simple management of your expenses and save your money by recording every transaction this transaction include the place, money, and date.

About App
===
- Developed by [Kotlin Programming Language](https://kotlinlang.org/).
- We use [Room Database](https://developer.android.com/training/data-storage/room) that highly recommended by Google.
- Designed according to google guidelines [Material Design](https://material.io/design).
- Is Open Source on [GitHub](https://github.com/iamqeshta/Qeshta-Expenses-App).
- Is Free without Ads on [Google Play Store](https://play.google.com/store/apps/dev?id=5847015618369379078).


Features of App
===
1. Support languages English and Arabic.
2. Support theme Light and Dark.
3. Filtering search by date.
4. Alert notifications.
5. Requires Android 7.0 and up.

# Database Tables
### User
User | Type | Extra
------------ | ------------- | -------------
User id | Int | @PrimaryKey & AUTO INCREMENT
User Name | String | ---
User Mobile | String | ---
User Email | String | ---
User Password | String | ---

### Expense
User | Type | Extra
------------ | ------------- | -------------
Expense id | Int | @PrimaryKey & AUTO INCREMENT
Expense Place | String | ---
Expense Date | Date | ---
Expense Money | Int | ---
User id | Int | @Foreign key

# Libraries we used
```Kotlin
    implementation "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
    implementation 'androidx.core:core-ktx:1.3.2'
    implementation 'androidx.appcompat:appcompat:1.2.0'
    implementation 'com.google.android.material:material:1.3.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.0.4'
    implementation 'androidx.legacy:legacy-support-v4:1.0.0'
    testImplementation 'junit:junit:4.+'
    androidTestImplementation 'androidx.test.ext:junit:1.1.2'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.3.0'
    implementation "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"
    implementation 'com.akexorcist:localization:1.2.9'
    def room_version = "2.3.0"
    implementation "androidx.room:room-runtime:$room_version"
    kapt "androidx.room:room-compiler:$room_version
```
