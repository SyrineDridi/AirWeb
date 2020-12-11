package fr.airweb.airwebtest.di

import fr.airweb.airwebtest.ui.NewsViewModel
import org.koin.dsl.module

val appModule = module {
    single { NewsViewModel(get(), get(), get(), get()) }
}