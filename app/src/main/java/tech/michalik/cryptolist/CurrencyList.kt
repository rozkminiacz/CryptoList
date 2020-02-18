package tech.michalik.cryptolist

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tech.michalik.cryptolist.binding.BindableViewHolder
import tech.michalik.cryptolist.databinding.SingleCurrencyItemBinding

/**
 * Created by jaroslawmichalik on 18/02/2020
 */
class CurrencyList @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private val currencyAdapter by lazy { CurrencyAdapter() }

    init {
        layoutManager = LinearLayoutManager(context).apply {
            orientation = VERTICAL
        }

        adapter = currencyAdapter
    }

    fun setItems(list: List<CurrencyDisplayable>) {
        currencyAdapter.items = list
    }
}

class CurrencyAdapter : RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {

    var items: List<CurrencyDisplayable> = emptyList()
        set(value) {
            val result =
                DiffUtil.calculateDiff(CurrencyDisplayableListDiffUtilCallback(field, value))
            field = value
            result.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val binding =
            SingleCurrencyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CurrencyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class CurrencyViewHolder(binding: SingleCurrencyItemBinding) :
        BindableViewHolder<CurrencyDisplayable>(
            binding = binding
        ) {
        override fun bind(viewModel: CurrencyDisplayable) {
            binding.setVariable(BR.displayable, viewModel)
            super.bind(viewModel)
        }
    }
}

class CurrencyDisplayableListDiffUtilCallback(
    private val oldList: List<CurrencyDisplayable>,
    private val newList: List<CurrencyDisplayable>
) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].symbol == newList[newItemPosition].symbol
    }

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}