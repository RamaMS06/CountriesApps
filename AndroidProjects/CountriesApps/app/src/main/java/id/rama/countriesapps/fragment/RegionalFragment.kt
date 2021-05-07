package id.rama.countriesapps.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import id.rama.countriesapps.R
import id.rama.countriesapps.adapter.AdapterFilterCountries
import id.rama.countriesapps.model.ModelFilterCountries
import id.rama.countriesapps.utils.Utilities
import kotlinx.android.synthetic.main.fragment_regional.*

class RegionalFragment : Fragment(), AdapterFilterCountries.FilterClickListener {
    lateinit var navController : NavController
    private val adapterFilterCountries by lazy { AdapterFilterCountries(requireContext(),this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_regional, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        getListRegional()

        activity?.onBackPressedDispatcher?.addCallback(requireActivity(), object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }

        })

    }

    private fun getListRegional() {
        rv_filter_countries.apply {
            adapter = adapterFilterCountries
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL, false)
        }
        adapterFilterCountries.setData(Utilities.listDataFilterName)
    }

    override fun onItemClickListener(item: ModelFilterCountries, position: Int) {
        val bundle = bundleOf(Utilities.REGION_NAME to item.filterName, Utilities.REGION_IMAGE to item.filterImage)
        navController.navigate(R.id.action_regionalFragment_to_menuUtamaFragment, bundle)
    }
}