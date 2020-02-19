package tech.michalik.cryptolist

import tech.michalik.cryptolist.network.CurrencyDto

/**
 * Created by jaroslawmichalik on 17/02/2020
 */

val fakeData = listOf(
    CurrencyDto(
        name = "Bitcoin",
        symbol = "BTC",
        priceUsd = "9550.51",
        percentChange24h = "-3.26",
        percentChange1h = "0.51",
        volume = 41412461114.760406494140625
    ),
    CurrencyDto(
        name = "Ethereum",
        symbol = "ETH",
        priceUsd = "2550.51",
        percentChange24h = "-1.26",
        percentChange1h = "-0.51",
        volume = 52461114.760406494140625
    ),
    CurrencyDto(
        name = "XRP",
        symbol = "XRP",
        priceUsd = "0.27234",
        percentChange24h = "-6.786",
        percentChange1h = "1.51",
        volume = 3886229544.760406494140625
    ),
    CurrencyDto(
        name = "Bitcoin Cash",
        symbol = "BCH",
        priceUsd = "393.61",
        percentChange24h = "-7.91",
        percentChange1h = "2.54",
        volume = 7409385396.760406494140625
    ),
    CurrencyDto(
        name = "Bitcoin SV",
        symbol = "BCHSV",
        priceUsd = "290.61",
        percentChange24h = "-2.91",
        percentChange1h = "6.54",
        volume = 3536921069.760406494140625
    )
)