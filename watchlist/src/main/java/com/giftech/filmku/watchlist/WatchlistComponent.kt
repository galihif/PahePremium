package com.giftech.filmku.watchlist

import android.content.Context
import com.giftech.filmku.di.WatchlistModuleDependencies
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [WatchlistModuleDependencies::class])
interface WatchlistComponent {

    fun inject(activity: WatchlistActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(watchlistModuleDependencies: WatchlistModuleDependencies): Builder
        fun build(): WatchlistComponent
    }

}