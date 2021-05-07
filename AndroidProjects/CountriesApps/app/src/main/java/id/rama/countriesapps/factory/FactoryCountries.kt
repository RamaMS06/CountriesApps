package id.rama.countriesapps.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.rama.countriesapps.repository.RepoCountries
import id.rama.countriesapps.viewmodel.ViewModelCountries

@Suppress("UNCHECKED_CAST")
class FactoryCountries(private val repo : RepoCountries) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewModelCountries(repo) as T
    }
}