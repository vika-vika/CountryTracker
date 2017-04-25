package net.vnnz.apps.kotlin.tracker.activity

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import net.vnnz.apps.kotlin.tracker.R
import net.vnnz.apps.kotlin.tracker.presenter.MainPresenter
import net.vnnz.apps.kotlin.tracker.view.MainView
import java.io.File
import android.graphics.BitmapFactory
import android.content.BroadcastReceiver

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, MainView {
    //TODO add M permissons check
    //TODO Add loader inside Main
    //TODO save file in Service
    //TODO add hash to file & check

    lateinit var receiver : BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        toolbar.title = ""

        val presenter = MainPresenter()
        presenter.bindView(this)

        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun getFloatingButton() = fab

    override fun startListActivity()  = startActivity(Intent(this, ListActivity::class.java))

    override fun onBackPressed()  {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_share) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
      //  val id = item.itemId
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun updateMaps() {
        var file: File = File (Environment.getExternalStorageDirectory().absolutePath, "maps/map_europe.png")
        if (file.exists()) {
            val myBitmap = BitmapFactory.decodeFile(file.absolutePath)
            iv_map.setImageBitmap(myBitmap)
        }
    }

    fun onFooClick(view: View) {

    }
}
