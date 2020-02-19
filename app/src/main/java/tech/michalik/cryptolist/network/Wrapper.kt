package tech.michalik.cryptolist.network

import com.google.gson.annotations.SerializedName

data class Wrapper(
    @SerializedName("data")
    val data: List<CurrencyDto>
)