// HomeViewModel.kt
package com.pridehotel.booking.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.pridehotel.booking.data.models.Hotel
import com.pridehotel.booking.data.repository.HotelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel // Added HiltViewModel annotation
class HomeViewModel @Inject constructor( // Added @Inject constructor
    private val hotelRepo: HotelRepository
) : ViewModel() {
    // This assumes getHotels() is a non-suspending function.
    // If getHotels() is a suspend function or returns a Flow, this would need to be handled differently (e.g., using viewModelScope, StateFlow).
    val hotels: List<Hotel> = hotelRepo.getHotels()
}

