package com.base.baserecyclerview

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.base.baserecyclerview.databinding.ActivityMainBinding
import com.base.baserecyclerview.model.User
import com.base.baserecyclerview.model.UserService
import com.base.baserecyclerview.model.UsersListener

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UsersAdapter

    private val usersService : UserService
        get() = (applicationContext as App).usersService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UsersAdapter()

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        usersService.addListener (usersListener)
    }

    private val usersListener: UsersListener = { usersList ->
        adapter.users = usersList
        Log.d("userData", "users Listener list is ${usersList}")
    }

    override fun onDestroy() {
        super.onDestroy()
        usersService.removeListener { usersListener }
    }
}