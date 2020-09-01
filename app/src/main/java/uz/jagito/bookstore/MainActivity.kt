package uz.jagito.bookstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import uz.jagito.bookstore.data.local.sharedpreference.LocalStorage
import uz.jagito.bookstore.databinding.ActivityMainBinding
import uz.jagito.bookstore.ui.screens.intro.IntroFragment
import uz.jagito.bookstore.utils.APP_ACTIVITY
import uz.jagito.bookstore.utils.replaceFragment

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    lateinit var mToolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        APP_ACTIVITY = this
        initFields()
        if (LocalStorage.instance.isFirstTime)
        replaceFragment(IntroFragment(), false)
    }

    //Test
    private fun initFields(){
        mToolbar = mBinding.mainToolbar
        mToolbar.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24)
    }
}