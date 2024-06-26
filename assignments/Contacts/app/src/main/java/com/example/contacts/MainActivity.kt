package com.example.contacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.contacts.databinding.ActivityMainBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var adapter: ContactListAdapter? = null
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listenerSetup()
        observerSetup()
        recyclerSetup()
    }

    private fun clearFields() {

        binding.contactName.setText("")
        binding.contactNumber.setText("")
    }

    private fun listenerSetup() {
        binding.addButton.setOnClickListener {
            val name = binding.contactName.text.toString()
            val number = binding.contactNumber.text.toString()

            if (name != "" && number != "") {
                val contact = Contact(name, number)
                viewModel.insertContact(contact)
                clearFields()
            }else{
                Toast.makeText(this, "Incomplete information", Toast.LENGTH_LONG).show()
            }
        }

        binding.findButton.setOnClickListener {
            viewModel.findContact(binding.contactName.text.toString())

        }

        binding.ascButton.setOnClickListener {
            viewModel.getAllContactsASC()?.observe(this) { contacts ->
                contacts?.let {
                    adapter?.setContactList(it)
                }
            }
        }

        binding.descButton.setOnClickListener {
            viewModel.getAllContactsDesc()?.observe(this) { contacts ->
                contacts?.let {
                    adapter?.setContactList(it)
                }
            }
        }
    }


    private fun observerSetup() {
        viewModel.getAllContacts()?.observe(this) { contacts ->
            contacts?.let {
                adapter?.setContactList(it)
            }
        }
        viewModel.getSearchResults().observe(this) { contacts ->
            contacts?.let {
                if (it.isNotEmpty()) {
                    binding.contactName.setText(it[0].contactName)
                    binding.contactNumber.setText(it[0].contactPhone)
                }
            }
        }
    }

    private fun recyclerSetup() {
        adapter = ContactListAdapter(R.layout.card_layout)
        binding.contactRecycler.layoutManager = LinearLayoutManager(this)
        binding.contactRecycler.adapter = adapter
    }






}