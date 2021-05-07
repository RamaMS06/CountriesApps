package id.rama.countriesapps.repository

import id.rama.countriesapps.api.RetrofitInstance
import id.rama.countriesapps.model.ModelCountries
import retrofit2.Response

class RepoCountries {
    suspend fun getListCountries(regionalbloc: String)
            : Response<List<ModelCountries>>? {
        return RetrofitInstance.apiListCountries.getCountries(regionalbloc)
    }

    suspend fun searchListCounries(name : String): Response <List<ModelCountries>>?{
        return RetrofitInstance.apiListCountries.searchCountries(name)
    }
}