package com.example.a2hw6month

import java.io.Serializable


data class Task(val id: String, var text: String, var isChecked: Boolean = false) : Serializable