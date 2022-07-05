package uz.gita.anorbank.ui.screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.anorbank.R
import uz.gita.anorbank.data.enums.RegisterErrorEnum
import uz.gita.anorbank.data.model.simple.RegisterModel
import uz.gita.anorbank.databinding.ScreenReigstrationBinding
import uz.gita.anorbank.ui.viewModel.RegisterScreenVM
import uz.gita.anorbank.ui.viewModel.impl.RegisterScreenVMImpl
import uz.gita.anorbank.utils.value

@AndroidEntryPoint
class RegisterScreen : Fragment(R.layout.screen_reigstration) {

    private val viewModel: RegisterScreenVM by viewModels<RegisterScreenVMImpl>()
    private val binding by viewBinding(ScreenReigstrationBinding::bind)
    private val args: RegisterScreenArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.apply {
            goSplashScreenLD.observe(this@RegisterScreen) {
                findNavController().navigate(R.id.action_registerScreen_to_splashScreen)
            }
            goVerificationScreenLD.observe(this@RegisterScreen) {
                binding.apply {
                    findNavController().navigate(
                        RegisterScreenDirections.actionRegisterScreenToVerificationScreen
                            (
                            RegisterModel(
                                inputName.value(),
                                inputSurname.value(),
                                inputPhone.value(),
                                inputPassword.value(),
                                inputConfirmPassword.value()
                            )
                        )
                    )
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        inputPhone.addTextChangedListener {
            viewModel.correctForm(inputPhone.value())
            sendData()
        }
        inputName.addTextChangedListener { sendData() }
        inputSurname.addTextChangedListener { sendData() }
        inputPassword.addTextChangedListener { sendData() }
        inputConfirmPassword.addTextChangedListener { sendData() }
        privacy.setOnCheckedChangeListener { _, _ -> sendData() }

        buttonLanguage.apply {
            setImageResource(args.language)
            setOnClickListener { viewModel.goSplashScreen() }
        }

        viewModel.apply {
            correctFormPhoneLD.observe(viewLifecycleOwner, correctFormPhoneObserver)
            invalidFieldsLD.observe(viewLifecycleOwner, invalidFieldsObserver)
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
    }

    private val correctFormPhoneObserver = Observer<String> {
        binding.inputPhone.apply {
            setText(it)
            setSelection(it.length)
        }

    }

    private val invalidFieldsObserver = Observer<List<RegisterErrorEnum>> {
        binding.apply {
            outerPhone.isSelected = false
            errorName.visibility = View.INVISIBLE
            outerName.isSelected = false
            errorSurname.visibility = View.INVISIBLE
            outerSurname.isSelected = false
            errorPasswordMatch.visibility = View.INVISIBLE
            outerPassword.isSelected = false
            errorPassword.visibility = View.INVISIBLE
            outerPassword2.isSelected = false
            it.forEach { error ->
                when (error) {
                    RegisterErrorEnum.FIRST_NAME -> {
                        errorName.visibility = View.VISIBLE
                        outerName.isSelected = true
                    }
                    RegisterErrorEnum.LAST_NAME -> {
                        errorSurname.visibility = View.VISIBLE
                        outerSurname.isSelected = true
                    }
                    RegisterErrorEnum.PHONE -> {
                        outerPhone.isSelected = true
                    }
                    RegisterErrorEnum.PASSWORD -> {
                        errorPasswordMatch.visibility = View.VISIBLE
                        outerPassword2.isSelected = true
                    }
                    RegisterErrorEnum.PASSWORD_LENGTH -> {
                        errorPassword.visibility = View.VISIBLE
                        outerPassword.isSelected = true
                    }
                }
            }
        }
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