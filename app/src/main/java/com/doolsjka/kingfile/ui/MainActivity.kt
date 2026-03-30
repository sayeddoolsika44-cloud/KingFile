package com.doolsjka.kingfile.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.doolsjka.kingfile.R
import com.doolsjka.kingfile.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var b: ActivityMainBinding
    private lateinit var nav: NavController

    override fun onCreate(s: Bundle?) {
        super.onCreate(s)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
        val host = supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
        nav = host.navController
        b.bottomNav.setupWithNavController(nav)
        nav.addOnDestinationChangedListener { _, dest, _ ->
            b.bottomNav.visibility = when(dest.id) {
                R.id.mediaFragment, R.id.browserFragment, R.id.terminalFragment -> View.GONE
                else -> View.VISIBLE
            }
        }
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent?) {
        intent?.data?.let { uri ->
            val b = Bundle().apply { putString("uri", uri.toString()) }
            nav.navigate(R.id.mediaFragment, b)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    override fun onSupportNavigateUp() = nav.navigateUp() || super.onSupportNavigateUp()
}
