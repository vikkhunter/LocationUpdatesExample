package com.example.locationupdatesexample.sharedviewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.locationupdatesexample.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment: Fragment() {
    val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.first_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonPostValue = view.findViewById<Button>(R.id.btn_post_value)
        buttonPostValue.setOnClickListener {
            viewModel.postValue("Your life is going to change soon!!!")
        }
    }

}