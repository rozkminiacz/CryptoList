package tech.michalik.cryptolist.views

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tech.michalik.cryptolist.screen.CurrencyDisplayable

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