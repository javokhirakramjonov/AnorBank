package uz.gita.anorbank.ui.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.anorbank.R
import uz.gita.anorbank.databinding.ScreenPinCodeBinding
import uz.gita.anorbank.ui.viewModel.PinCodeScreenVM
import uz.gita.anorbank.ui.viewModel.impl.PinCodeScreenVMImpl

class PinCodeScreen : Fragment(R.layout.screen_pin_code) {
    private val viewModel: PinCodeScreenVM by viewModels<PinCodeScreenVMImpl>()
    private val binding by viewBinding(ScreenPinCodeBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.goBackLD.observe(this) {
            findNavController().popBackStack()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        codeButton1.setOnClickListener {pressNumber(codeButton1.text.toString())}
        codeButton2.setOnClickListener {pressNumber(codeButton2.text.toString())}
        codeButton3.setOnClickListener {pressNumber(codeButton3.text.toString())}
        codeButton4.setOnClickListener {pressNumber(codeButton4.text.toString())}
        codeButton5.setOnClickListener {pressNumber(codeButton5.text.toString())}
        codeButton6.setOnClickListener {pressNumber(codeButton6.text.toString())}
        codeButton7.setOnClickListener {pressNumber(codeButton7.text.toString())}
        codeButton8.setOnClickListener {pressNumber(codeButton8.text.toString())}
        codeButton9.setOnClickListener {pressNumber(codeButton9.text.toString())}
        codeButton10.setOnClickListener {}
        codeButton11.setOnClickListener {pressNumber(codeButton11.text.toString())}
        codeButton12.setOnClickListener {}
        Unit
    }

    private fun pressNumber(digit: String) {

    }
}