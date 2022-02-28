package com.example.deskbookingappllication.ui


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.deskbookingappllication.R
import com.example.deskbookingappllication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val binding get() = _binding

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val toolbar = supportActionBar
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = _binding.root
        setContentView(view)
        //NavController
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navFragmentContainer) as NavHostFragment
        navController = navHostFragment.navController


        appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.bottomNavigatinView.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomNavigatinView.visibility =
                if (destination.id == R.id.login || destination.id == R.id.register) {
                    View.GONE
                } else {
                    View.VISIBLE
                }
        }
//
        binding.bottomNavigatinView.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.BookingPlan -> {
                    toolbar?.title = "BookingPlan"
                    Navigation.findNavController(binding.navFragmentContainer)
                        .navigate(R.id.bookingPlan)
                    true
                }
                R.id.Profile -> {
                    toolbar?.title = "Profile"
                    Navigation.findNavController(binding.navFragmentContainer)
                        .navigate(R.id.userProfile)
                    true
                }
                R.id.Favorites -> {
                    toolbar?.title = "Favorites"
                    Navigation.findNavController(binding.navFragmentContainer)
                        .navigate(R.id.favorites)
                    true
                }
                else -> false
            }

        }


    }

    override fun onSupportNavigateUp(): Boolean {

        val navController = findNavController(R.id.navFragmentContainer)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()

    }

}


