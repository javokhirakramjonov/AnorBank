package uz.gita.anorbank.ui.viewModel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.anorbank.ui.viewModel.PinCodeScreenVM

class PinCodeScreenVMImpl : ViewModel(), PinCodeScreenVM {
    override val goBackLD = MutableLiveData<Unit>()

    override fun goBack() {
        goBackLD.value = Unit
    }
}