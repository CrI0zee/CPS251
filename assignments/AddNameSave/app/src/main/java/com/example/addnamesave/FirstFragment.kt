package com.example.addnamesave

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.addnamesave.BR.myViewModel


import androidx.navigation.fragment.findNavController
import com.example.addnamesave.databinding.FragmentFirstBinding
import androidx.lifecycle.ViewModelProvider
/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {



    // This property is only valid between onCreateView and
    // onDestroyView.


    private lateinit var viewModel: MainViewModel
    lateinit var binding: FragmentFirstBinding
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_first, container, false)
        binding.lifecycleOwner = this

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    binding.setVariable(myViewModel, viewModel)




    }


}