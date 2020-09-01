package uz.jagito.bookstore.ui.screens.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import uz.jagito.bookstore.R
import uz.jagito.bookstore.databinding.FragmentSignUpBinding
import uz.jagito.bookstore.utils.enableToolbar
import uz.jagito.bookstore.utils.gone
import uz.jagito.bookstore.utils.showToast
import uz.jagito.bookstore.utils.visible

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        initFields()
        enableToolbar()
    }

    private fun initFields() {
        binding.apply {
            signUpFirstNameInput.doOnTextChanged { _, _, _, _ -> refreshBtn() }
            signUpLastNameInput.doOnTextChanged { _, _, _, _ -> refreshBtn() }
            signUpEmailInput.doOnTextChanged { _, _, _, _ -> refreshBtn() }
            signUpPasswordInput.doOnTextChanged { _, _, _, _ -> refreshBtn() }
            signUpPhoneNumberInput.doOnTextChanged { _, _, _, _ ->
                checkPhoneNumber()
            }
            signUpPhoneNumberInput.setOnFocusChangeListener { _, b ->
                if (!b) {
                    checkPhoneNumber()
                }
            }

            signUpActiveBtn.setOnClickListener {
                if (checkPhoneNumber()){
                    showToast("Welcome to out Book Store!")
                }
            }
        }
    }

    private fun checkPhoneNumber():Boolean{
        if (binding.signUpPhoneNumberInput.isTextValidInternationalPhoneNumber()) {
            binding.signUpInactiveBtn.error = null
            refreshBtn()
            return true
        } else {
            binding.signUpPhoneNumberInput.error = "Phone number format is wrong!"
        }
        return false
    }

    private fun refreshBtn() {
        val bName = binding.signUpFirstNameInput.editableText.isNotEmpty()
        val bLastName = binding.signUpLastNameInput.editableText.isNotEmpty()
        val bEmail = binding.signUpEmailInput.editableText.isNotEmpty()
        val bPhoneNumber = binding.signUpPhoneNumberInput.isTextValidInternationalPhoneNumber()
        val bPassword = binding.signUpPasswordInput.editableText.isNotEmpty()
        if (
            (bName || bLastName) &&
            bEmail &&
            bPhoneNumber &&
            bPassword
        ) {
            binding.signUpActiveBtn.visible()
            binding.signUpInactiveBtn.gone()

        } else {
            binding.signUpActiveBtn.gone()
            binding.signUpInactiveBtn.visible()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}