package com.thusee.profile.util

import android.content.Context
import android.widget.ArrayAdapter
import com.thusee.profile.R
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
}