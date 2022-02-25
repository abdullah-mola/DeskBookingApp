package com.example.deskbookingappllication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
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
        appBarConfiguration = AppBarConfiguration(navController.graph )

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigatin_view)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            bottomNavigationView.visibility = if (destination.id == R.id.login ||destination.id == R.id.register) {
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
            var fragment: Fragment
            when (item.itemId) {
                R.id.BookingPlan -> {
                    toolbar?.setTitle("BookingPlan")
                    fragment = BookingPlan()
                    loadFragment(fragment)
                    true
                }
                R.id.Profile -> {
                    toolbar?.setTitle("Profile")
                    fragment = UserProfile()
                    loadFragment(fragment)
                    true

                }
                R.id.Favorites -> {
                    toolbar?.setTitle("Favorites")
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
}


