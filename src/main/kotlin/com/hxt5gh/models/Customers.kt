package com.hxt5gh.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

@Serializable
data class Customers(
    val id : String ,
    val fName : String,
    val lName : String,
    val email : String
)

val customerData = mutableSetOf<Customers>()
