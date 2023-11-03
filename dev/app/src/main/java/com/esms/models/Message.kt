package com.esms.models

import java.time.LocalTime

data class Message(
    val content: String,
    val time: LocalTime,  //TODO: change to datetime
    val recieved: Boolean //TODO: change to contact id
)