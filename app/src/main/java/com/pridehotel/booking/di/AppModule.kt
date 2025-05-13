package com.pridehotel.booking.di

import com.pridehotel.booking.data.repository.HotelRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideHotelRepository(): HotelRepository = HotelRepository()
}