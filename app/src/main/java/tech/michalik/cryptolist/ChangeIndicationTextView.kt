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
        val (color, drawable) = colorAndDrawableForValue(value)
        this.setTextColor(color)
        this.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawable, 0)
    }

    private fun colorAndDrawableForValue(value: Double): Pair<Int, Int> {
        return when {
            value > 0 -> {
                R.color.darkGreen to R.drawable.triangle_up_green
            }
            value < 0 -> {
                R.color.darkRed to R.drawable.triangle_down_red
            }
            else -> R.color.darkerGray to 0
        }.let { ContextCompat.getColor(context, it.first) to it.second }
    }
}