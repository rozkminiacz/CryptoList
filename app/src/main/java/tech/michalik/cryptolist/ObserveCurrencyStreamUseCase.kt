package tech.michalik.cryptolist

import io.reactivex.Flowable

interface ObserveCurrencyStreamUseCase {
    fun execute(fetchIntervalSeconds: Int): Flowable<RxResult<List<CurrencyDto>>>
}