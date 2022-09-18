package com.backend.client.database

import org.jetbrains.exposed.sql.Database

object DatabaseClient {

    fun initDB() {
        try {
            Database.connect(
                url = "jdbc:mysql://docker-mysql:3306/indumentaria?useSSL=false&enabledTLSProtocols=TLSv1.2",
                driver = "com.mysql.cj.jdbc.Driver",
                user = "root",
                password = "qazWSXedc123"
            )
        } catch (ex: Exception) {
            print("DatabaseClient error: $ex")
            throw ex
        }
    }
}