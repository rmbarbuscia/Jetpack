package com.generation.todo

import android.animation.Animator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.generation.todo.adapter.TarefaAdapter
import com.generation.todo.adapter.TaskItemClickListener
import com.generation.todo.databinding.FragmentListBinding
import com.generation.todo.model.Tarefa
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment : Fragment(), TaskItemClickListener {

    private lateinit var binding: FragmentListBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        mainViewModel.listTarefas()

        binding = FragmentListBinding.inflate(layoutInflater, container, false)



        //Instaciar o adapter
        val adapter = TarefaAdapter(this,mainViewModel)

        //Definir o Layout Manager da RecyclerView
        binding.recyclerTarefa.layoutManager = LinearLayoutManager(context)

        //Passar o adapter criado para o recyclerTarefa
        binding.recyclerTarefa.adapter = adapter

        //Definir a lista para ter um tamanho fixo indepedente dos itens
        binding.recyclerTarefa.setHasFixedSize(true)

        //Navegação para o Fragment de Form
        binding.floatingAdd.setOnClickListener {
            mainViewModel.tarefaSelecionada = null
            findNavController().navigate(R.id.action_listFragment_to_formFragment)
        }

        mainViewModel.myTarefaResponse.observe(viewLifecycleOwner){
            response -> if (response.body() != null){
                adapter.setLista(response.body()!!)
            }
        }

        return binding.root
    }


    override fun onTaskClicked(tarefas: Tarefa) {
       mainViewModel.tarefaSelecionada = tarefas
       findNavController().navigate(R.id.action_listFragment_to_formFragment)
    }

}