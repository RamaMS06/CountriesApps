package id.rama.countriesapps.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import id.rama.countriesapps.R
import id.rama.countriesapps.model.ModelCountries
import id.rama.countriesapps.utils.GlideApp
import kotlinx.android.synthetic.main.item_countries.view.*

class AdapterCountries(var context: Context, var clickListener: CountriesClickListener) :
    RecyclerView.Adapter<AdapterCountries.CountriesViewHolder>() {
    private var listCountries = emptyList<ModelCountries>()

    inner class CountriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val container = itemView.container_countries

        fun bindData(data: ModelCountries) {
            with(itemView) {
                txt_name_countries.text = data.name
                txt_population.text = data.population
                txt_latlong.text = data.latlng.toString()
                GlideApp.with(context)
                    .load(data.flag)
                    .fitCenter()
                    .into(img_flag_countries)
            }
        }

        fun init(item : ModelCountries, action : CountriesClickListener){
            with(itemView){
                setOnClickListener {
                    action.onItemClickListener(item,absoluteAdapterPosition)
                }
            }
        }

    }

    interface CountriesClickListener {
        fun onItemClickListener(item : ModelCountries, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        return CountriesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_countries, parent, false)
        )
    }

    override fun getItemCount(): Int = listCountries.size

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        holder.container.animation =
            AnimationUtils.loadAnimation(context, R.anim.item_animation_fall_down)
        holder.bindData(listCountries[position])
        holder.init(listCountries[position],clickListener)
    }

    fun setData(newList: List<ModelCountries>) {
        listCountries = newList
        notifyDataSetChanged()
    }
}