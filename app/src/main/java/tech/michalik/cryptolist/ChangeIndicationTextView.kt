package tech.michalik.cryptolist

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat

/**
 * Created by jaroslawmichalik on 18/02/2020
 */
class ChangeIndicationTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {
    fun setValue(value: Double) {
        this.text = "$value%"
        this.setTextColor(colorForValue(value))
    }

    private fun colorForValue(value: Double): Int {
        val colorResource = when {
            value > 0 -> {
                R.color.darkGreen
            }
            value < 0 -> {
                R.color.darkRed
            }
            else -> R.color.darkerGray
        }
        return ContextCompat.getColor(context, colorResource)
    }
}