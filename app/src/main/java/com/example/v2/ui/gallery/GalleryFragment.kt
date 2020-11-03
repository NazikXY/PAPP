package com.example.v2.ui.gallery

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.data.com.example.data.OrderItem
import com.example.menu.adapters.OrderAdapter
import com.example.v2.R
import com.example.v2.get_storage
import com.example.v2.ui.dialog.MyDialog
import com.example.v2.ui.dialogFragments.FillItemFragment
import kotlinx.android.synthetic.main.fragment_fill_item.*
import kotlinx.android.synthetic.main.fragment_gallery.*

class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel
    val storage = get_storage()


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_gallery, container, false)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)

        galleryViewModel.itemList.observe(viewLifecycleOwner, Observer {
            lvItems.adapter = OrderAdapter(this.context as Activity, it)
        })

        lvItems.onItemClickListener = AdapterView.OnItemClickListener{
            parent, view, position, id ->

            Toast.makeText(this.context, "$position", Toast.LENGTH_SHORT).show()
//            val fragment = st
            MyDialog.newInstance(storage.orderQueue[storage.current_order].order[position].name, position)
                .show(this.requireActivity().supportFragmentManager, MyDialog.TAG)


        }
    }
}