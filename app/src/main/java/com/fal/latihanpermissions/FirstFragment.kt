package com.fal.latihanpermissions

import android.Manifest.permission.SEND_SMS
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.os.Bundle
import android.telephony.SmsManager
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.fal.latihanpermissions.databinding.FragmentFirstBinding


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        CheckConnectionType()

    }

    //WIfi Permissions
    fun CheckConnectionType(){
        val connectionManager = requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val wifi_Connection = connectionManager.getNetworkInfo(TYPE_WIFI)
        val mobile_data_connection = connectionManager.getNetworkInfo(TYPE_MOBILE)

        if (wifi_Connection != null) {
            if (wifi_Connection.isConnectedOrConnecting)
            {
                binding.statusConn.text = "WIFI Connection ON"
            }
            else
            {
                if (mobile_data_connection != null) {
                    if (mobile_data_connection.isConnectedOrConnecting) {
                        binding.statusConn.text = "Mobile Data Connection ON"
                    } else {
                        binding.statusConn.text = "No Network Connection"
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}