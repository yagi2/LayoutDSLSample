@file:SuppressLint("SetTextI18n")

package com.yagi2.layoutdslsample

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

@SuppressLint("Registered")
class AnswerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(createView(LayoutBuilder(this)))
    }

    private fun createView(builder: LayoutBuilder): View = with(builder) {
        linearLayout {
            textView {
                text = "Hello,"
                textColor = Color.RED
            }.lparams {
                margin = dip(10)
            }

            textView {
                text = "Kotlin!"
                textColor = Color.BLUE
            }.lparams {
                margin = dip(10)
            }

            (0..10).forEach {
                textView {
                    text = it.toString()
                }.lparams {
                    margin = dip(2)
                }
            }
        }
    }

    inner class LayoutBuilder(private val context: Context) {
        fun linearLayout(init: LinearLayout.() -> Unit): LinearLayout =
                LinearLayout(context).apply(init)

        fun ViewGroup.textView(init: TextView.() -> Unit): TextView =
                TextView(context).apply(init).also(::addView)

        fun TextView.lparams(
                width: Int = LinearLayout.LayoutParams.WRAP_CONTENT,
                height: Int = LinearLayout.LayoutParams.WRAP_CONTENT,
                init: LinearLayout.LayoutParams.() -> Unit
        ): TextView =
                this.apply {
                    layoutParams = LinearLayout.LayoutParams(width, height).apply(init)
                }

        var TextView.textColor: Int
            get() = currentTextColor
            set(v) = setTextColor(v)

        var LinearLayout.LayoutParams.margin: Int
            @Deprecated("This property does not have a getter.", level = DeprecationLevel.ERROR)
            get() = throw RuntimeException("This property does not have a getter.")
            set(v) = setMargins(v, v, v, v)

        private fun Context.dip(value: Int): Int = (value * resources.displayMetrics.density).toInt()
        fun View.dip(value: Int) = context.dip(value)
    }
}