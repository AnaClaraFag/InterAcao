package com.example.interacao


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation



class TelaLogoFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_tela_logo, container, false)

        val buttonNavSeg = view.findViewById<Button>(R.id.button_Logo)

        buttonNavSeg.setOnClickListener(){
            Navigation.findNavController(view).navigate(R.id.action_telaLogoFragment_to_telaLogin)
        }
        return view
    }
}

    /*fun goToNextScreen() {
        findNavController().navigate(R.id.action_telaLogoFragment_to_telaLogin)
    }*/
