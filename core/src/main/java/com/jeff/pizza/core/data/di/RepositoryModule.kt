package com.jeff.pizza.core.data.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.jeff.pizza.core.data.repository.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    private const val PREFERENCE_FILE_KEY = "preferenceFileKey"

    @Singleton
    @Provides
    fun providesSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
            context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
                appContext,
                AppDatabase::class.java,
                "AppDatabase"
        ).build()
    }
}