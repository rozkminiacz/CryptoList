package tech.michalik.cryptolist.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import tech.michalik.cryptolist.BR
import tech.michalik.cryptolist.binding.BindableViewHolder
import tech.michalik.cryptolist.databinding.SingleCurrencyItemBinding
import tech.michalik.cryptolist.screen.CurrencyDisplayable

class CurrencyAdapter : RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {

    var items: List<CurrencyDisplayable> = emptyList()
        set(value) {
            val result =
                DiffUtil.calculateDiff(
                    CurrencyDisplayableListDiffUtilCallback(
                        field,
                        value
                    )
                )
            field = value
            result.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val binding =
            SingleCurrencyItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

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