package com.example.weighttracker.di

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.example.weighttracker.data.local.AppDatabase
import com.example.weighttracker.data.repository.WeightRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DATASTORE_NAME = "user_prefs"
val Context.dataStore by preferencesDataStore(name = DATASTORE_NAME)

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "weights.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideWeightRepository(db: AppDatabase, @ApplicationContext context: Context): WeightRepository =
        WeightRepository(db.weightEntryDao(), context.dataStore)
}