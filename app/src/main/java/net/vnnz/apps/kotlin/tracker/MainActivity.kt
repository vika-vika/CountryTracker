package net.vnnz.apps.kotlin.tracker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import net.vnnz.apps.kotlin.tracker.adapter.ItemSelectAdapter
import net.vnnz.apps.kotlin.tracker.pojo.Item
import net.vnnz.apps.kotlin.tracker.pojo.ListItem
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var adapter = ItemSelectAdapter(Data.getInstance().items.map {ListItem(it)})
        itemsList.adapter = adapter
    }

    fun getItems(items: ArrayList<Item>) = items.map(Item::name)
}
