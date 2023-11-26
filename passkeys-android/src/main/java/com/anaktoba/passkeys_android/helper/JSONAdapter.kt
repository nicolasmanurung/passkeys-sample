package com.anaktoba.passkeys_android.helper


/**
 * @author Nicolas Manurung (nicolas.manurung@dana.id)
 * @version JSONAdapter, v 0.1 26/11/23 18.04 by Nicolas Manurung
 */
interface JSONAdapter {
    fun <T> fromJson(jsonStr: String, clazz: Class<T>): T

    fun <T> toJson(obj: T): String?
}