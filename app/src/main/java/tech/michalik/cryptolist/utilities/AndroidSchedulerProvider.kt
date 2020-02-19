package tech.michalik.cryptolist.utilities

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import tech.michalik.cryptolist.utilities.SchedulerProvider

class AndroidSchedulerProvider :
    SchedulerProvider {
    override val main: Scheduler
        get() = AndroidSchedulers.mainThread()
    override val io: Scheduler
        get() = Schedulers.io()
    override val single: Scheduler
        get() = Schedulers.single()
}