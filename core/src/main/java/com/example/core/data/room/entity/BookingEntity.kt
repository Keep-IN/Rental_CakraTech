package com.example.core.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookings")
data class BookingEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0, // Auto-generated unique ID
    val codeBook: String,
    val vehicleName: String,
    val vehicleImage: String,
    val dateStart: String,
    val dateEnd: String
)
