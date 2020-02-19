package tech.michalik.cryptolist.utilities

import io.reactivex.Scheduler

interface SchedulerProvider {
    val main: Scheduler
    val io: Scheduler
    val single: Scheduler
}