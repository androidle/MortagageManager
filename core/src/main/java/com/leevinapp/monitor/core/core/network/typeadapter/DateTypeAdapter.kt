package com.leevinapp.monitor.core.core.network.typeadapter

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import com.google.gson.JsonSyntaxException
import com.leevinapp.monitor.core.core.config.Constants.DATE_FORMAT_UTC
import java.lang.reflect.Type
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone
import java.util.Locale

class DateTypeAdapter : JsonSerializer<Date>, JsonDeserializer<Date> {
    private val dateFormat: DateFormat

    init {
        dateFormat = SimpleDateFormat(DATE_FORMAT_UTC,Locale.CHINA)
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
    }

    override fun serialize(
        date: Date,
        typeOfSrc: Type,
        context: JsonSerializationContext
    ): JsonElement {
        synchronized(dateFormat) {
            return JsonPrimitive(dateFormat.format(date))
        }
    }

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Date {
        try {
            synchronized(dateFormat) {
                return dateFormat.parse(json.asString) as Date
            }
        } catch (e: ParseException) {
            throw JsonSyntaxException(json.asString, e)
        }
    }
}