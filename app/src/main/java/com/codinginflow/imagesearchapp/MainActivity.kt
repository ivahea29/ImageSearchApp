package com.codinginflow.imagesearchapp

<<<<<<< Updated upstream
=======
// Import necessary Android libraries
import android.os.Bundle
>>>>>>> Stashed changes
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

<<<<<<< Updated upstream
class MainActivity : AppCompatActivity() {
=======
// Declare that this Activity is an Android entry point for Hilt
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // Declare a variable to hold the Navigation Controller
    private lateinit var navController: NavController

    // Override the onCreate method of the Activity
>>>>>>> Stashed changes
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the layout for the Activity
        setContentView(R.layout.activity_main)
<<<<<<< Updated upstream
=======

        // Get the NavHostFragment and Navigation Controller
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_main) as NavHostFragment
        navController = navHostFragment.findNavController()

        // Set up the Action Bar with the Navigation Controller
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    // Override the onSupportNavigateUp method of the Activity
    override fun onSupportNavigateUp(): Boolean {
        // Navigate up or call the superclass method
        return navController.navigateUp() || super.onSupportNavigateUp()
>>>>>>> Stashed changes
    }
}
