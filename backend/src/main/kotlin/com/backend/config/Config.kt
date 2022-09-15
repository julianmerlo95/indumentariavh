package com.backend.config

import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory

class Config : IConfig {

    private val stores = getStores()

    override fun get(): Config {
        return stores
    }

    private fun getStores(): Config {
        val config = ConfigFactory.parseResources("application.${Environment.getEnvironment()}.conf")
            .resolve()
        return config
    }

}