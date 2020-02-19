package tech.michalik.cryptolist.usecase

import io.reactivex.Flowable
import tech.michalik.cryptolist.network.CurrencyDto
import tech.michalik.cryptolist.utilities.RxResult

interface ObserveCurrencyStreamUseCase {
    fun execute(fetchIntervalSeconds: Int): Flowable<RxResult<List<CurrencyDto>>>
}