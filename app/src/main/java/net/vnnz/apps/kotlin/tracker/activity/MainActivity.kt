package net.vnnz.apps.kotlin.tracker.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment

import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import net.vnnz.apps.kotlin.tracker.R
import net.vnnz.apps.kotlin.tracker.presenter.MainPresenter
import net.vnnz.apps.kotlin.tracker.view.MainView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, MainView {
    //TODO add M permissons check
    //TODO Add loader inside Main
    //TODO add hash to file & check

    private val REQUEST_SELECT_COUNTRIES = 1001;
    private val LOG_TAG = "MainActivity"

    private lateinit var presenter:MainPresenter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        toolbar.title = ""

        presenter = MainPresenter()
        presenter.bindView(this)
        presenter.setMap(this)

        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun getFloatingButton() = fab

    override fun startListActivity() {
        startActivityForResult(Intent(this, ListActivity::class.java), REQUEST_SELECT_COUNTRIES)
    }

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
            presenter.shareImage();
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

    override fun updateMap(bitmap:Bitmap?) {
        iv_map.setImageBitmap(bitmap)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if ((requestCode == REQUEST_SELECT_COUNTRIES) && (resultCode == Activity.RESULT_OK)) {
            presenter.updateMaps();
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        Log.e(LOG_TAG, "onRestoreInstanceState");
    }
}
