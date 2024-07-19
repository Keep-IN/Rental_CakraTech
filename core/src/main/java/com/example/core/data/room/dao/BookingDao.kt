package com.example.core.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.core.data.room.entity.BookingEntity

@Dao
interface BookingDao {
    @Insert
    suspend fun insertBooking(booking: BookingEntity)

    @Query("SELECT * FROM bookings")
    fun getAllBookings(): LiveData<List<BookingEntity>>

    @Query("DELETE FROM bookings")
    suspend fun clearAllBookings()
}
