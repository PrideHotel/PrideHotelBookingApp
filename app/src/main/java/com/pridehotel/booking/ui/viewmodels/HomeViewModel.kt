package com.pridehotel.booking.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.pridehotel.booking.data.models.Hotel
import com.pridehotel.booking.data.repository.HotelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val hotelRepository: HotelRepository
) : ViewModel() {
    val hotels: List<Hotel> = hotelRepository.getHotels()
}