package tech.michalik.cryptolist

import androidx.databinding.Bindable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import org.joda.time.DateTime
import tech.michalik.mvx.BaseViewModel
import tech.michalik.mvx.observable
import tech.michalik.mvx.onChange
import timber.log.Timber
import java.util.*

/**
 * Created by jaroslawmichalik on 17/02/2020
 */

class CryptoListViewModel(
    observeCurrencyStreamUseCase: ObserveCurrencyStreamUseCase,
    private val sortCurrencyDisplayableUseCase: SortCurrencyDisplayableUseCase,
    private val dtoToDisplayableMapper: Mapper<CurrencyDto, CurrencyDisplayable>,
    schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    @get:Bindable
    var currencyList: List<CurrencyDisplayable> by observable(
        initialValue = emptyList(),
        br = BR.currencyList
    )

    @get:Bindable
    var sortType: SortType by observable(
        initialValue = SortType.NAME,
        br = BR.sortType
    )

    @get:Bindable
    var showError: Boolean by observable(
        initialValue = false,
        br = BR.showError
    )

    @get:Bindable
    var lastUpdateTimestamp: DateTime by observable(
        initialValue = DateTime.now(),
        br = BR.lastUpdateTimestamp
    )

    @get:Bindable
    var progressBarVisible: Boolean by observable(
        initialValue = true,
        br = BR.progressBarVisible
    )

    val availableSortTypes = SortType.values().toList()

    private val disposableContainer = CompositeDisposable()

    init {
        onChange(BR.sortType) {
            currencyList = sortCurrencyDisplayableUseCase.execute(currencyList, sortType)
        }

        observeCurrencyStreamUseCase.execute(fetchIntervalSeconds = 30)
            .subscribeOn(schedulerProvider.io)
            .observeOn(schedulerProvider.main)
            .subscribeBy(
                onError = {
                    Timber.e(it)
                },
                onNext = {
                    when (it) {
                        is RxResult.Success -> {
                            val currencies = dtoToDisplayableMapper
                                .mapAll(it.value)
                                .let { list ->
                                    sortCurrencyDisplayableUseCase.execute(list, sortType)
                                }
                            this.currencyList = currencies
                            this.showError = false
                            this.lastUpdateTimestamp = DateTime.now()
                            this.progressBarVisible = false
                        }
                        is RxResult.Error -> {
                            this.showError = true
                        }
                    }
                }
            ).addTo(disposableContainer)
    }

    override fun close() {
        disposableContainer.dispose()
        super.close()
    }
}