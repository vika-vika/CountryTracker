package net.vnnz.apps.kotlin.tracker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import net.vnnz.apps.kotlin.tracker.pojo.Item
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var adapter = ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, getItems(Data.getInstance().items))

        itemsList.adapter = adapter
    }

    fun getItems(items: ArrayList<Item>) = items.map(Item::name)
}
