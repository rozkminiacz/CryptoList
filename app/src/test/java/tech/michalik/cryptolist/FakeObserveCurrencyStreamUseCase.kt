package tech.michalik.cryptolist

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject

class FakeObserveCurrencyStreamUseCase(
    private val dataSource: Subject<RxResult<List<CurrencyDto>>> = BehaviorSubject.create()
) : ObserveCurrencyStreamUseCase {

    override fun execute(fetchIntervalSeconds: Int): Flowable<RxResult<List<CurrencyDto>>> {
        return dataSource.toFlowable(BackpressureStrategy.BUFFER)
    }
}