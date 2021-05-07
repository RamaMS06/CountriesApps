package id.rama.countriesapps.model

data class ModelCountries(
    val name: String,
    val capital: String,
    val region: String,
    val subregion: String,
    val population: String,
    var latlng: List<Float>? = null,
    val nativeName: String,
    val flag: String,
    val languages : List<LanguageCountries>
)

data class LanguageCountries(
    val name : String,
    val nativeName: String
)