package com.example.v2.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.com.example.data.OrderItem
import com.example.v2.get_storage

class GalleryViewModel : ViewModel() {
    private val storage = get_storage()

    private val _itemList = MutableLiveData<ArrayList<OrderItem>>().apply {
        value = storage.orderQueue[storage.current_order].order
    }
    val itemList = _itemList
}