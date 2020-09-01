package uz.jagito.bookstore.ui.screens.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import uz.jagito.bookstore.R
import uz.jagito.bookstore.databinding.FragmentSignInBinding
import uz.jagito.bookstore.utils.enableToolbar
import uz.jagito.bookstore.utils.gone
import uz.jagito.bookstore.utils.visible

class SignInFragment : Fragment(R.layout.fragment_sign_in) {
    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!
    private var isNotEmptyEmail = false
    private var isNotEmptyPassword = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        enableToolbar()
        initFields()
    }


    private fun initFields() {
        binding.signInEmailInput.doOnTextChanged { text, _, _, _ ->
            isNotEmptyEmail = text != null && text.trim().isNotEmpty()
            refreshBtn()
        }
        binding.signInPasswordInput.doOnTextChanged { text, _, _, _ ->
            isNotEmptyPassword = text != null && text.trim().isNotEmpty()
            refreshBtn()
        }
    }

    private fun refreshBtn() {
        if (isNotEmptyEmail && isNotEmptyPassword) {
            binding.signInActiveBtn.visible()
            binding.signInInactiveBtn.gone()
        } else {
            binding.signInActiveBtn.gone()
            binding.signInInactiveBtn.visible()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}