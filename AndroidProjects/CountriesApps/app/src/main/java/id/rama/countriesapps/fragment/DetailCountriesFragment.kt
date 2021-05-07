package id.rama.countriesapps.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.Navigation
import id.rama.countriesapps.R
import id.rama.countriesapps.utils.GlideApp
import id.rama.countriesapps.utils.Utilities
import kotlinx.android.synthetic.main.fragment_detail_countries.*

class DetailCountriesFragment : Fragment() {
    lateinit var navController: NavController
    lateinit var nameCountries: String
    lateinit var continentCountries: String
    lateinit var latlongCountries: String
    lateinit var populationCountries: String
    lateinit var languageCountries: String
    lateinit var nativeLanguageCountries : String
    lateinit var imageCountries: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_countries, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        getDataExtra()

        btn_back_detail_countries.setOnClickListener {
            activity?.onBackPressed()
        }
        activity?.onBackPressedDispatcher?.addCallback(requireActivity(), object :OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                navController.navigate(R.id.action_detailCountriesFragment_to_regionalFragment)
                this.remove()
            }
        })
    }

    private fun getDataExtra() {
        nameCountries = arguments?.get(Utilities.NAME_COUNTRIES).toString()
        continentCountries = arguments?.get(Utilities.CONTINENT_COUNTRIES).toString()
        latlongCountries = arguments?.get(Utilities.LATLONG_COUNTRIES).toString()
        populationCountries = arguments?.get(Utilities.POPULATION_COUNTRIES).toString()
        languageCountries = arguments?.get(Utilities.LANGUAGE_COUNTRIES).toString()
        nativeLanguageCountries = arguments?.get(Utilities.LANGUAGE_NATIVE_COUNTRIES).toString()
        imageCountries = arguments?.get(Utilities.FLAG_COUNTRIES).toString()

        txt_detail_name_countries.text = nameCountries
        txt_detail_continent.text = continentCountries
        txt_detail_latitude_longitude.text = latlongCountries
        txt_detail_populasi.text = populationCountries
        txt_detail_native_language.text = nativeLanguageCountries
        txt_detail_language.text = languageCountries

        GlideApp.with(requireContext())
            .load(imageCountries)
            .into(img_detail_flag_categories)
    }



}