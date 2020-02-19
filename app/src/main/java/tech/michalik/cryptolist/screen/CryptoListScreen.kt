package tech.michalik.cryptolist.screen

import tech.michalik.cryptolist.BR
import tech.michalik.cryptolist.R
import tech.michalik.cryptolist.databinding.CryptoListScreenBinding
import tech.michalik.cryptolist.network.NetworkModule
import tech.michalik.cryptolist.usecase.ObserveCurrencyStreamUseCaseImpl
import tech.michalik.cryptolist.usecase.SortCurrencyDisplayableUseCaseImpl
import tech.michalik.cryptolist.utilities.AndroidSchedulerProvider
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
                dtoToDisplayableMapper = CurrencyDtoToDisplayableMapper()
            )
        })
    }
}