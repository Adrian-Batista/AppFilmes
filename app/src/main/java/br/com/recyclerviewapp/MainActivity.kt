package br.com.recyclerviewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.recyclerviewapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnItemClickListener {

    private val list = generateDummyList(5)
    private val adapter = ItemAdapter(list, this)
    private var auxIndex = -1

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)
    }

    fun criaItem():Item?{
        var aux: Int = 0
        val titulo: EditText = findViewById(R.id.tituloEditText)
        val txtTitulo = titulo.text.toString()
        val genero: EditText = findViewById(R.id.generoText)
        val txtGenero = genero.text.toString()
        val ano: EditText = findViewById(R.id.anoText)
        var txtAno: Int = 0

        if (txtTitulo.isEmpty() or txtGenero.isEmpty()){
            Toast.makeText(this, "Preencha o TÍTULO e GÊNERO do Filme!", Toast.LENGTH_SHORT).show()
            aux++
        }
        if (ano.text.toString().length !=4){
            Toast.makeText(this, "O campo ANO aceita exatos 4 digitos!", Toast.LENGTH_SHORT).show()
            aux++
        }else{
            txtAno = ano.text.toString().toInt()
        }
        if(aux == 0){
            val newItem = Item(R.drawable.filme, txtTitulo, txtGenero, txtAno)
            return newItem
        }
        return null
    }

    fun insertItem(view: View){
        val item: Item? = criaItem()
        if(item != null){
            list.add(item)
            adapter.notifyItemInserted(list.size)
            Toast.makeText(this, "Filme criado com Sucesso!", Toast.LENGTH_SHORT).show()
        }
    }

    fun editItem(view: View){
        if(auxIndex != -1){
            val editItem: Item? = criaItem()
            if(editItem != null){
                val index: Int = auxIndex
                list[index] = editItem
                adapter.notifyItemChanged(index)
                Toast.makeText(this, "Item $index Editado!", Toast.LENGTH_SHORT).show()
            }

        }else{
            Toast.makeText(this, "Selecione um Filme para editar!", Toast.LENGTH_SHORT).show()
        }
        auxIndex = -1
    }

    fun removeItem(view: View){
        if(auxIndex != -1){
            val index: Int = auxIndex
            list.removeAt(index)
            adapter.notifyItemRemoved(index)
            Toast.makeText(this, "Item $index Selecionado!", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "Selecione um Filme para remover!", Toast.LENGTH_SHORT).show()
        }
        auxIndex = -1
    }

    override fun onItemClick(position: Int) {
        val item: Item = list[position]
        val titulo: TextView = findViewById(R.id.tituloEditText)
        titulo.setText(item.text1)
        val genero: TextView = findViewById(R.id.generoText)
        genero.setText(item.text2)
        /*val ano: TextView = findViewById(R.id.anoText)
        ano.setText(item.text3)*/

        Toast.makeText(this, "Item $position selecionado!", Toast.LENGTH_SHORT).show()
        auxIndex = position
    }

    private fun generateDummyList(size: Int): ArrayList<Item>{
        val list = ArrayList<Item>()
        for(i in 0 until size){
            val item = Item(R.drawable.filme, "Filme $i", "Gênero", 2000)
            list.add(item)
        }
        return list
    }
}