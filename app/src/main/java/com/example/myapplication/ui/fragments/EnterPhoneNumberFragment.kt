package com.example.myapplication.ui.fragments

import androidx.fragment.app.Fragment
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.activitys.RegisterActivity
import com.example.myapplication.utilits.AUTH
import com.example.myapplication.utilits.replaceActivity
import com.example.myapplication.utilits.replaceFragment
import com.example.myapplication.utilits.showToast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_enter_phone_number.*
import java.util.concurrent.TimeUnit

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EnterPhoneNumberFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EnterPhoneNumberFragment : Fragment(R.layout.fragment_enter_phone_number) {
    private lateinit var mPhoneNumber:String
    private lateinit var mCallback:PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onStart() {
        super.onStart()
        mCallback = object  : PhoneAuthProvider.OnVerificationStateChangedCallbacks()
        {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                AUTH.signInWithCredential(credential).addOnCompleteListener { task->
                  if (task.isSuccessful)
                  {
                      showToast("Добро пожаловать")
                      (activity as RegisterActivity).replaceActivity(MainActivity())
                  }
                    else showToast(task.exception?.message.toString())
                }


            }

            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                replaceFragment(EnterCodeFragment(mPhoneNumber,id))
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                showToast(p0.message.toString())
            }

        }
        register_btn_next.setOnClickListener {
            sendCode()
        }
    }

    private fun sendCode() {
        if(register_input_phone_number.text.toString().isEmpty())
        {
            showToast(getString(R.string.register_toast_enter_phone))
        }else
            authUser()
    }

    private fun authUser() {
        mPhoneNumber = register_input_phone_number.text.toString()
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            mPhoneNumber,
            60,
            TimeUnit.SECONDS,
            activity as RegisterActivity,
            mCallback
        )
    }
}

