package uz.jagito.bookstore.ui.screens.intro

import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_intro.*
import kotlinx.android.synthetic.main.fragment_sign_in.*
import uz.jagito.bookstore.R
import uz.jagito.bookstore.models.IntroModel
import uz.jagito.bookstore.ui.adapters.IntroAdapter
import uz.jagito.bookstore.ui.screens.login.SignInFragment
import uz.jagito.bookstore.ui.screens.login.SignUpFragment
import uz.jagito.bookstore.utils.disableToolbar
import uz.jagito.bookstore.utils.replaceFragment
import uz.jagito.bookstore.utils.showToast


class IntroFragment : Fragment(R.layout.fragment_intro) {
    private val data = listOf(
        IntroModel(
            "Discounted Secondhand Books",
            "Used and near new secondhand books at great prices.",
            R.drawable.ic_intro_page_01
        ),
        IntroModel(
            "20 Book Grocers Nationally",
            "We've successfully opened 20 stores across Australia.",
            R.drawable.ic_intro_page_02
        ),
        IntroModel(
            "Sell or Recycle Your Old Books With Us",
            "If you're looking to downsize, sell or recycle old books, the Book Grocer can help.",
            R.drawable.ic_intro_page_03
        )
    )

    private val adapter = IntroAdapter(data)

    override fun onResume() {
        super.onResume()
        disableToolbar()
        initFields()
    }


    private fun initFields() {

        intro_view_pager.adapter = adapter
        TabLayoutMediator(tabLayoutInto, intro_view_pager) { tab, possition -> }.attach()

        adapter.onSignIn {
            replaceFragment(SignInFragment())
        }
        adapter.onSignUp {
            replaceFragment(SignUpFragment())
        }
    }
}