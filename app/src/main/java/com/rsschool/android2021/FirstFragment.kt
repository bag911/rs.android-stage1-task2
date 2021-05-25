package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.lang.Exception

class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var listener: GenerationNumber? = null
    private var minEditText:EditText? = null
    private var maxEditText:EditText? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as GenerationNumber
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)
        minEditText = view.findViewById(R.id.min_value)
        maxEditText = view.findViewById(R.id.max_value)
        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"


        generateButton?.setOnClickListener {
            
            if(minEditText?.text?.isNotEmpty()==true && maxEditText?.text?.isNotEmpty()==true){
               try {
                   val min = minEditText?.text.toString().toInt()
                   val max = maxEditText?.text.toString().toInt()
                   if(max>min){
                       if(max<=Int.MAX_VALUE && min<=Int.MAX_VALUE){
                           listener?.generate(min,max)
                       }
                   } else {
                       Toast.makeText(activity?.applicationContext, "MAX should be bigger then MIN", Toast.LENGTH_SHORT).show()
                   }
               }
               catch (e:Exception){
                   Toast.makeText(activity?.applicationContext, "Integer overflow", Toast.LENGTH_SHORT).show()
               }
            } else {
                Toast.makeText(activity?.applicationContext, "EditText is empty", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }

    interface GenerationNumber{
        fun generate(min:Int,max:Int)
    }
}