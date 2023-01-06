package com.example.wisdomleaf.di

import com.example.wisdomleaf.data.datasource.DataSource
import com.example.wisdomleaf.domain.repo.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {
    @Provides
    @Singleton
    fun providesAppRepository(coinListDataSource: DataSource)
            : AppRepository {
        return AppRepository(coinListDataSource)
    }
}