package com.kake.base.util

import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.Credentials
actual suspend fun initMongoApp() {
    App.create(Constants.APP_ID).login(Credentials.anonymous())
}