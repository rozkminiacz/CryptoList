package tech.michalik.cryptolist.network

import io.reactivex.Single
import retrofit2.http.GET

interface NetworkService {
    @GET("tickers/?limit=20")
    fun getTickers(): Single<Wrapper>
}