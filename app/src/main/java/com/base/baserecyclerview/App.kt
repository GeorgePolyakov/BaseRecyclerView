package com.base.baserecyclerview

import android.app.Application
import com.base.baserecyclerview.model.UserService

class App : Application() {

    val usersService = UserService()

}