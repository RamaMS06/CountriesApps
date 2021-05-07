package id.rama.countriesapps.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import id.rama.countriesapps.R
import id.rama.countriesapps.adapter.AdapterCountries
import id.rama.countriesapps.factory.FactoryCountries
import id.rama.countriesapps.model.ModelCountries
import id.rama.countriesapps.repository.RepoCountries
import id.rama.countriesapps.utils.Utilities
import id.rama.countriesapps.viewmodel.ViewModelCountries
import kotlinx.android.synthetic.main.fragment_menu_utama.*

class MenuUtamaFragment : Fragment(),  AdapterCountries.CountriesClickListener{
    lateinit var navController: NavController
    private lateinit var viewModelCountries: ViewModelCountries
    private val adapterCountries by lazy { AdapterCountries(requireContext(), this) }
    lateinit var nameRegion: String
    private var imageRegion: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu_utama, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        nameRegion = arguments?.get(Utilities.REGION_NAME).toString()
        imageRegion = arguments?.get(Utilities.REGION_IMAGE) as Int

        setupRecyclerView()
        getDataCountries(nameRegion)
        searchDataCountries()
        img_detail_filter_flag_categories.setImageResource(imageRegion)

    }

    private fun setupRecyclerView() {
        rv_countries.apply {
            adapter = adapterCountries
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun getDataCountries(data: String) {
        val repo = RepoCountries()
        val factoryCountries = FactoryCountries(repo)
        viewModelCountries = ViewModelProvider(
            requireActivity(),
            factoryCountries
        ).get(ViewModelCountries::class.java)
        viewModelCountries.getListCountries(data)
        viewModelCountries.responseCountries.observe(requireActivity(), Observer { response ->
            if (response.isSuccessful) {
                response.body()?.let { adapterCountries.setData(it) }
            }
        })
    }

    private fun searchDataCountries() {
        edt_search_view_countries.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val repo = RepoCountries()
                val factoryCountries = FactoryCountries(repo)
                viewModelCountries = ViewModelProvider(
                    requireActivity(),
                    factoryCountries
                ).get(ViewModelCountries::class.java)
                viewModelCountries.searchListCountries(edt_search_view_countries.text.toString())
                viewModelCountries.responseCountries.observe(
                    requireActivity(),
                    Observer { response ->
                        if (response.isSuccessful) {
                            response.body()?.let { adapterCountries.setData(it) }
                        }
                    })
            }
        })
    }

    override fun onItemClickListener(item: ModelCountries, position : Int) {
        viewModelCountries.responseCountries.observe(this, Observer { response ->
            if (response.isSuccessful) {
                val bundle = bundleOf(
                    Utilities.NAME_COUNTRIES to item.name,
                    Utilities.CONTINENT_COUNTRIES to item.region,
                    Utilities.LATLONG_COUNTRIES to item.latlng,
                    Utilities.LANGUAGE_COUNTRIES to item.languages[0].name,
                    Utilities.LANGUAGE_NATIVE_COUNTRIES to item.languages[0].nativeName,
                    Utilities.POPULATION_COUNTRIES to item.population,
                    Utilities.FLAG_COUNTRIES to item.flag
                )
                navController.navigate(
                    R.id.action_menuUtamaFragment_to_detailCountriesFragment,
                    bundle
                )
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        nameRegion = arguments?.get(Utilities.REGION_NAME).toString()
        imageRegion = arguments?.get(Utilities.REGION_IMAGE) as Int
        outState.putString("name_region", nameRegion)
        outState.putInt("image_region", imageRegion)
        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        getDataCountries(savedInstanceState?.getString("name_region").toString())
    }

}