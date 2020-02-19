package tech.michalik.cryptolist

import io.kotlintest.IsolationMode
import io.kotlintest.assertSoftly
import io.kotlintest.matchers.collections.shouldBeSortedWith
import io.kotlintest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotlintest.shouldBe
import io.kotlintest.specs.BehaviorSpec
import io.reactivex.subjects.BehaviorSubject
import tech.michalik.cryptolist.network.CurrencyDto
import tech.michalik.cryptolist.screen.CurrencyDtoToDisplayableMapper
import tech.michalik.cryptolist.screen.SortType
import tech.michalik.cryptolist.utilities.RxResult
import tech.michalik.cryptolist.utilities.mapAll
import kotlin.random.Random

class CryptoListViewModelBehaviorSpec : BehaviorSpec({
    Given("data is emited without errors") {

        val mapper =
            CurrencyDtoToDisplayableMapper()

        val dataSourceSubject = BehaviorSubject.create<RxResult<List<CurrencyDto>>>()

        val observeCurrencyStreamUseCase = FakeObserveCurrencyStreamUseCase(
            dataSource = dataSourceSubject
        )

        When("view model starts") {
            val viewModel = createViewModel(
                observeCurrencyStreamUseCase = observeCurrencyStreamUseCase
            )

            dataSourceSubject.onNext(RxResult.Success(fakeData))

            Then("display currency list sorted by default method") {
                assertSoftly {
                    viewModel.currencyList shouldContainExactlyInAnyOrder fakeData.let(mapper::mapAll)
                    viewModel.currencyList shouldBeSortedWith Comparator { o1, o2 ->
                        o1.name.compareTo(o2.name)
                    }
                }
            }
            And("new data comes") {

                val dataWithRandomizedVolume = fakeData.randomizedVolume()
                dataSourceSubject.onNext(RxResult.Success(dataWithRandomizedVolume))

                Then("display new data sorted by default method") {
                    assertSoftly {
                        viewModel.currencyList shouldContainExactlyInAnyOrder dataWithRandomizedVolume.let(
                            mapper::mapAll
                        )
                        viewModel.currencyList shouldBeSortedWith Comparator { o1, o2 ->
                            o1.name.compareTo(o2.name)
                        }
                    }
                }
            }

            And("new data comes and sort type has changed to VOLUME") {
                viewModel.sortType = SortType.VOLUME24

                val dataWithVolumeRandomized = fakeData.randomizedVolume()

                dataSourceSubject.onNext(RxResult.Success(dataWithVolumeRandomized))
                Then("display new data sorted by volume") {
                    assertSoftly {
                        viewModel.currencyList shouldContainExactlyInAnyOrder dataWithVolumeRandomized.let(
                            mapper::mapAll
                        )

                        viewModel.currencyList shouldBeSortedWith Comparator { o1, o2 ->
                            o1.volume.compareTo(o2.volume)
                        }

                    }
                }
            }

            And("sort type has changed to VOLUME") {
                viewModel.sortType = SortType.VOLUME24
                Then("display currency list sorted by volume") {
                    viewModel.currencyList shouldBeSortedWith Comparator { o1, o2 ->
                        o1.volume.compareTo(o2.volume)
                    }
                }
            }

        }
    }

    Given("data is emitted with some errors") {
        val dataSourceSubject = BehaviorSubject.create<RxResult<List<CurrencyDto>>>()

        val observeCurrencyStreamUseCase = FakeObserveCurrencyStreamUseCase(
            dataSource = dataSourceSubject
        )

        When("view model starts") {
            val viewModel = createViewModel(
                observeCurrencyStreamUseCase = observeCurrencyStreamUseCase
            )

            And("error is emitted") {
                dataSourceSubject.onNext(RxResult.Error(Throwable("any error")))
                Then("show error on view") {
                    viewModel.showError shouldBe true
                }
            }

            And("error is emitted first and after that value is emitted") {
                dataSourceSubject.onNext(RxResult.Error(Throwable("any error")))
                Then("show error on view, show data, hide error") {
                    viewModel.showError shouldBe true

                    dataSourceSubject.onNext(RxResult.Success(fakeData))
                    viewModel.showError shouldBe false
                }
            }
        }
    }
}) {
    override fun isolationMode(): IsolationMode? {
        return IsolationMode.InstancePerTest
    }
}

private fun List<CurrencyDto>.randomizedVolume(): List<CurrencyDto> {
    return map {
        it.copy(
            volume = Random.nextDouble()
        )
    }.shuffled()
}
