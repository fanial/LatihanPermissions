package com.fal.latihanpermissions

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.fal.latihanpermissions.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /**
        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
        **/

        binding.btnSendSMS.setOnClickListener {
            val number = binding.contact.text.toString()
            val msg = binding.smsText.text.toString()
            val permissionRequest = 101

            if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),
                        Manifest.permission.SEND_SMS)) {
                } else {
                    ActivityCompat.requestPermissions(requireActivity(),arrayOf(Manifest.permission.SEND_SMS),
                        permissionRequest)
                }
            } else {
                myMessage(number,msg)
                Log.i("SMS_STATUS", "PERMISSION RECEIVED ... SEND SMS ")
            }
        }
    }

    fun myMessage(phoneNo: String, message: String) {
        val myNumber = phoneNo.trim()
        val myMsg = message.trim()
        if (myNumber == "" || myMsg == "") {
            Toast.makeText(context, "Field cannot be empty", Toast.LENGTH_SHORT).show()
        } else {
            if (TextUtils.isDigitsOnly(myNumber)) {
                val smsManager: SmsManager = SmsManager.getDefault()
                smsManager.sendTextMessage("+62 " + myNumber, null, myMsg, null, null)
                Toast.makeText(context, "Message Sent", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "Please enter the correct number", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}