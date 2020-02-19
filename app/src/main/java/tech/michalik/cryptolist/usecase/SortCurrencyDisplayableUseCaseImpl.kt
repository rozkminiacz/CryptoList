package tech.michalik.cryptolist.usecase

import tech.michalik.cryptolist.screen.CurrencyDisplayable
import tech.michalik.cryptolist.screen.SortType
import tech.michalik.cryptolist.screen.SortType.*

class SortCurrencyDisplayableUseCaseImpl :
    SortCurrencyDisplayableUseCase {
    override fun execute(
        list: List<CurrencyDisplayable>,
        sortType: SortType
    ): List<CurrencyDisplayable> {
        return list.sortedWith(Comparator { o1, o2 ->
            when (sortType) {
                NAME -> o1.name.compareTo(o2.name)
                VOLUME24 -> o1.volume.compareTo(o2.volume)
                PERCENT_CHANGE_24 -> o1.dayChange.compareTo(o2.dayChange)
            }
        })
    }
}