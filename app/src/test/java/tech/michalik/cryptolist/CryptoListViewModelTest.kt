package tech.michalik.cryptolist

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import io.kotlintest.matchers.collections.shouldHaveSize
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

/**
 * Created by jaroslawmichalik on 17/02/2020
 */
class CryptoListViewModelTest : StringSpec({
    "on start it should ask for data" {
        val dataSource = BehaviorSubject.create<RxResult<List<CurrencyDto>>>()
        createViewModel(
            FakeObserveCurrencyStreamUseCase(dataSource)
        )

        dataSource.hasObservers() shouldBe true
    }

    "on close it should dispose data stream" {
        val dataSource = BehaviorSubject.create<RxResult<List<CurrencyDto>>>()
        val viewModel = createViewModel(
            FakeObserveCurrencyStreamUseCase(dataSource)
        )

        viewModel.close()

        dataSource.hasObservers() shouldBe false
    }

    "initial sort type should be by NAME"{
        val viewModel = createViewModel()

        viewModel.sortType shouldBe SortType.NAME
    }

    "on sort type change to VOLUME24 should sort list with VOLUME24"{
        val unsortedList = listOf(
            CurrencyDisplayable(
                name = "Etherum",
                volume = 25546019818.54097747802734375,
                symbol = "ETH",
                hourChange = 2.15,
                dayChange = -3.13,
                dollarPrice = 250.57
            ),
            CurrencyDisplayable(
                name = "Bitcoin",
                volume = 41412461114.54097747802734375,
                symbol = "BTC",
                hourChange = 2.15,
                dayChange = -3.13,
                dollarPrice = 250.57
            )
        )

        val listSortedByVolume = listOf(
            CurrencyDisplayable(
                name = "Bitcoin",
                volume = 41412461114.54097747802734375,
                symbol = "BTC",
                hourChange = 2.15,
                dayChange = -3.13,
                dollarPrice = 250.57
            ),
            CurrencyDisplayable(
                name = "Etherum",
                volume = 25546019818.54097747802734375,
                symbol = "ETH",
                hourChange = 2.15,
                dayChange = -3.13,
                dollarPrice = 250.57
            )
        )

        val viewModel = createViewModel(
            sortCurrencyDisplayableUseCase = mock {
                on { execute(eq(unsortedList), eq(SortType.VOLUME24)) } doReturn listSortedByVolume
            }
        )

        viewModel.currencyList = unsortedList

        viewModel.sortType = SortType.VOLUME24

        viewModel.currencyList shouldBe listSortedByVolume
    }

    "given currency stream emits data when viewmodel starts then set data on view"{
        val dtoList: List<CurrencyDto> = fakeData

        val subject = BehaviorSubject.create<RxResult<List<CurrencyDto>>>()

        val observeCurrencyStreamUseCase = FakeObserveCurrencyStreamUseCase(
            dataSource = subject
        )

        val viewModel = createViewModel(
            observeCurrencyStreamUseCase = observeCurrencyStreamUseCase
        )

        require(viewModel.currencyList.isEmpty())

        subject.onNext(RxResult.Success(dtoList))

        viewModel.currencyList shouldHaveSize dtoList.size
    }
})

fun createViewModel(
    observeCurrencyStreamUseCase: ObserveCurrencyStreamUseCase = FakeObserveCurrencyStreamUseCase(),
    sortCurrencyDisplayableUseCase: SortCurrencyDisplayableUseCase = SortCurrencyDisplayableUseCaseImpl()
) = CryptoListViewModel(
    observeCurrencyStreamUseCase = observeCurrencyStreamUseCase,
    dtoToDisplayableMapper = DtoToDisplayableMapper(),
    sortCurrencyDisplayableUseCase = sortCurrencyDisplayableUseCase,
    schedulerProvider = TestSchedulerProviderTrampoline()
)

class TestSchedulerProviderTrampoline : SchedulerProvider {
    override val main: Scheduler
        get() = Schedulers.trampoline()
    override val io: Scheduler
        get() = Schedulers.trampoline()
    override val single: Scheduler
        get() = Schedulers.trampoline()
}