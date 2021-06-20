package br.com.recyclerviewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class MainActivity : AppCompatActivity(), OnItemClickListener {

    private val list = generateDummyList(500)
    private val adapter = ItemAdapter(list, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }

    fun insertItem(view: View){
        val index: Int = Random.nextInt(8)
        val newItem = Item(R.drawable.ic_android_black_24dp,
            "New item at position $index",
            "Line 2")
        list.add(index, newItem)
        adapter.notifyItemInserted(index)
    }

    fun removeItem(view: View){
        val index: Int = Random.nextInt(8)
        list.removeAt(index)
        adapter.notifyItemRemoved(index)
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Item $position clicked", Toast.LENGTH_SHORT).show()
        val clickedItem: Item = list[position]
        clickedItem.text2 = "Clicked"
        adapter.notifyItemChanged(position)
    }

    private fun generateDummyList(size: Int): ArrayList<Item>{
        val list = ArrayList<Item>()
        for(i in 0 until size){
            val drawable = when((0..2).random()){
                0-> R.drawable.ic_android_black_24dp
                1-> R.drawable.ic_baseline_ac_unit_24
                else-> R.drawable.ic_baseline_adb_24
            }
            val item = Item(drawable, "Item $i", "Line 2")
            list.add(item)
        }
        return list
    }
}