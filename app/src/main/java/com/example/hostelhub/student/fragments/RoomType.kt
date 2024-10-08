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
import com.example.hostelhub.student.StudentDashboard.Companion.roomType


class RoomType : Fragment() {
    private lateinit var sharedPreferences: SharedPreferences
    private val PREF_NAME = "my_settings"
    lateinit var viewRoomType: View
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
        viewRoomType =  inflater.inflate(R.layout.fragment_room_type, container, false)

        rgrp = viewRoomType.findViewById(R.id.rgrp)




        nextButton = viewRoomType.findViewById(R.id.next_button)
        nextButton.setOnClickListener(){
            var selectId = rgrp.checkedRadioButtonId
            if (selectId == -1){
                Toast.makeText(requireContext(),"Please select any one!!",Toast.LENGTH_SHORT).show()
            } else {
                val rB = rgrp.findViewById<RadioButton>(selectId)
                roomType = rB.text.toString()
                sharedPreferences = requireActivity().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
                with(sharedPreferences.edit()) {
                    putString("roomType", StudentDashboard.roomType)
                    putBoolean("roomType_selected", true) // Mark that room type is selected
                    apply()
                }
                // change fragment
                val seaterFragment = SeaterFragment()
                requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragment_container,seaterFragment).commit()

            }
        }
        return viewRoomType
    }


}