package com.thusee.profile.util

import android.content.Context
import android.widget.ArrayAdapter
import com.thusee.profile.R
import com.thusee.profile.data.request.Cities
import com.thusee.profile.data.response.KeyValue

object AppUtils {

    private fun filterMultiChoiceData(
        result: MutableMap<String, List<KeyValue>>,
        multiChoiceName: String
    ): List<String> {
        result[multiChoiceName]?.let {
            return it.map { keyValue ->
                keyValue.name
            }
        }
        return emptyList()
    }

    fun getArrayAdapter(
        result: MutableMap<String, List<KeyValue>>,
        context: Context,
        multiChoiceName: String
    ): ArrayAdapter<String> {
        return ArrayAdapter(
            context,
            R.layout.list_item,
            filterMultiChoiceData(result, multiChoiceName)
        )
    }

    fun getIdFromMultiChoiceList(
        result: MutableMap<String, List<KeyValue>>,
        multiChoiceName: String, value: String
    ): String {
        if (value.isNotEmpty()) {
            return result[multiChoiceName]?.first {
                it.name == value
            }?.id.toString()
        }
        return ""
    }

    fun readFromAsset(context: Context, filename: String): String {
        return try {
            context.resources.assets.open(filename).bufferedReader().use { it.readText() }
        } catch (ex: Exception) {
            ""
        }
    }

    fun getCityObjectFromList(list: List<Cities>, city: String): Cities? {
        if (city.isNotEmpty()) {
            return list.find { it.city == city }
        }
        return Cities()
    }

    fun getCityStringList(cities: List<Cities>): List<String> {
        return cities.map { it.city }
    }

}