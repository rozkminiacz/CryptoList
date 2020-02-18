package tech.michalik.cryptolist

import com.nhaarman.mockitokotlin2.*
import io.kotlintest.specs.StringSpec
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import tech.michalik.cryptolist.network.NetworkService
import tech.michalik.cryptolist.network.Wrapper
import java.util.concurrent.TimeUnit

class ObserveCurrencyStreamUseCaseImplTest : StringSpec({
    "it should ask for data every x seconds" {
        val testScheduler = TestScheduler()
        val testSchedulerProvider = TestInteractiveSchedulerProvider(
            testScheduler = testScheduler
        )

        val mockNetworkService = mock<NetworkService> {
            on { getTickers() } doReturn Single.just(Wrapper(emptyList()))
        }

        val useCase = createUseCase(
            networkService = mockNetworkService,
            schedulerProvider = testSchedulerProvider
        )

        useCase.execute(fetchIntervalSeconds = 30)
            .subscribeOn(testScheduler)
            .subscribe()

        testScheduler.triggerActions()

        testScheduler.advanceTimeBy(100, TimeUnit.MILLISECONDS)
        verify(mockNetworkService, times(1)).getTickers()

        testScheduler.advanceTimeBy(30, TimeUnit.SECONDS)

        verify(mockNetworkService, times(2)).getTickers()

        testScheduler.advanceTimeBy(30 * 6, TimeUnit.SECONDS)

        verify(mockNetworkService, times(8)).getTickers()
    }

    "on network call success it should emit list"{
        val testScheduler = TestScheduler()

        val useCase = createUseCase(
            networkService = mock {
                on { getTickers() } doReturn Single.just(Wrapper(fakeData))
            },
            schedulerProvider = TestInteractiveSchedulerProvider(testScheduler)
        )


        val testObserver = useCase.execute(fetchIntervalSeconds = 1)
            .observeOn(testScheduler)
            .test()

        testScheduler.triggerActions()

        testObserver.awaitCount(1)
        testObserver.assertValue {
            it is RxResult.Success
        }
    }

    "on network call success it should emit error result"{
        val testScheduler = TestScheduler()

        val useCase = createUseCase(
            networkService = mock {
                on { getTickers() } doReturn Single.error(Throwable("any throwable"))
            },
            schedulerProvider = TestInteractiveSchedulerProvider(testScheduler)
        )


        val testObserver = useCase.execute(fetchIntervalSeconds = 1)
            .observeOn(testScheduler)
            .test()

        testScheduler.triggerActions()

        testObserver.awaitCount(1)
        testObserver.assertValue {
            it is RxResult.Error
        }
    }

    "it should continue emissions after error"{
        val firstData = fakeData
        val throwable = Throwable("any error")
        val secondData = fakeData.shuffled()

        val testScheduler = TestScheduler()

        val useCase = createUseCase(
            networkService = mock {
                on { getTickers() } doReturnConsecutively listOf(
                    Single.just(Wrapper(firstData)),
                    Single.error(throwable),
                    Single.just(Wrapper(secondData)),
                    Single.never()
                )
            },
            schedulerProvider = TestInteractiveSchedulerProvider(testScheduler)
        )

        val testObserver = useCase.execute(fetchIntervalSeconds = 30)
            .observeOn(testScheduler)
            .test()

        testScheduler.triggerActions()
        testScheduler.advanceTimeBy(30 * 3, TimeUnit.SECONDS)
        testObserver.assertValueCount(3)
        testObserver.assertNoErrors()
    }
})

private fun createUseCase(
    networkService: NetworkService,
    schedulerProvider: SchedulerProvider
) = ObserveCurrencyStreamUseCaseImpl(
    networkService = networkService,
    schedulerProvider = schedulerProvider
)

class TestInteractiveSchedulerProvider(
    private val testScheduler: TestScheduler
) : SchedulerProvider {
    override val main: Scheduler
        get() = testScheduler
    override val io: Scheduler
        get() = testScheduler
    override val single: Scheduler
        get() = testScheduler
}