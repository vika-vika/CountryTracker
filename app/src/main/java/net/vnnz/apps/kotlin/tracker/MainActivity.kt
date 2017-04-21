package net.vnnz.apps.kotlin.tracker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.databinding.DataBindingUtil
import net.vnnz.apps.kotlin.tracker.databinding.ActivityMainBinding
import net.vnnz.apps.kotlin.tracker.pojo.ListItem

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding:ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewmodel = ListPresenter(this, supportLoaderManager)
    }
}
