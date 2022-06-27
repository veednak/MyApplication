
package com.example.myapplication.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityRegisterBinding
import com.example.myapplication.ui.fragments.EnterPhoneNumberFragment
import com.example.myapplication.utilits.replaceFragment

private lateinit var mToolbar:Toolbar
private lateinit var mBinding:ActivityRegisterBinding
class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {2
        super.onCreate(savedInstanceState)
        mBinding= ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()
        mToolbar= mBinding.registerToolbar
        setSupportActionBar(mToolbar)
        title=getString(R.string.register_title_your_phone)
        replaceFragment(EnterPhoneNumberFragment())
    }

}