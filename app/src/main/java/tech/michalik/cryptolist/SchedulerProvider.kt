package tech.michalik.cryptolist

import io.reactivex.Scheduler

interface SchedulerProvider {
    val main: Scheduler
    val io: Scheduler
    val single: Scheduler
}