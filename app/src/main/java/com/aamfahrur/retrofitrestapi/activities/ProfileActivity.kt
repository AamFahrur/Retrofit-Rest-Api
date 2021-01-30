package com.aamfahrur.retrofitrestapi.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aamfahrur.retrofitrestapi.R
import android.content.Intent
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.aamfahrur.retrofitrestapi.fragments.HomeFragment
import com.aamfahrur.retrofitrestapi.fragments.SettingsFragment
import com.aamfahrur.retrofitrestapi.fragments.UserFragment
import com.aamfahrur.retrofitrestapi.storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        bottomNavigationView.setOnNavigationItemSelectedListener(this)
        displayFragment(HomeFragment())
    }

    private fun displayFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.layoutplacer, fragment)
            .commit()
    }

    override fun onStart() {
        super.onStart()
        if (!SharedPrefManager.getInstance(this).isLoggedIn){
            val intent = Intent(applicationContext, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        when(item.itemId){
            R.id.menu_home -> {
                fragment = HomeFragment()
            }
            R.id.menu_user -> {
                fragment = UserFragment()
            }
            R.id.menu_settings -> {
                fragment = SettingsFragment()
            }
        }

        if (fragment!= null){
            displayFragment(fragment)
        }
        return false
    }
}