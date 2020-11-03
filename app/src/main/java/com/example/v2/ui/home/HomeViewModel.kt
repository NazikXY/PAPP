package com.example.v2.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.com.example.data.UncompletedOrder
import com.example.menu.adapters.UncompleteOrderAdapter
import com.example.v2.get_storage
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class HomeViewModel : ViewModel() {
    private val storage = get_storage()

    private val _orderList = MutableLiveData<ArrayList<UncompletedOrder>>().apply {
        runBlocking {
            GlobalScope.launch {
                storage.reloadStorage()
            }
        }
        value = storage.orderQueue
    }

    val orderList = _orderList


}