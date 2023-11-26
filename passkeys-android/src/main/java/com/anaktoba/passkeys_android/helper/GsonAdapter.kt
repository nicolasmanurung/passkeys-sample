package com.anaktoba.passkeys_android.helper

import com.google.gson.Gson


/**
 * @author Nicolas Manurung (nicolas.manurung@dana.id)
 * @version GsonAdapter, v 0.1 26/11/23 18.04 by Nicolas Manurung
 */
class GsonAdapter : JSONAdapter {
    private val gson = Gson()
    override fun <T> fromJson(jsonStr: String, clazz: Class<T>): T {
        return gson.fromJson(jsonStr, clazz)
    }

    override fun <T> toJson(obj: T): String? {
        return gson.toJson(obj)
    }
}