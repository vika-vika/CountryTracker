package net.vnnz.apps.kotlin.tracker.activity

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import net.vnnz.apps.kotlin.tracker.R
import net.vnnz.apps.kotlin.tracker.presenter.MainPresenter
import net.vnnz.apps.kotlin.tracker.view.MainView
import java.io.File

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, MainView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        toolbar.title = ""

        MainPresenter().bindView(this)

        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        val root: String = Environment.getExternalStorageDirectory().getAbsolutePath();
        val myDir: File = File(root + "/maps")

        val filename:String = "map_europe.png"
        var file: File = File (myDir, filename)
        Log.e("TAG","file exists " + file.exists())
        //TODO: add M permissons check
        //TODO File observer
        if (file.exists()) {
            Picasso.with(this).load(file).into(iv_map);
        }
    }

    override fun getFloatingButton() = fab

    override fun startListActivity()  = startActivity(Intent(this, ListActivity::class.java))

    override fun onBackPressed() {
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
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
}
