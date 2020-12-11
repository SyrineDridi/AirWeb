package fr.airweb.airwebtest.utils

import android.app.Application
import fr.airweb.airwebtest.api.di.apiModule
import fr.airweb.airwebtest.data.di.dataModule
import fr.airweb.airwebtest.di.appModule
import fr.airweb.airwebtest.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin

class AirWebApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        if (GlobalContext.getOrNull() == null) { // Used to prevent Robolectric to startKoin multiple times
            startKoin {
                androidContext(this@AirWebApplication)
                androidFileProperties()
                modules(listOf(appModule, dataModule, domainModule, apiModule))
            }
        }
    }
}