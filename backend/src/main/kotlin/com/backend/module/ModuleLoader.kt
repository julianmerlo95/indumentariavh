package com.backend.module

import com.backend.config.Config
import com.backend.config.IConfig
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import com.backend.client.database.DatabaseClient
import com.backend.client.database.augmentedReality.repository.AugmentedRealityRepository
import com.backend.client.database.bill.repository.BillRepository
import com.backend.client.database.billDetail.repository.BillDetailRepository
import com.backend.client.database.client.repository.ClientRepository
import com.backend.client.database.currentAccountClient.repository.CurrentAccountClientRepository
import com.backend.client.database.expenses.repository.ExpensesRepository
import com.backend.client.database.product.repository.ProductRepository
import com.backend.client.database.report.repository.ReportRepository
import com.backend.client.database.user.repository.UserRepository
import com.backend.service.*

object ModuleLoader {

    fun init() {
        startKoin {
            modules(loadModules())
        }
    }

    private fun loadModules(): List<Module> {
        return listOf(
            coreModule(),
            clientModule(),
            serviceModule(),
            repositoryModule()
        )
    }

    private fun coreModule(): Module {
        return module {
            single<IConfig> { Config() }
        }
    }

    private fun clientModule(): Module {
        return module {
            single { DatabaseClient }
        }
    }

    private fun serviceModule(): Module {
        return module {
            single { ClientService(get()) }
            single { UserService(get()) }
            single { ReportService(get()) }
            single { ExpensesService(get()) }
            single { ProductService(get()) }
            single { AugmentedRealityService(get()) }
            single { BillService(get(), get()) }
            single { BillDetailService(get(), get()) }
            single { CurrentAccountClientService(get(), get()) }
        }
    }

    private fun repositoryModule(): Module {
        return module {
            single { ClientRepository() }
            single { UserRepository() }
            single { ReportRepository() }
            single { ProductRepository() }
            single { AugmentedRealityRepository() }
            single { BillRepository() }
            single { ExpensesRepository() }
            single { BillDetailRepository() }
            single { CurrentAccountClientRepository() }
        }
    }
}