package tech.michalik.cryptolist

import com.google.gson.annotations.SerializedName

data class CurrencyDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("price_usd")
    val priceUsd: String,
    @SerializedName("percent_change_24h")
    val percentChange24h: String,
    @SerializedName("percent_change_1h")
    val percentChange1h: String,
    @SerializedName("volume24")
    val volume: Double
)