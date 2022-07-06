package uz.gita.anorbank.ui.screen

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.anorbank.R
import uz.gita.anorbank.databinding.ScreenVerifyBinding
import uz.gita.anorbank.ui.viewModel.VerificationScreenVM
import uz.gita.anorbank.ui.viewModel.impl.VerificationScreenVMImpl
import uz.gita.anorbank.utils.value

@AndroidEntryPoint
class VerificationScreen : Fragment(R.layout.screen_verify) {

    private val viewModel: VerificationScreenVM by viewModels<VerificationScreenVMImpl>()
    private val binding by viewBinding(ScreenVerifyBinding::bind)
    private val args: VerificationScreenArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.goBackLD.observe(this) {
            findNavController().popBackStack()
        }
        viewModel.goPinCodeScreenLD.observe(this) {
            findNavController().navigate(R.id.action_verificationScreen_to_pinCodeScreen)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {

        buttonBack.setOnClickListener {
            viewModel.goBack()
        }

        viewModel.apply {
            this.sendCodeButtonLD.observe(viewLifecycleOwner, sendCodeButtonObserver)
            timerLD.observe(viewLifecycleOwner, timerObserver)
            codeStateLD.observe(viewLifecycleOwner, codeStateObserver)
            resendButtonStateLD.observe(viewLifecycleOwner, resendButtonStateObserver)
            inputCodeStateLD.observe(viewLifecycleOwner, inputCodeStateObserver)
        }

        inputCode.addTextChangedListener {
            buttonContinue.isEnabled = inputCode.value().length == 6
        }

        buttonContinue.setOnClickListener {
            viewModel.sendCode(inputCode.value())
        }

        buttonResend.setOnClickListener {
            viewModel.resend(args.user)
            inputCode.setText("")
        }

    }

    private val sendCodeButtonObserver = Observer<Boolean> {
        binding.buttonContinue.isEnabled = it
    }

    private val timerObserver = Observer<Int> {
        binding.timer.text = "Code arrives within: $it"
    }

    private val codeStateObserver = Observer<Boolean> {
        binding.outerCode.isSelected = !it
        if (!it) {
            binding.errorPassword.visibility = View.VISIBLE
        } else {
            binding.errorPassword.visibility - View.GONE
        }
    }

    private val resendButtonStateObserver = Observer<Boolean> {
        binding.buttonResend.isEnabled = it
    }

    private val inputCodeStateObserver = Observer<Boolean> {
        binding.inputCode.isEnabled = it
    }

}