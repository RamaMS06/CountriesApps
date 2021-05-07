package id.rama.countriesapps.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.rama.countriesapps.R
import id.rama.countriesapps.model.ModelFilterCountries
import kotlinx.android.synthetic.main.item_filter_countries.view.*

class AdapterFilterCountries(
    private var context: Context,
    private var clickListener: FilterClickListener
) : RecyclerView.Adapter<AdapterFilterCountries.FilterCategoriesViewHolder>() {

    var listFilter = emptyList<ModelFilterCountries>()

    inner class FilterCategoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val container = itemView.container_filter_countries
        fun bindData(data: ModelFilterCountries) {
            with(itemView) {
                txt_filter_name_countries.text = data.filterName
                Glide.with(context)
                    .load(data.filterImage)
                    .into(img_filter_flag_categories)
            }
        }

        fun init(item: ModelFilterCountries, action: FilterClickListener) {
            with(itemView) {
                setOnClickListener {
                    action.onItemClickListener(item, absoluteAdapterPosition)
                }
            }
        }
    }

    interface FilterClickListener {

        fun onItemClickListener(item: ModelFilterCountries, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterCategoriesViewHolder {
        return FilterCategoriesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_filter_countries, parent, false)
        )
    }

    override fun getItemCount(): Int = listFilter.size

    override fun onBindViewHolder(holder: FilterCategoriesViewHolder, position: Int) {
        holder.container.animation = AnimationUtils.loadAnimation(context,R.anim.item_animation_fall_down)
        holder.bindData(listFilter[position])
        holder.init(listFilter[position], clickListener)
    }

    fun setData(newList: List<ModelFilterCountries>) {
        listFilter = newList
        notifyDataSetChanged()
    }
}