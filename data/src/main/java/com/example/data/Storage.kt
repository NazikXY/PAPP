package com.example.data

import android.util.Log
import com.example.data.com.example.data.CompletedOrder
import com.example.data.com.example.data.OrderItem
import com.example.data.com.example.data.UncompletedOrder
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import java.io.FileNotFoundException
import java.lang.Exception
import java.net.SocketException

class Storage(var name: String){

    var orderQueue: ArrayList<UncompletedOrder> = ArrayList<UncompletedOrder>()
    var network:Network =  Network()
    var current_order = 0
    var completedOrders: ArrayList<CompletedOrder> = ArrayList<CompletedOrder>()


    fun loadStorage(){
//        network =
    }

    fun reloadStorage():Boolean{
        var result = false
        try{
            orderQueue = parseOrdersFromJSON(network.loadOrders())
            result = true
        }
        catch (e: FileNotFoundException){
            Log.d("MyFilter", e.toString())
            println(e.toString())
        }
        catch(e: SocketException){
            Log.d("MyFilter", e.toString())
            println(e.toString())
        }
        catch (e: Exception){
            Log.d("MyFilter", e.toString())
        }
        finally {
            println("hello")
            return result
        }
    }
    private fun stringToArrayOfUncompletedOrder(data: JSONArray): ArrayList<OrderItem> {
        val result = data
        val itemList = ArrayList<OrderItem>()
        for( i in 0 until result.length()){
            val tmp = JSONTokener(result[i].toString()).nextValue() as JSONObject
            val item = tmp.getJSONObject("item")
            val count = tmp.getInt("count")
            itemList.add(OrderItem(item.getString("name"),item.getString("units"), count.toDouble()))
        }
        return itemList
    }
    private fun parseOrdersFromJSON(data: String): ArrayList<UncompletedOrder>{
        val ordersArray = (JSONTokener(data).nextValue() as JSONObject).getJSONArray("data")
        val result = ArrayList<UncompletedOrder>()
        for(i in 0 until ordersArray.length())
        {
            val obj = (JSONTokener(ordersArray[i].toString()).nextValue() as JSONObject)
            val id = obj.getInt("id")
            println(id.toString())
            val name = obj.getString("name")
            val orderListString = stringToArrayOfUncompletedOrder(obj.getJSONArray("goods_list"))
            result.add(UncompletedOrder(id, name, orderListString))
        }
        return result

    }
}