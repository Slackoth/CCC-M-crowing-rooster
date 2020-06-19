package com.cccm.crowingrooster.screens.chat.Messages

data class ChatMessage(val id: String, val mssge: String, val from_uid: String, val to_uid: String, val time: Long) {
    constructor() : this("", "", "", "", -1)
}