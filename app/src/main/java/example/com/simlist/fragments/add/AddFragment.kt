package example.com.simlist.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import example.com.simlist.R
import example.com.simlist.data.User
import example.com.simlist.data.UserViewModel
import example.com.simlist.databinding.FragmentAddBinding

class AddFragment : Fragment(R.layout.fragment_add) {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        binding.addNumberAge.setOnClickListener{
            insertDataToDatabase()
        }
        return binding.root

    }

    private fun insertDataToDatabase() {
        val firstName= binding.addTxtFirstName.text.toString()
        val lastName = binding.addTxtSecondName.text.toString()
        val age = binding.addNumberAge.text

        if(inputCheck(firstName,lastName,age)){
            //Create User Object
            val user = User(0,firstName,lastName,Integer.parseInt(age.toString()))
            //Add data to database
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(),"Successfully Added!!!",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Please fill required fields",Toast.LENGTH_SHORT).show()

        }

    }
    private fun inputCheck(firstName:String,secondName:String,age:Editable):Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(secondName) && age.isEmpty())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}