package com.example.deskbookingappllication.ui

import android.content.Context
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.deskbookingappllication.R
import com.example.deskbookingappllication.databinding.FragmentBookingBinding
import com.example.deskbookingappllication.model.Book
import com.example.deskbookingappllication.model.Desk
import com.example.deskbookingappllication.model.viewModels.BookingViewModel
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.gson.Gson
import java.util.*

class BookingFragment : Fragment() {

    private var _binding: FragmentBookingBinding? = null
    private val binding get() = _binding!!
    private val args: BookingFragmentArgs by navArgs()
    private val bookingViewModel: BookingViewModel by activityViewModels()
    private lateinit var book: Book
    private var startDate: Long = 0
    private var endDate: Long = 0
    private val dpTag = "BookingFragmentDatePicker"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentBookingBinding.inflate(inflater, container, false)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val deskArgs = args.id
        val desk = Gson().fromJson(deskArgs, Desk::class.java)
        Glide.with(binding.root).load(desk.map).into(binding.bookingDeskIv)
        val deskId = desk.desk_id
        val today = MaterialDatePicker.todayInUtcMilliseconds()
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        calendar.timeInMillis = today
        // Build constraints.
        val constraintsBuilder =
            CalendarConstraints.Builder().setValidator(DateValidatorPointForward.now())

        val datePicker =
            MaterialDatePicker.Builder.dateRangePicker()
                .setTitleText("Pick date to MAX. 3 Days")
                .setCalendarConstraints(constraintsBuilder.build())
                .build()


        binding.btnDatePickBooking.setOnClickListener {
            datePicker.show(parentFragmentManager, dpTag)
        }

        datePicker.addOnPositiveButtonClickListener { datePicked ->
            startDate = datePicked.first
            endDate = datePicked.second

        }
        binding.btnBookingBook.setOnClickListener {
            book = Book(deskId, convertLongToDate(startDate), convertLongToDate(endDate))
            bookingViewModel.booking(book)
            bookingViewModel.statusCode.observe(viewLifecycleOwner) {
                when (it) {
                    204 -> {
                        Toast.makeText(context, "Flex desk booked", Toast.LENGTH_LONG).show()
                        Navigation.findNavController(binding.root).navigate(R.id.offices)
                    }
                    400 -> {
                        Toast.makeText(
                            context,
                            "Request body invalid", Toast.LENGTH_LONG
                        ).show()
                    }
                    401 -> {
                        Toast.makeText(context, "Authentication not valid", Toast.LENGTH_LONG)
                            .show()
                    }
                    404 -> {
                        Toast.makeText(
                            context,
                            "Desk with the given deskId not found",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                    409 -> {
                        Toast.makeText(
                            context,
                            "The desk with the given id is either already booked as a flex desk for this day, or is a fix-desk, or the date range is more than 3 days.",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                }
            }
        }
    }

    private fun convertLongToDate(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return format.format(date)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true)
            {
                override fun handleOnBackPressed() {
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            callback
        )
    }
}