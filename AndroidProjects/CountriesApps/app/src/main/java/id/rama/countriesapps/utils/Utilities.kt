package id.rama.countriesapps.utils

import id.rama.countriesapps.R
import id.rama.countriesapps.model.ModelFilterCountries

class Utilities {
    companion object {
        //Url
        const val URL_COUNTRIES = "https://restcountries.eu/rest/v2/"

        //Region Utilities
        var REGION_NAME = "regionName"
        var REGION_IMAGE = "regionImage"

        //Filter Utilities
        var filterName = arrayOf(
            "Africa",
            "Americas",
            "Asia",
            "Europe",
            "Oceania"
        )

        var filterImage = arrayOf(
            R.drawable.ic_africa,
            R.drawable.ic_americas,
            R.drawable.ic_asian,
            R.drawable.ic_leurope,
            R.drawable.ic_oceania
        )

        //Countries Utilities
        var NAME_COUNTRIES = "nameCountries"
        var CONTINENT_COUNTRIES = "continentCountries"
        var LATLONG_COUNTRIES ="latlongCountries"
        var FLAG_COUNTRIES = "flagCountries"
        var POPULATION_COUNTRIES = "populationCountries"
        var LANGUAGE_COUNTRIES = "languageCountries"
        var LANGUAGE_NATIVE_COUNTRIES = "languageNativeCountries"

        val listDataFilterName: ArrayList<ModelFilterCountries>
            get() {
                val list = arrayListOf<ModelFilterCountries>()
                for (position in filterName.indices) {
                    val filter = ModelFilterCountries()
                    filter.filterName = filterName[position]
                    filter.filterImage = filterImage[position]
                    list.add(filter)
                }
                return list
            }
    }
}