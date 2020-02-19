package tech.michalik.cryptolist.screen

import tech.michalik.cryptolist.screen.CurrencyDisplayable
import tech.michalik.cryptolist.screen.SortType

interface SortCurrencyDisplayableUseCase {
    fun execute(list: List<CurrencyDisplayable>, sortType: SortType): List<CurrencyDisplayable>
}