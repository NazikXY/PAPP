package com.example.data
import android.util.JsonReader
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL




class Network() {
    var host = "http://192.168.0.103"
    var currentOrder = "/get_current_order/"
    var ordersSequence = "/get_orders_sequence"


    fun isConnection(): String {

        return reqGet(URL(host))
    }

    fun loadOrder(): String{

        return reqGet(URL(host.plus(currentOrder)))
    }

    fun loadOrders(): String{
        return reqGet(URL(host.plus(ordersSequence)))
    }

    fun reqGet(url: URL): String{
        with(url.openConnection() as HttpURLConnection){
            requestMethod = "GET"
            var res = ""
            inputStream.bufferedReader().lineSequence().iterator().forEach {
                item -> res += item
            }

            return res
        }
    }

}