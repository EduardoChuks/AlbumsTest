package com.edu.labs.albumstest.domain.util

import com.edu.labs.albumstest.common.Constants.UNKNOWN
import java.util.*

object CurrencyUtil {

    fun getCurrencySymbol(currency: String?): String =
        if (!currency.isNullOrEmpty()) Currency.getInstance(currency).symbol else UNKNOWN

}