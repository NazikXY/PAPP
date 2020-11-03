package com.example.v2.ui.home

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.v2.R
import com.example.menu.adapters.UncompleteOrderAdapter
import com.example.v2.get_storage
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {
    val storage = get_storage()
    private lateinit var homeViewModel: HomeViewModel
    var naviControler:NavController? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        naviControler = findNavController()
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)

        homeViewModel.orderList.observe(viewLifecycleOwner, Observer {
            lvOrder.adapter = UncompleteOrderAdapter(this.context as Activity, it)
        })

        lvOrder.onItemClickListener = AdapterView.OnItemClickListener{
                parent: AdapterView<*>, view: View, position: Int, id: Long ->
            Toast.makeText(this.context, "$position", Toast.LENGTH_SHORT).show()
            storage.current_order = position
            naviControler?.navigate(R.id.nav_gallery)
        }
    }
}