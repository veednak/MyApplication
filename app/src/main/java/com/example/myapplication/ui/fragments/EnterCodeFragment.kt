package com.example.myapplication.ui.fragments

import androidx.fragment.app.Fragment
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.activitys.RegisterActivity
import com.example.myapplication.utilits.AUTH
import com.example.myapplication.utilits.AppTextWatcher
import com.example.myapplication.utilits.replaceActivity
import com.example.myapplication.utilits.showToast
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_enter_code.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
class EnterCodeFragment(val mPhoneNumber: String,val id: String) : Fragment(R.layout.fragment_enter_code) {


    override fun onStart() {
        super.onStart()
        (activity as RegisterActivity).title = mPhoneNumber
        register_input_code.addTextChangedListener(AppTextWatcher
        {
                val string = register_input_code.text.toString()
                if(string.length == 6) {
                    enterCode()
                }
        })
    }
    private fun enterCode()
    {
        val code = register_input_code.text.toString()
        val credential = PhoneAuthProvider.getCredential(id,code)
        AUTH.signInWithCredential(credential).addOnCompleteListener { task->
            if (task.isSuccessful)
            {
                showToast("Добро пожаловать")
                (activity as RegisterActivity).replaceActivity(MainActivity())
            }
            else showToast(task.exception?.message.toString())
        }
    }
}