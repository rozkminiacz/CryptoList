package tech.michalik.cryptolist

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject
import tech.michalik.cryptolist.network.CurrencyDto
import tech.michalik.cryptolist.usecase.ObserveCurrencyStreamUseCase
import tech.michalik.cryptolist.utilities.RxResult

class FakeObserveCurrencyStreamUseCase(
    private val dataSource: Subject<RxResult<List<CurrencyDto>>> = BehaviorSubject.create()
) : ObserveCurrencyStreamUseCase {

    override fun execute(fetchIntervalSeconds: Int): Flowable<RxResult<List<CurrencyDto>>> {
        return dataSource.toFlowable(BackpressureStrategy.BUFFER)
    }
}