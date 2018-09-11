package com.yagi2.layoutdslsample

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

class MainActivity : AppCompatActivity() {

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
                textColor = Color.GREEN
            }.lparams {
                margin = dip(10)
            }
        }
    }
}

class LayoutBuilder(private val context: Context) {
    fun Context.dip(value: Int): Int = (value * resources.displayMetrics.density).toInt()
    fun View.dip(value: Int) = context.dip(value)
}