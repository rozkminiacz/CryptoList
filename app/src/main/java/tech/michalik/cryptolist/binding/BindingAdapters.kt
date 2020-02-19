package tech.michalik.cryptolist.binding

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.core.widget.ContentLoadingProgressBar
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingComponent
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import tech.michalik.cryptolist.views.ChangeIndicationTextView
import tech.michalik.cryptolist.screen.CurrencyDisplayable
import tech.michalik.cryptolist.views.CurrencyList
import tech.michalik.cryptolist.binding.SpinnerExtensions.getSpinnerValue
import tech.michalik.cryptolist.binding.SpinnerExtensions.setSpinnerEntries
import tech.michalik.cryptolist.binding.SpinnerExtensions.setSpinnerInverseBindingListener
import tech.michalik.cryptolist.binding.SpinnerExtensions.setSpinnerValue

/**
 * Created by jaroslawmichalik on 18/02/2020
 */

class BindingAdaptersComponent : DataBindingComponent {
    override fun getInverseSpinnerBindings(): InverseSpinnerBindings {
        return InverseSpinnerBindings()
    }

    override fun getCurrencyListBindings(): CurrencyListBindings {
        return CurrencyListBindings()
    }

    override fun getTextViewBindings(): TextViewBindings {
        return TextViewBindings()
    }
}

class CurrencyListBindings {
    @BindingAdapter("bind:items")
    fun CurrencyList.bindItems(list: List<CurrencyDisplayable>) {
        setItems(list)
    }
}

class TextViewBindings {
    private val dateTimeFormatter = DateTimeFormat.forPattern("HH:mm:ss")
    @BindingAdapter("bind:hourMinuteSecond")
    fun TextView.bindDateTime(dateTime: DateTime) {
        text = dateTimeFormatter.print(dateTime)
    }

    @BindingAdapter("bind:percentageChange")
    fun ChangeIndicationTextView.bindPercentageChange(value: Double) {
        setValue(value)
    }

    @BindingAdapter("bind:bindNumber")
    fun TextView.bindNumber(value: Number) {
        text = value.toString()
    }

    @BindingAdapter("bind:delayedVisibility")
    fun TextView.bindVisibility(visible: Boolean) {
        if (visible) {

            postDelayed({
                visibility = VISIBLE
            }, 700)
        } else {
            visibility = GONE
        }
    }

    @BindingAdapter("bind:bindDollar")
    fun TextView.bindDollar(value: Double) {
        text = "$value$"
    }
}

@BindingAdapter("bind:visible")
fun ContentLoadingProgressBar.bindVisibility(visible: Boolean) {
    if (visible) show() else hide()
}

class InverseSpinnerBindings {

    @BindingAdapter("bind:selectedValue")
    fun Spinner.setSelectedValue(selectedValue: Any?) {
        setSpinnerValue(selectedValue)
    }

    @BindingAdapter("bind:selectedValueAttrChanged")
    fun Spinner.setInverseBindingListener(inverseBindingListener: InverseBindingListener?) {
        setSpinnerInverseBindingListener(inverseBindingListener)
    }

    @BindingAdapter("bind:entries")
    fun Spinner.setEntries(entries: List<Any>?) {
        setSpinnerEntries(entries)
    }

    companion object InverseSpinnerBindings {

        @JvmStatic
        @InverseBindingAdapter(attribute = "selectedValue", event = "selectedValueAttrChanged")
        fun Spinner.getSelectedValue(): Any? {
            return getSpinnerValue()
        }
    }
}

object SpinnerExtensions {

    fun Spinner.setSpinnerEntries(entries: List<Any>?) {
        if (entries != null) {
            val arrayAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, entries)
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            adapter = arrayAdapter
        }
    }

    fun Spinner.setSpinnerInverseBindingListener(listener: InverseBindingListener?) {
        if (listener == null) {
            onItemSelectedListener = null
        } else {
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    if (tag != position) {
                        listener.onChange()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
        }
    }

    fun Spinner.setSpinnerValue(value: Any?) {
        if (adapter != null) {
            val position = (adapter as ArrayAdapter<Any>).getPosition(value)
            setSelection(position, false)
            tag = position
        }
    }

    fun Spinner.getSpinnerValue(): Any? {
        return selectedItem
    }
}