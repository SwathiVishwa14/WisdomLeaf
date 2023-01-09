package com.example.wisdomleaf.di

import android.content.Context
import com.example.wisdomleaf.data.datasource.DataSource
import com.example.wisdomleaf.data.datasource.DataSourceImpl
import com.example.wisdomleaf.data.mapper.BookListResponseMapper
import com.example.wisdomleaf.domain.WebService
import com.example.wisdomleaf.domain.retrofit.RetrofitHelper
import com.example.wisdomleaf.domain.retrofit.RetrofitHelperImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @AppRetrofit
    @Singleton
    @Provides
    fun provideRetrofitBuilder(
        gson: Gson,
        @ApplicationContext context: Context
    ): Retrofit.Builder {

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(interceptor)
            .build()
        val baseUrl = "https://picsum.photos/v2/"

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideBookService(@AppRetrofit retrofit: Retrofit.Builder): WebService {
        return retrofit
            .build()
            .create(WebService::class.java)
    }

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    fun provideBookRetrofitService(
        WebServices: WebService
    ): RetrofitHelper {
        return RetrofitHelperImpl(WebServices)
    }

    @Provides
    @Singleton
    fun providesBooksDataSource(
        retrofitService: RetrofitHelper,
        booksListResponseMapper: BookListResponseMapper
    ): DataSource {
        return DataSourceImpl(retrofitService, booksListResponseMapper)
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AppRetrofit
