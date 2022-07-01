package uz.gita.anorbank.ui.viewModel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.anorbank.ui.viewModel.SplashScreenVM

class SplashScreenVMImpl : ViewModel(), SplashScreenVM {
    override val goRegisterScreenLD = MutableLiveData<Unit>()

    override fun goRegisterScreen() {
        goRegisterScreenLD.value = Unit
    }
}