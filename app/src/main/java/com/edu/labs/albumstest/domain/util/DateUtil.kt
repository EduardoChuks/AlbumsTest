package com.edu.labs.albumstest.domain.util

import com.edu.labs.albumstest.common.Constants.UNKNOWN
import java.text.ParseException
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DateUtil {

    // Pattern from the api call response
    const val yyyyMMddTHHmmssZ = "yyyy-MM-dd'T'HH:mm:ss'Z'"

    /**
     * Function to parse and format a string date returned from api call
     * For this exercise the output format will be yyyy/MM/dd because there is no
     * other design requirement (name of the day e.g.)
     */
    fun parseDate(parsingPattern: String, dateStr: String?): String {
        return if (!dateStr.isNullOrEmpty()) {
            try {
                val formatter = DateTimeFormatter.ofPattern(parsingPattern)
                val localDate = LocalDate.parse(dateStr, formatter)
                "${localDate.year}/${localDate.monthValue}/${localDate.dayOfMonth}"
            } catch (e: ParseException) {
                UNKNOWN
            }
        } else UNKNOWN
    }

}