package com.mureed.app.view

import android.os.Bundle
import androidx.fragment.app.commit
import com.mureed.app.R
import com.mureed.app.view.channels.ChannelFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) // Avoid adding multiple fragments after configuration change
            addChannelFragment()
    }

    private fun addChannelFragment() {
        supportFragmentManager.commit {
            replace(R.id.root, ChannelFragment(), ChannelFragment::class.java.name)
        }
    }
}