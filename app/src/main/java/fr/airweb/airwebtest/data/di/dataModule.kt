package fr.airweb.airwebtest.data.di

import fr.airweb.airwebtest.data.mappers.NewsEntityMapper
import fr.airweb.airwebtest.data.mappers.NewsModelMapper
import fr.airweb.airwebtest.data.repositories.PsgRepositoryImpl
import fr.airweb.airwebtest.data.sources.NewsLocalSourceImpl
import fr.airweb.airwebtest.data.sources.PsgSourceImpl
import fr.airweb.airwebtest.db.AppDatabase
import fr.airweb.airwebtest.domain.repositories.PsgRepository
import fr.airweb.airwebtest.domain.sources.NewsLocalDataSource
import fr.airweb.airwebtest.domain.sources.PsgRemoteDataSource
import org.koin.dsl.module

val dataModule = module {
    single { AppDatabase.getInstance(get()) }
    factory { NewsModelMapper() }
    factory { NewsEntityMapper() }
    single<PsgRepository> { PsgRepositoryImpl(get(), get()) }
    single<PsgRemoteDataSource> { PsgSourceImpl(get(), get()) }
    single<NewsLocalDataSource> { NewsLocalSourceImpl(get(), get()) }
}