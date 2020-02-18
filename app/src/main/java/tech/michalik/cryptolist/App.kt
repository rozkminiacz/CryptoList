package tech.michalik.cryptolist

import android.app.Application
import androidx.databinding.DataBindingUtil
import timber.log.Timber

/**
 * Created by jaroslawmichalik on 17/02/2020
 */
class App: Application(){
    override fun onCreate() {
        Timber.plant(Timber.DebugTree())
        DataBindingUtil.setDefaultComponent(BindingAdaptersComponent())
        super.onCreate()
    }
}