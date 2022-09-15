package com.backend.util

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.convertValue
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


object Mapper {
    var objectMapper: ObjectMapper = ObjectMapper()
    val FORMAT_INVOICE_DATE: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    init {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        objectMapper.configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false)
        objectMapper.registerModule(SimpleModule())
        objectMapper.registerModule(JavaTimeModule())
        objectMapper.findAndRegisterModules()
        objectMapper.registerKotlinModule()
    }

    inline fun <reified T> fromMap(map: Map<String, Any?>): T = objectMapper.convertValue(map)
    fun toMap(obj: Any?): Map<String, Any?> = objectMapper.convertValue(obj ?: Any())
    // fun dtoToEntity(obj: Dto): Entity = objectMapper.convertValue(obj)
    inline fun <reified T> anyToObject(map: Any): T = objectMapper.convertValue(map)

    fun serialize(obj: Any?): String? {
        if (obj == null) return null
        return objectMapper.writeValueAsString(obj)
    }

    inline fun <reified T> deserialize(string: String?): T? {
        if (string == null) return null
        return objectMapper.readValue(string)
    }

    fun mapperDate(toDate: String): LocalDateTime {
        val invoiceDate = LocalDate.parse(toDate, FORMAT_INVOICE_DATE)
        val toDateParse = LocalDateTime.of(invoiceDate, LocalDateTime.MIN.toLocalTime())
        return toDateParse
    }
}

