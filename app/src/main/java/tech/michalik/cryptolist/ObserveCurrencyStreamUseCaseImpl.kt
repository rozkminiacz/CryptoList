package tech.michalik.cryptolist

import io.reactivex.Flowable
import tech.michalik.cryptolist.network.NetworkService
import java.util.concurrent.TimeUnit

class ObserveCurrencyStreamUseCaseImpl(
    private val networkService: NetworkService,
    private val schedulerProvider: SchedulerProvider
) : ObserveCurrencyStreamUseCase {

    override fun execute(fetchIntervalSeconds: Int): Flowable<RxResult<List<CurrencyDto>>> {
        return Flowable.interval(
            0,
            fetchIntervalSeconds.toLong(),
            TimeUnit.SECONDS,
            schedulerProvider.single
        ).flatMap {
            networkService.getTickers()
                .map { RxResult.Success(it.data) as RxResult<List<CurrencyDto>> }
                .onErrorReturn { RxResult.Error(it) }
                .toFlowable()
        }
    }
}