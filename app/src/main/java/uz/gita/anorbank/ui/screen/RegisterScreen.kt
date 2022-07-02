package uz.gita.anorbank.ui.screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.anorbank.R
import uz.gita.anorbank.data.model.simple.RegisterModel
import uz.gita.anorbank.databinding.ScreenReigstrationBinding
import uz.gita.anorbank.ui.viewModel.RegisterScreenVM
import uz.gita.anorbank.ui.viewModel.impl.RegisterScreenVMImpl
import uz.gita.anorbank.utils.value

@AndroidEntryPoint
class RegisterScreen : Fragment(R.layout.screen_reigstration) {

    private val viewModel: RegisterScreenVM by viewModels<RegisterScreenVMImpl>()
    private val binding by viewBinding(ScreenReigstrationBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        inputPhone.addTextChangedListener { sendData() }
        inputName.addTextChangedListener { sendData() }
        inputSurname.addTextChangedListener { sendData() }
        inputPassword.addTextChangedListener { sendData() }
        inputConfirmPassword.addTextChangedListener { sendData() }
        privacy.setOnCheckedChangeListener { _, _ -> sendData() }

        viewModel.apply {
            continueButtonStateLD.observe(viewLifecycleOwner, continueButtonStateObserver)
            errorMessageLD.observe(viewLifecycleOwner, errorMessageObserver)
        }

        buttonContinue.setOnClickListener {
            viewModel.goVerification(
                RegisterModel(
                    inputName.value(),
                    inputSurname.value(),
                    inputPhone.value(),
                    inputPassword.value(),
                    inputConfirmPassword.value()
                )
            )
        }

        Unit
    }

    private val continueButtonStateObserver = Observer<Boolean> {
        binding.buttonContinue.isEnabled = it
    }

    private val errorMessageObserver = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
    }

    private fun sendData() {
        binding.apply {
            viewModel.checkData(
                RegisterModel(
                    inputName.value(),
                    inputSurname.value(),
                    inputPhone.value(),
                    inputPassword.value(),
                    inputConfirmPassword.value()
                ),
                privacy.isChecked
            )
        }
    }

}