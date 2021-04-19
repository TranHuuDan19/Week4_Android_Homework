package com.example.week2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.week2.databinding.ActivityRestaurantListBinding

class RestaurantListActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRestaurantListBinding
    private lateinit var viewModel: RestaurantListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_restaurant_list)
        viewModel = ViewModelProvider(this).get(RestaurantListViewModel::class.java)
        val adapter = RestaurantAdapter()
        binding.rcList.adapter = adapter
        adapter.data = getRestaurantList()
    }
}