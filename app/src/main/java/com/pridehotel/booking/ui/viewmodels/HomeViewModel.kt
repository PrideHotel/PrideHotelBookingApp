// HomeViewModel.kt
package com.pridehotel.booking.ui.viewmodels

import androidx.lifecycle.ViewModel
// Corrected import path for Hotel model
import com.pridehotel.booking.data.models.Hotel
import com.pridehotel.booking.data.repository.HotelRepository

class HomeViewModel(
    private val hotelRepo: HotelRepository
) : ViewModel() {
    // This assumes getHotels() is a non-suspending function.
    // If getHotels() is a suspend function or returns a Flow, this would need to be handled differently (e.g., using viewModelScope, StateFlow).
    val hotels: List<Hotel> = hotelRepo.getHotels()
}

