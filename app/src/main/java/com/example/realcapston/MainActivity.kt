package com.example.realcapston

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

@Suppress("UNREACHABLE_CODE")
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        if (App.prefs.myId == "") {
//            finish()
//        } else {

            navController = Navigation.findNavController(this, R.id.my_nav_host_fragment)

            bottomnavigation.setupWithNavController(navController)

//        NavigationUI.setupActionBarWithNavController(this,navController)

//        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId

        if (id == R.id.logout) {
            App.prefs.myId = ""
            startActivity<LoginActivity>()
        }
        return super.onOptionsItemSelected(item)
    }
}