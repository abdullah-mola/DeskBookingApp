package com.example.deskbookingappllication.ui

import android.os.Bundle
import android.text.Editable
import android.text.Editable.Factory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.deskbookingappllication.R
import com.example.deskbookingappllication.api.RetrofitInstance
import com.example.deskbookingappllication.databinding.FragmentUserProfileBinding
import com.example.deskbookingappllication.model.User
import com.example.deskbookingappllication.model.viewModels.UserViewModel


class UserProfileFragment : Fragment() {
    private var _binding: FragmentUserProfileBinding? = null
    private val binding get() = _binding!!
    private val userViewModel: UserViewModel by activityViewModels()
    private lateinit var user: User
    var admin: Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        val userId: String = RetrofitInstance.userId.toString()
        userViewModel.loadUser(userId)
        val passwordLayout = binding.tilPassword
        userViewModel.user.observe(viewLifecycleOwner) {
            binding.etProfileEmail.text = Factory.getInstance().newEditable(it.email)
            binding.etProfileFirstname.text =
                Factory.getInstance().newEditable(it.firstname)
            binding.etProfileLastname.text = Factory.getInstance().newEditable(it.lastname)
            binding.etProfileDepartment.text =
                Factory.getInstance()
                    .newEditable(it.department)

//            var admin: Editable? = Editable.Factory.getInstance()
//                    .newEditable(it.isAdmin)
//            admin = userViewModel.user.value!!.isAdmin!!

        }


//        binding.btnLogout.setOnClickListener {
//            NavHostFragment.findNavController(this).navigate(R.id.login)
//        }

        binding.btnProfileSave.setOnClickListener {
            val firstName = binding.etProfileFirstname.text.toString().trim()
            val lastName = binding.etProfileLastname.text.toString().trim()
            val email = binding.etProfileEmail.text.toString().trim()
            val password = binding.etProfilePassword.text.toString().trim()
            val department = binding.etProfileDepartment.text.toString().trim()
            user = User(email, password, firstName, lastName, department)
            userViewModel.updateUser(user)

            userViewModel.statusCode.observe(viewLifecycleOwner) {
                when (it) {
                    200 -> {

                        Toast.makeText(context, "Saved Successfully", Toast.LENGTH_SHORT).show()
                    }
                    400 -> {
                        passwordLayout.error = "Please Enter Your password"
                        Toast.makeText(
                            context,
                            "Please make sure you filled all data ",
                            Toast.LENGTH_SHORT
                        ).show()
                    }


                }

            }
        }

        val playButton = binding.btnCommentNavigate
//
        userViewModel.user.observe(viewLifecycleOwner) {


            if (it.isAdmin == true) {

                playButton.visibility = View.VISIBLE
                binding.btnCommentNavigate.setOnClickListener {

                    NavHostFragment.findNavController(this).navigate(R.id.admin)
                }
            } else {
                playButton.visibility = View.GONE


            }
        }
//        }
//        if (user.isAdmin == false) {

//        }
//            else{
//            View.GONE.also { binding.btnCommentNavigate.visibility = it }
//            }


        return binding.root
    }

    fun Fragment.setActivityTitle(title: String) {
        (activity as AppCompatActivity?)?.supportActionBar?.title = title
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}