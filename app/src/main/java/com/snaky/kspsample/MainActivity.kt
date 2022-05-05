package com.snaky.kspsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.snaky.ksp.firstMap
import com.snaky.ksp.mainList
import com.snaky.kspsample.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.text).text =
            mainList.joinToString() + "\n" + "maps: \n" + firstMap
    }
}