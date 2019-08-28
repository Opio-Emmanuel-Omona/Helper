package com.example.helper.todo

import android.os.Parcelable

import java.io.Serializable

class TodoModel(var message: String?, var status: Boolean, var time: String?) : Serializable
