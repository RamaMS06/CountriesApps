package id.rama.countriesapps.api

import id.rama.countriesapps.model.ModelCountries
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {

    //Get Countries
    @GET("region/{region}")
    suspend fun getCountries(
        @Path("region") regionalblock: String
    ): Response<List<ModelCountries>>

    //Search Countries
    @GET("name/{name}")
    suspend fun searchCountries(
        @Path("name") name: String
    ): Response<List<ModelCountries>>
}