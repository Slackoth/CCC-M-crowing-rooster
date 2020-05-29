package com.cccm.crowingrooster

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.cccm.crowingrooster.databinding.ActivityMainBinding
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CommunicationInterface {

    private lateinit var navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var  drawerLayout: DrawerLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //DataBinding
        val bind = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        //Linking DrawerLayout
        drawerLayout = bind.drawerLayout

        //Setting the nav controller
        navController = this.findNavController(R.id.nav_host_fragment)

        //TODO: Declaration of all top-level-destinations within the app.
        val topLevelDestinations = setOf(R.id.sellerMainScreen, R.id.logInFragment, R.id.sellerProfileFragment,
        R.id.salesFragment,R.id.sellerClientListFragment,R.id.buyerMainScreenFragment,R.id.chatFragment, R.id.BuyerProfileFragment,
        R.id.SearchFragment, R.id.OrdersFragment, R.id.deliveriesFragment)

//        Building the top-bar configuration
//        *Old way for building and setting the drawer-layout
//        appBarConfiguration = AppBarConfiguration.Builder(topLevelDestinations)
//            .setDrawerLayout(drawerLayout)
//            .build()

        appBarConfiguration = AppBarConfiguration(topLevelDestinations, drawerLayout)

//        Set up NavigationView
//        bind.navigationView.setupWithNavController(navController)
        NavigationUI.setupWithNavController(bind.navigationView,navController)

        //Set up MaterialToolbar
        //materialToolbar.setupWithNavController(navController, appBarConfiguration)

        //Setup ActionBar
        setSupportActionBar(bind.topAppBarMainScreen)
        setupActionBarWithNavController(navController,appBarConfiguration)

        //TODO: Changing the three-bar menu for a log-off icon on the nav-drawer *IT WON'T BE IMPLEMENTED BUT DON'T DELETE IT IN CASE
//        navController.addOnDestinationChangedListener { controller, destination, arguments ->
//            if (destination.id == R.id.sellerMainScreen) {
//                //bind.topAppBarMainScreen.setNavigationIcon(R.drawable.ic_logoff_24dp)
//                supportActionBar?.setHomeButtonEnabled(true)
//                supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_logoff_24dp)
//            }
//        }


    }

    override fun onSupportNavigateUp(): Boolean {
        //return super.onSupportNavigateUp()
        return NavigationUI.navigateUp(navController,appBarConfiguration)
    }

    /*TODO: Implementation of the interface methods to communicate with each fragment.
    *  Investigate if communication between fragments can be done through ViewModels and Interfaces together*/

    override fun hideTopBar() {
        supportActionBar?.hide()
    }

    override fun showTopBar() {
        supportActionBar?.show()
    }



//    override fun addMoreOptions(menuId: Int, menu: Menu?) {
//        menuInflater.inflate(menuId, menu)
//    }


    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.search_menu, menu)

        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView= searchItem?.actionView as SearchView

        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))

        searchView.setOnQueryTextListener( object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {

                searchView.clearFocus()
                searchView.setQuery( "", false)
                val collapseActionView = searchItem.collapseActionView()

                Toast.makeText(this@MainActivity, "Looking for $query", Toast.LENGTH_LONG).show()

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        return true

    }*/





}

