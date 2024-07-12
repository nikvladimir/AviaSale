package com.alfadroid.aviasale

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.alfadroid.airtickets.presentation.HostAirTicketsFragment
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

        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.nav_host_fragment, HostAirTicketsFragment.newInstance())
            }
        }

        binding.apply {
            bottomNavigation.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.navigation_airTickets -> loadFragment(HostAirTicketsFragment.newInstance())
                    R.id.navigation_hotels -> loadFragment(HotelsFragment.newInstance())
                    R.id.navigation_shorter -> loadFragment(ShorterFragment.newInstance())
                    R.id.navigation_subscribers -> loadFragment(SubscribersFragment.newInstance())
                    R.id.navigation_profile -> loadFragment(ProfileFragment.newInstance())
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
        supportFragmentManager.commit {
            replace(R.id.nav_host_fragment, fragment)
        }
    }
}