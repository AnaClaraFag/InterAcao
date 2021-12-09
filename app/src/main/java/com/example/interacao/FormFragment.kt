package com.example.interacao

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.interacao.databinding.FragmentFormBinding
import com.example.interacao.fragment.DatePickerFragment
import com.example.interacao.fragment.TimePickerListener
import com.example.interacao.model.Tarefas
import com.example.interacao.util.format
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import java.text.SimpleDateFormat
import java.util.*

class FormFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private val mainViewModel: MainViewModel by activityViewModels()
    private var _tarefaSelecionada: Tarefas? = null
    private val tarefaSelecionada get() = _tarefaSelecionada!!

    private var _binding: FragmentFormBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentFormBinding.inflate(inflater, container, false)

        carregarDados()

        mainViewModel.selectedDateLiveData.observe(viewLifecycleOwner, {
            selectedDate -> binding.editData.setText(selectedDate.toString())
        })

        DatePicker()

        binding.buttonAdd.setOnClickListener {
            inserirNoBanco()
        }

        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    private fun inputCheck(
        nome: String, desc: String, dono: String,
        data: String, status: String
    ): Boolean{
        return !(TextUtils.isEmpty(nome) &&
                TextUtils.isEmpty(desc) &&
                TextUtils.isEmpty(dono) &&
                TextUtils.isEmpty(data) &&
                TextUtils.isEmpty(status)
                )
    }

    fun inserirNoBanco(){
        val nome = "Luan 13A"
        val desc = binding.editDesc.text.toString()
        val dono = binding.editDono.text.toString()
        val data = binding.editData.text.toString()
        val hora = binding.editHora.text.toString()
        val dataHora = "${data} ${hora}:00"
        val status = binding.editStatus.text.toString()

        if(inputCheck(nome, desc, dono, dataHora, status)){
            _tarefaSelecionada = mainViewModel.tarefaSelecionada
            var atualizarCriar = ""
            if (_tarefaSelecionada != null) {
                val tarefas = Tarefas(tarefaSelecionada.id, nome, desc, dono,
                    dataHora,
                    status
                )
                mainViewModel.updateTarefa(tarefas)
                atualizarCriar = "Tarefa Atualizada!"
            }else{
                val tarefas = Tarefas(0, nome, desc, dono,
                    dataHora,
                    status
                )
                mainViewModel.addTarefa(tarefas)
                atualizarCriar = "Tarefa Adicionada!"
            }
            Toast.makeText(
                context, atualizarCriar,
                Toast.LENGTH_LONG
            ).show()

            findNavController().navigate(R.id.action_formFragment_to_listFragment)
        }else{
            Toast.makeText(
                context, "Preencha todos os campos!",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun carregarDados() {
        _tarefaSelecionada = mainViewModel.tarefaSelecionada
        if (_tarefaSelecionada != null) {
            //binding.editNome.setText(tarefaSelecionada.name)
            binding.editDesc.setText(tarefaSelecionada.description)
            binding.editDono.setText(tarefaSelecionada.assignetTo)
            binding.editData.setText(tarefaSelecionada.dueDate)
            binding.editStatus.setText(tarefaSelecionada.status)
        } else {
            //binding.editNome.text = null
            //binding.editDesc.text = null
            binding.editDono.text = null
            binding.editData.text = null
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun DatePicker() {
        binding.editData.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .build()
            datePicker.addOnPositiveButtonClickListener {
                binding.editData.setText((Date(it).format().toString()))
            }
            datePicker.show(parentFragmentManager, "DatePicker")
        }

        binding.editHora.setOnClickListener {
            val timePicker=  MaterialTimePicker.Builder().build()
            timePicker.addOnPositiveButtonClickListener {
                val hora=    if(timePicker.hour in 0..9)"0${timePicker.hour.toString()} " else "${timePicker.hour}"
                var minuto = if(timePicker.minute in 0..9)"0${timePicker.minute.toString()}"
                else "${timePicker.minute.toString()}"
                val horaMinuto=" ${hora }:${minuto}"
                binding.editHora.setText(horaMinuto)
            }
            timePicker.show(parentFragmentManager,"TimePicker")
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val itemAtPosition: String = p0?.getItemAtPosition(p2) as String
        Log.d("Developer", "itemAtPosition: $itemAtPosition")
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }



}