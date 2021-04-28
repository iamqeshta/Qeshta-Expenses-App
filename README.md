# Qeshta Expenses (Android App)
## Simple management of your expenses and save your money by recording every transaction this transaction include the place, money, and date.


![0](https://github.com/iamqeshta/Qeshta-Expenses-App/blob/master/res/0.png)

About App
===
- Developed by [Kotlin Programming Language](https://kotlinlang.org/).
- We use [Room Database](https://developer.android.com/training/data-storage/room) that highly recommended by Google.
- Designed according to google guidelines [Material Design](https://material.io/design).
- Is Open Source on [GitHub](https://github.com/iamqeshta/Qeshta-Expenses-App).
- Is Free without Ads on [Google Play Store](https://play.google.com/store/apps/details?id=com.iamqeshta.qeshtaexpensesapp).


Features of App
===
1. Support languages English and Arabic.
2. Support theme Light and Dark.
3. Filtering search by date.
4. Alert notifications.
5. Requires Android 7.0 and up.

Tools
===
- [Android Studio](https://developer.android.com/studio) for Development.
- [GitHub Desktop](https://desktop.github.com) for simplifies development workflow.
- [Adobe Xd](https://www.adobe.com/products/xd.html) for UI/UX Design .
- [Trello](https://trello.com/en) for manage project.

# Hint
## We adopted the [SOLID](https://en.wikipedia.org/wiki/SOLID) principles specifically the [Single-responsibility principle](https://en.wikipedia.org/wiki/Single-responsibility_principle) in coding.

## This app includes a lot of topics in Android :
### For example, but not limited
- [Material Components](https://material.io/components?platform=android).
- [Styles and Themes - Dark theme](https://developer.android.com/guide/topics/ui/look-and-feel/themes).
- [Fragments](https://developer.android.com/guide/fragments).
- [Intent](https://developer.android.com/reference/android/content/Intent).
- [Notifications](https://developer.android.com/guide/topics/ui/notifiers/notifications)

# Design Patterns we used
## Singleton Pattern with Database
```Kotlin
class DatabaseClient(mContext: Context) {
    val appDatabase: AppDatabase =
        Room.databaseBuilder(mContext, AppDatabase::class.java, "Qeshta_Expenses")
            .allowMainThreadQueries().build()

    companion object {
        private var mInstance: DatabaseClient? = null

        @Synchronized
        fun getInstance(context: Context?): DatabaseClient? {
            if (mInstance == null) {
                mInstance = DatabaseClient(context!!)
            }
            return mInstance!!
        }
    }
}
```
## ViewHolder Pattern with RecyclerView
```Kotlin
class ExpenseAdapter(...) :
    RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    inner class ExpenseViewHolder(var binding: RvExpenseRowBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val binding = RvExpenseRowBinding.inflate(LayoutInflater.from(context), parent, false)
        return ExpenseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {...}

    override fun getItemCount(): Int {
        return ExpensesList.size
    }
}
```

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
Expense | Type | Extra
------------ | ------------- | -------------
Expense id | Int | @PrimaryKey & AUTO INCREMENT
Expense Place | String | ---
Expense Date | Date | ---
Expense Money | Int | ---
User id | Int | @Foreign key

# Library we use for support languages
```Kotlin
    implementation 'com.akexorcist:localization:1.2.9'
```
[Localization Library](https://github.com/akexorcist/Localization)
> I will be very happy. If the app is translated into more than one language.

# App Design - [Behance](https://www.behance.net/gallery/117676351/Qeshta-Expenses-App)
![1](https://github.com/iamqeshta/Qeshta-Expenses-App/blob/master/res/1.jpg)
![2](https://github.com/iamqeshta/Qeshta-Expenses-App/blob/master/res/2.jpg)
![3](https://github.com/iamqeshta/Qeshta-Expenses-App/blob/master/res/3.jpg)
![4](https://github.com/iamqeshta/Qeshta-Expenses-App/blob/master/res/4.jpg)
![5](https://github.com/iamqeshta/Qeshta-Expenses-App/blob/master/res/5.jpg)
![6](https://github.com/iamqeshta/Qeshta-Expenses-App/blob/master/res/6.jpg)
![7](https://github.com/iamqeshta/Qeshta-Expenses-App/blob/master/res/7.jpg)
![8](https://github.com/iamqeshta/Qeshta-Expenses-App/blob/master/res/8.jpg)
![9](https://github.com/iamqeshta/Qeshta-Expenses-App/blob/master/res/9.jpg)
