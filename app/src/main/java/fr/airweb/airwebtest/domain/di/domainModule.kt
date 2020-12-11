package fr.airweb.airwebtest.domain.di

import fr.airweb.airwebtest.domain.usescases.FetchPsgNewsByTypeFromLocal
import fr.airweb.airwebtest.domain.usescases.FetchPsgNewsFromLocal
import fr.airweb.airwebtest.domain.usescases.FetchPsgNewsFromRemote
import fr.airweb.airwebtest.domain.usescases.SavePsgNewsInLocal
import org.koin.dsl.module

val domainModule = module {
    factory { FetchPsgNewsFromRemote(get()) }
    factory { FetchPsgNewsByTypeFromLocal(get()) }
    factory { FetchPsgNewsFromLocal(get()) }
    factory { SavePsgNewsInLocal(get()) }

}