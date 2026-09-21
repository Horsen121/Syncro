package com.example.utils.strings

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface StringResourceProvider {
    fun getString(@StringRes resId: Int): String

    fun getStringWithParams(
        @StringRes resId: Int,
        vararg params: String
    ): String
}

class AndroidStringResourceProvider @Inject constructor(
    @param:ApplicationContext private val context: Context
) : StringResourceProvider {

    override fun getString(@StringRes resId: Int): String = context.getString(resId)

    override fun getStringWithParams(resId: Int, vararg params: String): String =
        context.getString(resId, params)
}