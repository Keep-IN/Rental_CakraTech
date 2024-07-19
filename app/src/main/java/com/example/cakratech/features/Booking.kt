package com.example.cakratech.features

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.viewModelScope
import com.example.cakratech.R
import com.example.cakratech.databinding.ActivityBookingBinding
import com.example.core.data.network.Result
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class Booking : AppCompatActivity() {
    private lateinit var binding: ActivityBookingBinding
    private var dataDateStart: String? = null
    private var dataDateEnd: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBookingBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        binding.root.applySystemWindowInsets()

        val constraintsBuilder = CalendarConstraints.Builder()
            .setStart(MaterialDatePicker.todayInUtcMilliseconds())
            .setValidator(DateValidatorPointForward.now())

        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Pilih Tanggal")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .setCalendarConstraints(constraintsBuilder.build())
                .build()

        binding.tilDateStart.editText?.setOnClickListener {
            datePicker.show(supportFragmentManager, "date_picker")
            datePicker.addOnPositiveButtonClickListener {
                dataDateStart = SimpleDateFormat("dd/MM/yyyy", Locale.US)
                    .format(Date(it))
                binding.tilDateStart.editText?.setText(dataDateStart)
            }
        }

        binding.tilDateEnd.editText?.setOnClickListener {
            datePicker.show(supportFragmentManager, "date_picker")
            datePicker.addOnPositiveButtonClickListener {
                dataDateEnd = SimpleDateFormat("dd/MM/yyyy", Locale.US)
                    .format(Date(it))
                binding.tilDateEnd.editText?.setText(dataDateEnd)
            }
        }

        binding.cvWithDriver.setOnClickListener {
            binding.apply {
                cvWithDriver.setCardBackgroundColor(Color.parseColor("#FF723B"))
                cvWithoutDriver.setCardBackgroundColor(Color.parseColor("#E6E6E6"))
                cvAuto.setCardBackgroundColor(Color.parseColor("#FF723B"))
                cvMatic.setCardBackgroundColor(Color.parseColor("#E6E6E6"))
                tvWithDriver.setTextColor(Color.parseColor("#FFFFFF"))
                tvWithoutDriver.setTextColor(Color.parseColor("#2B2B2B"))
                tvAuto.setTextColor(Color.parseColor("#FFFFFF"))
                tvMatic.setTextColor(Color.parseColor("#2B2B2B"))
                clMT.visibility = View.GONE
            }
        }

        binding.cvWithoutDriver.setOnClickListener {
            binding.apply {
                cvWithDriver.setCardBackgroundColor(Color.parseColor("#E6E6E6"))
                cvWithoutDriver.setCardBackgroundColor(Color.parseColor("#FF723B"))
                tvWithDriver.setTextColor(Color.parseColor("#2B2B2B"))
                tvWithoutDriver.setTextColor(Color.parseColor("#FFFFFF"))
                clMT.visibility = View.VISIBLE
            }
        }

        binding.cvAuto.setOnClickListener {
            binding.apply {
                cvAuto.setCardBackgroundColor(Color.parseColor("#FF723B"))
                cvMatic.setCardBackgroundColor(Color.parseColor("#E6E6E6"))
                tvAuto.setTextColor(Color.parseColor("#FFFFFF"))
                tvMatic.setTextColor(Color.parseColor("#2B2B2B"))
            }
        }

        binding.cvMatic.setOnClickListener {
            binding.apply {
                cvAuto.setCardBackgroundColor(Color.parseColor("#E6E6E6"))
                cvMatic.setCardBackgroundColor(Color.parseColor("#FF723B"))
                tvAuto.setTextColor(Color.parseColor("#2B2B2B"))
                tvMatic.setTextColor(Color.parseColor("#FFFFFF"))
            }
        }
        binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
            binding.btnBooking.isEnabled = isChecked
        }
        binding.btnBooking.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
    private fun View.applySystemWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}