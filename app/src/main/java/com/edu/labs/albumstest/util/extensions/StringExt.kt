package com.edu.labs.albumstest.util.extensions

import com.edu.labs.albumstest.common.Constants.UNKNOWN

fun String?.notNull() = if (!this.isNullOrEmpty()) this else UNKNOWN