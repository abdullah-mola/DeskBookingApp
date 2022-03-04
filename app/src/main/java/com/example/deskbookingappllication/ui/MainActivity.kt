package com.example.deskbookingappllication.ui


import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
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
import com.example.deskbookingappllication.model.User
import com.example.deskbookingappllication.model.viewModels.UserViewModel
import com.google.android.material.internal.ContextUtils.getActivity
import okhttp3.internal.assertionsEnabled


class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val binding get() = _binding
    private val userViewModel: UserViewModel by viewModels()
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val toolbar = supportActionBar
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = _binding.root
        setContentView(view)
        //Hide Admin
        userViewModel.user.observe(this) {
            binding.bottomNavigatinView.menu.findItem(R.id.admin).isEnabled = it.isAdmin == true
        }
        //Navigation {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navFragmentContainer) as NavHostFragment
        navController = navHostFragment.navController


        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.login,
                R.id.offices,
                R.id.userProfile,
                R.id.favorites,
                R.id.register,
                R.id.desks,
                R.id.admin,
            )
        )
        binding.bottomNavigatinView.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setHomeButtonEnabled(false)

        navController.addOnDestinationChangedListener { _, destination, _ ->

            binding.bottomNavigatinView.visibility =
                if (destination.id == R.id.login || destination.id == R.id.register) {
                    View.GONE

                } else {
                    View.VISIBLE
                }
        }


        binding.bottomNavigatinView.setOnNavigationItemSelectedListener {

            when (it.itemId) {

                R.id.BookingPlan -> {
                    toolbar?.title = "BookingPlan"
                    Navigation.findNavController(binding.navFragmentContainer)
                        .navigate(R.id.offices)
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

                R.id.admin -> {


                    toolbar?.title = "Admin"
                    Navigation.findNavController(binding.navFragmentContainer)
                        .navigate(R.id.admin)

                    true
                }
                else -> false
            }

        }
        //End Navigation }


    }


    override fun onSupportNavigateUp(): Boolean {

        val navController = findNavController(R.id.navFragmentContainer)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()

    }


}


