package com.alfadroid.aviasale

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.alfadroid.airtickets.presentation.AirTicketsFragment
import com.alfadroid.aviasale.databinding.ActivityMainBinding
import com.alfadroid.hotels.HotelsFragment
import com.alfadroid.profile.ProfileFragment
import com.alfadroid.shorter.ShorterFragment
import com.alfadroid.subscribers.SubscribersFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(
            binding.navHostFragment.id, AirTicketsFragment()
        ).commitNow()

        binding.apply {
            bottomNavigation.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.navigation_airTickets -> loadFragment(AirTicketsFragment())
                    R.id.navigation_hotels -> loadFragment(HotelsFragment())
                    R.id.navigation_shorter -> loadFragment(ShorterFragment())
                    R.id.navigation_subscribers -> loadFragment(SubscribersFragment())
                    R.id.navigation_profile -> loadFragment(ProfileFragment())
                }
                true
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.main)
        { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .commit()
    }
}