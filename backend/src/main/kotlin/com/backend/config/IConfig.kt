package com.backend.config

import com.typesafe.config.Config

interface IConfig {
    fun get(): Config
}