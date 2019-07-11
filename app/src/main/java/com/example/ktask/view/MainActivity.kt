package com.example.ktask.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.support.v4.widget.DrawerLayout
import android.support.design.widget.NavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import com.example.ktask.R
import com.example.ktask.model.ModelPlaces
import com.example.ktask.viewmodel.PlaceViewModel
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        var frame1 : FrameLayout = findViewById(R.id.frame_allplace)
        var frame2 : FrameLayout = findViewById(R.id.frame_saveplace)
        val fab: FloatingActionButton = findViewById(R.id.fab)

//        var vm = ViewModelProviders.of(this).get(PlaceViewModel::class.java)
//        vm.allData?.observe(this,Observer<List<ModelPlaces>>(){
//                allData-> showList(allData)
//
//        })

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
    }

//    private fun showList(allData: List<ModelPlaces>?) {
//         Log.e("","***00"+allData)
//    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {

                // Handle the camera action
                //if (savedInstanceState == null) {
                    // 2
                frame_saveplace.visibility =View.GONE
                frame_allplace.visibility = View.VISIBLE

                supportFragmentManager
                        // 3
                        .beginTransaction()
                        // 4
                        .replace(R.id.frame_allplace, AllPlaces.newInstance(), "allPlace")
                        // 5
                        .commitAllowingStateLoss()
                }

            R.id.nav_gallery -> {

                frame_saveplace.visibility =View.VISIBLE
                frame_allplace.visibility = View.GONE

                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_saveplace, SavedPlaces.newInstance(),"savePlace")
                    .commitAllowingStateLoss()

        }




//            R.id.nav_slideshow -> {
//
//            }
//            R.id.nav_tools -> {
//
//            }
//            R.id.nav_share -> {
//
//            }
//            R.id.nav_send -> {
//
//            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
