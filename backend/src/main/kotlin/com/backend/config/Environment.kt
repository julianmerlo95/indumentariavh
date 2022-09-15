package com.backend.config

object Environment {

    private const val VAULT_TOKEN_ENV = "VAULT_TOKEN"
    private const val ENV_VARIABLE = "ENV"
    private const val LOCAL = "local"
    private const val DEVELOPMENT = "dev"
    private const val STAGING = "stg"
    private const val PRODUCTION = "live"

    fun isDevelopment(): Boolean {
        return DEVELOPMENT.contains(getEnvironment())
    }

    fun isStaging(): Boolean {
        return STAGING.contains(getEnvironment())
    }

    fun isProduction(): Boolean {
        return PRODUCTION.contains(getEnvironment())
    }

    fun isLocal(): Boolean {
        return LOCAL.contains(getEnvironment())
    }

    fun getVaultToken(): String? {
        return System.getenv(VAULT_TOKEN_ENV)
    }

    fun getEnvironment(): String {
        return System.getenv(ENV_VARIABLE) ?: LOCAL
    }
}
