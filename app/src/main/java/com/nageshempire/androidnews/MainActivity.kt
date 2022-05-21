package com.nageshempire.androidnews

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.auth.FirebaseAuth
import com.mikepenz.materialdrawer.holder.ImageHolder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.nameRes
import com.mikepenz.materialdrawer.util.addItems
import com.mikepenz.materialdrawer.util.setupWithNavController
import com.mikepenz.materialdrawer.widget.MaterialDrawerSliderView
import com.nageshempire.androidnews.auth.AuthActivity
import com.nageshempire.androidnews.databinding.ActivityMainBinding
import com.nageshempire.androidnews.databinding.NavHeaderMainBinding
import com.nageshempire.androidnews.util.view.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()
    lateinit var profile: NavHeaderMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_articles, R.id.nav_my_city, R.id.nav_live
            ), binding.drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        profile = NavHeaderMainBinding.inflate(layoutInflater)
        setupDrawer(binding.navView, navController)
        binding.appBarMain.mainBottomBar.setupWithNavController(navController)
        viewModel.user.observe(this) { user ->
            if (user == null) {
                startActivity(Intent(this@MainActivity, AuthActivity::class.java))
                finish()
            } else {
                profile.userTitle.text = user.displayName
                supportActionBar?.subtitle = user.displayName
            }
        }
    }

    private fun setupDrawer(slider: MaterialDrawerSliderView, navController: NavController) {
        slider.setupWithNavController(navController, null, null)
        slider.headerView = profile.root
        val signOut = SecondaryDrawerItem().apply {
            nameRes = R.string.sign_out
            icon = ImageHolder(R.drawable.ic_awesome_sign_out_alt)
            isSelectable = false
        }
        slider.addItems(
            PrimaryDrawerItem().apply {
                nameRes = R.string.personalizations
                isSelectable = false
            },
            SecondaryDrawerItem().apply {
                nameRes = R.string.my_city
                icon = ImageHolder(R.drawable.my_city)
                isSelectable = false
            },
            SecondaryDrawerItem().apply {
                nameRes = R.string.home_screen_setup
                icon = ImageHolder(R.drawable.vuesax_bold_element_3)
                isSelectable = false
            },
            SecondaryDrawerItem().apply {
                nameRes = R.string.language_preference
                icon = ImageHolder(R.drawable.language)
                isSelectable = false
            },
            PrimaryDrawerItem().apply {
                nameRes = R.string.others
                isSelectable = false
            },
            SecondaryDrawerItem().apply {
                nameRes = R.string.privacy_policy
                icon = ImageHolder(R.drawable.privacy_policy)
                isSelectable = false
            },
            SecondaryDrawerItem().apply {
                nameRes = R.string.terms_and_conditions
                icon = ImageHolder(R.drawable.terms_and_conditions)
                isSelectable = false
            },
            DividerDrawerItem(),
            signOut
        )

        slider.onDrawerItemClickListener = { v, drawerItem, position ->
            when (drawerItem) {
                signOut -> {
                    showConfirmSignOutDialog()
                    // return false so it will close drawer automatically
                    false
                }
                else -> false
            }
        }
    }

    private fun showConfirmSignOutDialog() {
        AlertDialog.Builder(this).apply {
            setTitle("Do you want to sign out ?")
            setNegativeButton("Cancel") { _, _ -> }
            setPositiveButton("Yes, Sign out") { _, _ ->
                FirebaseAuth.getInstance().signOut()
                toast("Signed out successfully")
                startActivity(Intent(this@MainActivity, AuthActivity::class.java))
                finish()
            }
            show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}