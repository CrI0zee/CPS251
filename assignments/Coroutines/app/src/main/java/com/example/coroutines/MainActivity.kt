package com.example.coroutines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutines.databinding.ActivityMainBinding
import com.example.coroutines.R
import kotlin.math.log


import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private var counter: Int = 0
    val names: MutableList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        //val names: MutableList<String> = ArrayList()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        layoutManager = LinearLayoutManager(this)
        binding.contentMain.recyclerView.layoutManager = layoutManager
        adapter = RecyclerAdapter(names)
        binding.contentMain.recyclerView.adapter = adapter

    }
    fun launchCoroutines(view: View) {
        counter++
        coroutineScope.launch(Dispatchers.Main){
            var string = performTaskAsync(counter).await()
            names.add(string)
            println(string)
            adapter?.notifyDataSetChanged()
        }
    }
    private suspend fun performTaskAsync(tasknumber: Int): Deferred<String> =
        coroutineScope.async(Dispatchers.Main) {
            val ran = Random.nextLong(1000, 10000)
            delay(ran)
            val string = "The name is "+binding.contentMain.name.text.toString()+" And the delay was " + ran + "milliseconds"
            return@async string
        }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


}