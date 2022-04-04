package com.generation.todo.fragment

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

class DatePickerFragment(
    private val timePickerListener: TimePickerListener
) : DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //Recupera data atual quando o calendário for aberto
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        //Cria e retorna uma instância do DatePickerDialog
        return DatePickerDialog(
            requireContext(),
            this,
            year, month, day
        )

    }

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, p1)
        calendar.set(Calendar.MONTH, p2)
        calendar.set(Calendar.DAY_OF_MONTH, p3)
        timePickerListener.onTimeSelected(calendar.time.toInstant().atZone(
            ZoneId.systemDefault()).toLocalDate())
    }


}

interface TimePickerListener {
    fun onTimeSelected(date: LocalDate)
}