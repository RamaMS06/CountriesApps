package id.rama.countriesapps.di

import android.os.SystemClock
import android.util.Log
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import id.rama.countriesapps.api.ApiServices
import id.rama.countriesapps.utils.Utilities
import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun gson() : Gson = Gson()

    @Provides
    @Singleton
    fun provideOkhttpClient() : OkHttpClient{
        val httpInterceptor = HttpLoggingInterceptor()
        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 1

        val interceptor = Interceptor{ chain ->
            SystemClock.sleep(1000)
            chain.proceed(chain.request())
        }
        
        httpInterceptor.level = HttpLoggingInterceptor.Level.BASIC
      return OkHttpClient.Builder().addInterceptor(httpInterceptor)
          .addNetworkInterceptor(interceptor)
          .dispatcher(dispatcher)
          .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitIstance(okHttpClient: OkHttpClient) : Retrofit{
        return Retrofit.Builder()
            .baseUrl(Utilities.URL_COUNTRIES)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun apiRetrofitServices(retrofit : Retrofit) : ApiServices{
        return retrofit.create(ApiServices::class.java)
    }
}