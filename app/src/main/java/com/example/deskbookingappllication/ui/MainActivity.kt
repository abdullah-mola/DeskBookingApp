package com.example.deskbookingappllication.ui


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import com.example.deskbookingappllication.R
import com.example.deskbookingappllication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val binding get() = _binding

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = _binding.root
        setContentView(view)
        //NavController
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navFragmentContainer) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomNavigatinView.visibility =
                if (destination.id == R.id.login || destination.id == R.id.register) {
                    View.GONE
                } else {
                    View.VISIBLE
                }
        }
//
//    }
        val toolbar = supportActionBar

        loadFragment(BookingPlan.newInstance())

        binding.bottomNavigatinView.setOnItemSelectedListener { item ->
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
}


