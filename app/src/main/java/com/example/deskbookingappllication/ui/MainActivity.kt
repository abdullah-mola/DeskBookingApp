package com.example.deskbookingappllication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import com.example.deskbookingappllication.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView


import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //NavController
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navFragmentContainer) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)

        //bottomNavigation
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigatin_view)

        //remove bottom navigation bar from login and register screen
        navController.addOnDestinationChangedListener { _, destination, _ ->
            bottomNavigationView.visibility =
                if (destination.id == R.id.login || destination.id == R.id.register) {
                    View.GONE
                } else {
                    View.VISIBLE
                }
        }
//
//    }
        val toolbar = getSupportActionBar();

        loadFragment(BookingPlan.newInstance())

        bottom_navigatin_view.setOnItemSelectedListener { item ->
            val fragment: Fragment
            when (item.itemId) {
                R.id.BookingPlan -> {
                    toolbar?.title = "BookingPlan"
                    fragment = BookingPlan()
                    loadFragment(fragment)
                    true
                }
                R.id.Profile -> {
                    toolbar?.title = "Profile"
                    fragment = UserProfile()
                    loadFragment(fragment)
                    true

                }
                R.id.Favorites -> {
                    toolbar?.title = "Favorites"
                    fragment = Favorites()
                    loadFragment(fragment)
                    true

                }

                else -> false
            }

        }


    }

    private fun loadFragment(fragment: Fragment) {
        // load fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.navFragmentContainer, fragment)
            .commit()
    }
//    fun onNavigationItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.BookingPlan -> Navigation.findNavController(this, R.id.navFragmentContainer)
//                .navigate(
//                    R.id.BookingPlan
//                )
//
//            R.id.Profile -> Navigation.findNavController(this, R.id.navFragmentContainer)
//                .navigate(
//                    R.id.Profile
//                )
//
//            R.id.Favorites -> Navigation.findNavController(
//                this,
//                R.id.navFragmentContainer
//            ).navigate(R.id.Favorites)
//
//
//        }
//
//
//        return true
//    }
}


