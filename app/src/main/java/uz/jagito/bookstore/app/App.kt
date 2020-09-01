package uz.jagito.bookstore.app

import android.app.Application
import uz.jagito.bookstore.data.local.sharedpreference.LocalStorage

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        LocalStorage.init(this)
    }

    companion object {
        lateinit var instance: App
    }
}
