package id.rama.countriesapps.api

import id.rama.countriesapps.utils.Utilities
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val logging = HttpLoggingInterceptor()
    private val client = OkHttpClient.Builder()


    private val retrofitListCountries by lazy {
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        client.addInterceptor(logging)
        Retrofit.Builder()
            .baseUrl(Utilities.URL_COUNTRIES)
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiListCountries : ApiServices by lazy {
        retrofitListCountries.create(ApiServices::class.java)
    }


}