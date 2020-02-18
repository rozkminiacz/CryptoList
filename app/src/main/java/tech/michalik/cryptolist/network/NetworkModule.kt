package tech.michalik.cryptolist.network

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import tech.michalik.cryptolist.CurrencyDto
import timber.log.Timber

/**
 * Created by jaroslawmichalik on 17/02/2020
 */

class NetworkModule {

    val networkService by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("https://api.coinlore.com/api/")
            .client(okHttpClient)
            .build()
            .create(NetworkService::class.java)
    }

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    private val loggingInterceptor: HttpLoggingInterceptor by lazy {
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            Timber.d(it)
        })
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        httpLoggingInterceptor
    }
}

interface NetworkService {
    @GET("tickers/?limit=20")
    fun getTickers(): Single<Wrapper>
}

data class Wrapper(
    @SerializedName("data")
    val data: List<CurrencyDto>
)