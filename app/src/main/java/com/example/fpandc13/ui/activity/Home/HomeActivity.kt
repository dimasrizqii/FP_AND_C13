package com.example.fpandc13.ui.activity.Home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.fpandc13.R
import com.example.fpandc13.databinding.ActivityHomeBinding
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityHomeBinding

    private lateinit var firebaseAnalytics: FirebaseAnalytics
//    private lateinit var firebaseCrashlytics: FirebaseCrashlytics


    override fun onCreate(savedInstanceState: Bundle?) {
        firebaseAnalytics = Firebase.analytics
//        firebaseCrashlytics = Firebase.crashlytics
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.main_fragment)
        setupActionBarWithNavController(navController)
        setupSmoothBottomMenu()
        supportActionBar?.hide()
        setupNavigation()


        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {


                else -> hideBottomNav(false)
            }
        }
    }
//    fun setSelectedItem(pos:Int){
//        binding.bottomBar.setSelectedItem(pos)
//    }
//    //set badge indicator
//    fun setBadge(pos:Int){
//        binding.bottomBar.setBadge(pos)
//    }
//    //remove badge indicator
//    fun removeBadge(pos:Int){
//        binding.bottomBar.removeBadge(pos)
//    }


    private fun hideBottomNav(hide: Boolean) {
        if (hide) {
            binding.bottomBar.visibility = View.GONE
        } else {
            binding.bottomBar.visibility = View.VISIBLE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.another_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.another_item_1 -> {
                showToast("Another Menu Item 1 Selected")
            }

            R.id.another_item_2 -> {
                showToast("Another Menu Item 2 Selected")
            }

            R.id.another_item_3 -> {
                showToast("Another Menu Item 3 Selected")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //set an active fragment programmatically
    fun setSelectedItem(pos:Int){
        binding.bottomBar.setSelectedItem(pos)
    }
    //set badge indicator
    fun setBadge(pos:Int){
        binding.bottomBar.setBadge(pos)
    }
    //remove badge indicator
    fun removeBadge(pos:Int){
        binding.bottomBar.removeBadge(pos)
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun setupSmoothBottomMenu() {
        val popupMenu = PopupMenu(this, null)
        popupMenu.inflate(R.menu.bottom_menu)
        val menu = popupMenu.menu
        //binding.bottomBar.setupWithNavController(menu, navController)
        binding.bottomBar.setupWithNavController( navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun setupNavigation() {
        // As we're inside a fragment calling `findNavController()` directly will crash the app
        // Hence, get a reference of `NavHostFragment`
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_fragment) as NavHostFragment

        // set navigation controller
        navController = navHostFragment.findNavController()

        // appbar configuration (for back button)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

}