package tech.michalik.cryptolist.usecase

import tech.michalik.cryptolist.screen.CurrencyDisplayable
import tech.michalik.cryptolist.screen.SortType

interface SortCurrencyDisplayableUseCase {
    fun execute(list: List<CurrencyDisplayable>, sortType: SortType): List<CurrencyDisplayable>
}