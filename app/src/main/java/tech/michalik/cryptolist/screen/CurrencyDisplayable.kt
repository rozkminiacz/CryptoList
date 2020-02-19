package tech.michalik.cryptolist.screen

data class CurrencyDisplayable(
    val name: String,
    val symbol: String,
    val dollarPrice: Double,
    val hourChange: Double,
    val dayChange: Double,
    val volume: Double
)