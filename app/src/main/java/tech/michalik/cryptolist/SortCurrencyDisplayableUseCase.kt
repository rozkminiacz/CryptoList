package tech.michalik.cryptolist

interface SortCurrencyDisplayableUseCase {
    fun execute(list: List<CurrencyDisplayable>, sortType: SortType): List<CurrencyDisplayable>
}