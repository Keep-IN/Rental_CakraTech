package com.example.core.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.core.data.network.Result
import com.example.core.data.reqres.CarData
import com.example.core.data.room.dao.BookingDao
import com.example.core.data.room.entity.BookingEntity
import com.example.core.di.ApiContract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val apiContract: ApiContract,
    private val bookingDao: BookingDao
) {
    fun getCarData(): LiveData<Result<CarData>> = liveData(Dispatchers.IO) {
        emit(Result.Loading)
        try {
            val response = apiContract.getCarData()
            val responseBody = response.body()
            if (response.isSuccessful && responseBody != null) {
                emit(Result.Success(responseBody))
                withContext(Dispatchers.IO) {
                    deleteAllBookings()
                    parseAndInsertBookings(responseBody)
                }
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMessage = try {
                    JSONObject(errorBody).getString("message")
                } catch (e: JSONException) {
                    "Unknown Error Occurred"
                }
                emit(Result.Error(errorMessage))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "An error occurred"))
        }
    }

    private suspend fun parseAndInsertBookings(carData: CarData) {
        carData.record.bookings.let { bookingsArray ->
            for (booking in bookingsArray) {
                val bookingEntity = BookingEntity(
                    codeBook = booking.codeBook,
                    vehicleName = booking.vehicleName,
                    vehicleImage = booking.vehicleImage,
                    dateStart = booking.dateStart,
                    dateEnd = booking.dateEnd
                )
                bookingDao.insertBooking(bookingEntity)
            }
        }
    }

    fun getAllBookings(): LiveData<List<BookingEntity>> = bookingDao.getAllBookings()

    private suspend fun deleteAllBookings() {
        withContext(Dispatchers.IO) {
            bookingDao.clearAllBookings()
        }
    }
}
