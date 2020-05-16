package com.example.urbangymfinder

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Userr(
    var name: String? = "",
    var email: String? = ""
) {

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "uid" to name,
            "author" to email
        )
    }
}