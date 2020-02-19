package tech.michalik.cryptolist.screen

import tech.michalik.cryptolist.network.CurrencyDto
import tech.michalik.cryptolist.utilities.Mapper

class CurrencyDtoToDisplayableMapper :
    Mapper<CurrencyDto, CurrencyDisplayable> {
    override fun map(from: CurrencyDto): CurrencyDisplayable {
        return CurrencyDisplayable(
            name = from.name,
            symbol = from.symbol,
            dollarPrice = from.priceUsd.toDouble(),
            hourChange = from.percentChange1h.toDouble(),
            dayChange = from.percentChange24h.toDouble(),
            volume = from.volume
        )
    }
}