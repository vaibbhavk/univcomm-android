package com.example.univcomm

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.univcomm.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    lateinit var drawerLayout: DrawerLayout

    lateinit var drawerNavigationView: NavigationView

    lateinit var toggle: ActionBarDrawerToggle

    private lateinit var binding: ActivityMainBinding


    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isLoading.value
            }
        }
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.customActionBar))

        drawerLayout = binding.drawerLayout


        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        drawerNavigationView = binding.drawerNavigationView

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomNavigation.setupWithNavController(navController)

        drawerNavigationView.setupWithNavController(navController)

        val bottomNavigationView = binding.bottomNavigation
        val bottomAppBar = binding.bottomAppBar
        val fabAdd = binding.fabAdd
        val appBarLayout = binding.appBarLayout
        val topAppBar = binding.customActionBar
        val pseudoSpace = binding.pseudoSpace


        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.welcomeFragment, R.id.loginFragment, R.id.registerFragment -> {
                    pseudoSpace.visibility = View.GONE
                    topAppBar.visibility = View.GONE
                    appBarLayout.visibility = View.GONE
                    fabAdd.visibility = View.GONE
                    bottomAppBar.visibility = View.GONE
                    bottomNavigationView.visibility = View.GONE
                }
                else -> {
                    pseudoSpace.visibility = View.VISIBLE
                    topAppBar.visibility = View.VISIBLE
                    appBarLayout.visibility = View.VISIBLE
                    fabAdd.visibility = View.VISIBLE
                    bottomAppBar.visibility = View.VISIBLE
                    bottomNavigationView.visibility = View.VISIBLE
                }
            }

            when (destination.id) {
                R.id.homeFragment -> topAppBar.title = "Home"
                R.id.notificationFragment -> topAppBar.title = "Notifications"
            }
        }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.top_app_bar_menu, menu);
        return true;
    }


    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(drawerNavigationView)) {
            drawerLayout.closeDrawer(drawerNavigationView, true)
        } else {
            super.onBackPressed()
        }
    }

}