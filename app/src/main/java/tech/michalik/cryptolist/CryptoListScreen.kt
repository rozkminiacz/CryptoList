package tech.michalik.cryptolist

import androidx.lifecycle.ViewModel
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import tech.michalik.cryptolist.databinding.CryptoListScreenBinding
import tech.michalik.cryptolist.network.NetworkModule
import tech.michalik.mvx.GenericViewModelProviderFactory
import tech.michalik.mvx.MvvmActivity
import javax.inject.Provider

/**
 * Created by jaroslawmichalik on 18/02/2020
 */
class CryptoListScreen : MvvmActivity<CryptoListViewModel, CryptoListScreenBinding>(
    layoutId = R.layout.crypto_list_screen,
    viewModelBr = BR.viewModel,
    vmClass = CryptoListViewModel::class.java

) {
    override fun inject() {
        viewModelProvider = GenericViewModelProviderFactory(Provider {
            val schedulerProvider = AndroidSchedulerProvider()
            CryptoListViewModel(
                observeCurrencyStreamUseCase = ObserveCurrencyStreamUseCaseImpl(
                    networkService = NetworkModule().networkService,
                    schedulerProvider = schedulerProvider
                ),
                schedulerProvider = schedulerProvider,
                sortCurrencyDisplayableUseCase = SortCurrencyDisplayableUseCaseImpl(),
                dtoToDisplayableMapper = DtoToDisplayableMapper()
            )
        })
    }
}

class AndroidSchedulerProvider : SchedulerProvider {
    override val main: Scheduler
        get() = AndroidSchedulers.mainThread()
    override val io: Scheduler
        get() = Schedulers.io()
    override val single: Scheduler
        get() = Schedulers.single()
}