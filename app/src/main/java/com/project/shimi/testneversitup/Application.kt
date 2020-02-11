package com.project.shimi.testneversitup

import android.app.Application
import android.content.Context

class TestNeversitupApp : Application() {

    private var token: String? = null

    init {
        instance = this
    }

    companion object {
        private var instance: TestNeversitupApp? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    fun getToken() = token

    fun setToken(token: String?) {
        this.token = token
    }
}