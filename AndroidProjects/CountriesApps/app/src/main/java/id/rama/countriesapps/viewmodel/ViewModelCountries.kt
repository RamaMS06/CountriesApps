package id.rama.countriesapps.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.rama.countriesapps.model.ModelCountries
import id.rama.countriesapps.repository.RepoCountries
import kotlinx.coroutines.launch
import retrofit2.Response

class ViewModelCountries(var repo : RepoCountries) : ViewModel() {
    val responseCountries : MutableLiveData<Response<List<ModelCountries>>> = MutableLiveData()

    fun getListCountries(regionalbloc : String){
        viewModelScope.launch {
            val response = repo.getListCountries(regionalbloc)
            responseCountries.value = response
        }
    }

    fun searchListCountries(name : String){
        viewModelScope.launch {
            val response = repo.searchListCounries(name)
            responseCountries.value = response
        }

    }

}