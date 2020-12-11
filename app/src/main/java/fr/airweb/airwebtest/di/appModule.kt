package fr.airweb.airwebtest.di

import fr.airweb.airwebtest.ui.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { NewsViewModel(get(), get(), get(), get()) }
}