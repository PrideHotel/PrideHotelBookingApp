package com.pridehotel.booking.data.repository

import com.pridehotel.booking.data.models.Hotel
import javax.inject.Inject // Added for Hilt

class HotelRepository @Inject constructor() { // Added @Inject constructor
    fun getHotels(): List<Hotel> {
        return listOf(
            Hotel("5286", "Pride Plaza Ahmedabad", "Ahmedabad", "https://assets.simplotel.com/simplotel/image/upload/pride-plaza-ahmedabad/1_(1)_fd98666c"),
            Hotel("9954", "Biznotel by Pride Motera, Ahmedabad", "Ahmedabad", "https://assets.simplotel.com/simplotel/image/upload/biznotel-by-pride-motera-ahmedabad/motera-banner_2721c22a"),
            Hotel("5262", "Pride Hotel, Bangalore", "Bangalore", "https://assets.simplotel.com/simplotel/image/upload/pride-hotel-bengaluru/Hotel_Exterior_Bldg_qzx0kh"),
            // Add more hotels from the dropdown as needed
        )
    }
}
