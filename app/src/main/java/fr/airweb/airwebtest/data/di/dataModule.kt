package fr.airweb.airwebtest.data.di

import fr.airweb.airwebtest.data.mappers.NewsEntityMapper
import fr.airweb.airwebtest.data.mappers.NewsModelMapper
import fr.airweb.airwebtest.data.repositories.NewsRepositoryImpl
import fr.airweb.airwebtest.data.sources.NewsLocalSourceImpl
import fr.airweb.airwebtest.data.sources.NewsRemoteSourceImpl
import fr.airweb.airwebtest.db.AppDatabase
import fr.airweb.airwebtest.domain.repositories.NewsRepository
import fr.airweb.airwebtest.domain.sources.NewsLocalDataSource
import fr.airweb.airwebtest.domain.sources.PsgRemoteDataSource
import org.koin.dsl.module

val dataModule = module {
    single { AppDatabase.getInstance(get()) }
    factory { NewsModelMapper() }
    factory { NewsEntityMapper() }
    single<NewsRepository> { NewsRepositoryImpl(get(), get()) }
    single<PsgRemoteDataSource> { NewsRemoteSourceImpl(get(), get()) }
    single<NewsLocalDataSource> { NewsLocalSourceImpl(get(), get()) }
}