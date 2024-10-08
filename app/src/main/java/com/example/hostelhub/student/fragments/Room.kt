package com.example.hostelhub.student.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.example.hostelhub.R
import com.example.hostelhub.student.StudentDashboard


class Room : Fragment() {
    private lateinit var sharedPreferences: SharedPreferences
    private val PREF_NAME = "my_settings"
    lateinit var viewRoom: View
    lateinit var rgrp : RadioGroup
    lateinit var nextButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewRoom = inflater.inflate(R.layout.fragment_room, container, false)

        rgrp = viewRoom.findViewById(R.id.rgrp)

        nextButton = viewRoom.findViewById(R.id.next_button)
        nextButton.setOnClickListener() {
            var selectId = rgrp.checkedRadioButtonId
            if (selectId == -1) {
                Toast.makeText(requireContext(), "Please select any one!!", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val rB = rgrp.findViewById<RadioButton>(selectId)
                StudentDashboard.room = rB.text.toString()
                sharedPreferences = requireActivity().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
                with(sharedPreferences.edit()) {
                    putString("room", StudentDashboard.room)
                    putBoolean("room_selected", true) // Mark that room is selected
                    apply()
                }
                // change fragment
                val bed = Bed()
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, bed).commit()

            }
        }
        return viewRoom
    }


}