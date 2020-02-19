package tech.michalik.cryptolist.views

import androidx.recyclerview.widget.DiffUtil
import tech.michalik.cryptolist.screen.CurrencyDisplayable

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