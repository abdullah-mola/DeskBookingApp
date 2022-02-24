package com.example.deskbookingappllication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.fragment.app.Fragment

import com.example.deskbookingappllication.R

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//
//        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigatin_view)
//        val navController = findNavController(R.id.navFragmentContainer)
//        bottomNavigationView.setupWithNavController(navController)
//
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


