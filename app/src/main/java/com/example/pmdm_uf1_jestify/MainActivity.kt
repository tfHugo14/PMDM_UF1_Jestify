package com.example.pmdm_uf1_jestify

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.pmdm_uf1_jestify.databinding.ActivityMainBinding
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(actionBar)

        val drawerLayout = findViewById<DrawerLayout>(R.id.container)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration =  AppBarConfiguration.Builder(navController.graph)
        appBarConfiguration.setOpenableLayout(drawerLayout)
        val appBarBuild = appBarConfiguration.build()

        navView.setupWithNavController(navController)
        actionBar.setupWithNavController(navController, appBarBuild)
    }
}