package com.backend.client.database

import com.backend.config.Config
import org.jetbrains.exposed.sql.Database

object DatabaseClient {

    private val clientConfig = Config().get().getConfig("db")

    fun initDB() {
        try {
            val url = clientConfig.getString("url")
            val driver = clientConfig.getString("driver")
            Database.connect(url, driver = driver, user = clientConfig.getString("userDB"), password = clientConfig.getString("passwordDB"))
        } catch (ex: Exception) {
            print("DatabaseClient error: $ex")
            throw ex
        }
    }
}