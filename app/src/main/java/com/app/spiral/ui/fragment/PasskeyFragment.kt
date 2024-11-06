package com.app.spiral

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import com.app.spiral.databinding.FragmentPasskeyBinding


class PasskeyFragment : Fragment() {
    lateinit var binding: FragmentPasskeyBinding
    lateinit var activity: MainActivity
    var _frag: PasskeyFragment? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, it: Bundle?
    ): View {
        binding = FragmentPasskeyBinding.inflate(layoutInflater)
        activity = requireActivity() as MainActivity
        _frag = this
        binding.tvSignIn.setOnClickListener { activity.navController.popBackStack() }
        Handler().postDelayed({
            if (_frag != null) {
                showBiometricPrompt(_frag!!) {
                    val navOptions = NavOptions.Builder()
                        .setPopUpTo(activity.navController.graph.startDestinationId, true) // Clear entire back stack
                        .build()
                    activity.navController.navigate(R.id.homeFragment, null, navOptions)
                }
            }
        }, 500)

        return binding.root
    }


    companion object {
        fun checkBiometricSupport(context: Context): Boolean {
            val biometricManager = BiometricManager.from(context)
            return when (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG)) {
                BiometricManager.BIOMETRIC_SUCCESS -> true
                BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                    // No biometric features available on this device
                    false
                }

                BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                    // Biometric features are currently unavailable
                    false
                }

                BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                    // No biometrics enrolled on the device
                    false
                }

                else -> false
            }
        }

        fun showBiometricPrompt(
            frag: Fragment, onSelected: () -> Unit = {}
        ) {
            if (checkBiometricSupport(frag.requireContext())) {
                val executor = ContextCompat.getMainExecutor(frag.requireContext())
                val biometricPrompt = BiometricPrompt(frag,
                    executor,
                    object : BiometricPrompt.AuthenticationCallback() {
                        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                            super.onAuthenticationSucceeded(result)
                            onSelected.invoke()
                            // Authentication was successful
                            // Perform necessary actions (e.g., grant access)
                        }

                        override fun onAuthenticationError(
                            errorCode: Int, errString: CharSequence
                        ) {
                            super.onAuthenticationError(errorCode, errString)
                            // Handle errors (e.g., show error message)
                        }

                        override fun onAuthenticationFailed() {
                            super.onAuthenticationFailed()
                            // Handle failure (e.g., notify the user)
                        }
                    })

                val promptInfo = BiometricPrompt.PromptInfo.Builder().setTitle(
                    frag.requireContext().getString(R.string.authenticate_with_fingerprint)
                ).setSubtitle(
                    frag.requireContext()
                        .getString(R.string.place_your_finger_on_the_sensor_to_continue)
                ).setNegativeButtonText(frag.requireContext().getString(R.string.cancel))
                    .build()

                biometricPrompt.authenticate(promptInfo)
            } else {
                Toast.makeText(
                    frag.requireContext(),
                    frag.requireContext().getString(R.string.fingerprint_sensor_not_found),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}