package net.vnnz.apps.kotlin.tracker.activity

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
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
import android.content.Context
import android.content.IntentFilter

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, MainView {

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

        val filter = IntentFilter()
        filter.addAction("FILE_CHANGED")

        receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                Log.e("TAG", "onReceive");
                presenter.onReceiveEvent(intent)
            }
        }
        registerReceiver(receiver, filter)
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
        if (receiver != null) {
            unregisterReceiver(receiver);
        }
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
        val root: String = Environment.getExternalStorageDirectory().getAbsolutePath();
        val myDir: File = File(root + "/maps")
        val files =  myDir.listFiles()

        Log.d("Files", "Size: " + files.size)
        for (i in files.indices) {
            Log.d("Files", "FileName:" + files[i].getName())
        }

        val filename:String = "map_europe.png"
        var file: File = File (myDir, filename)
        Log.e("TAG","file exists " + file.exists())
        //TODO: add M permissons check
        //TODO File observer

    }
}
