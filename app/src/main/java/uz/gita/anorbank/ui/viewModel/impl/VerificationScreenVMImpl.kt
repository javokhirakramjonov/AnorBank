package uz.gita.anorbank.ui.viewModel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.anorbank.data.model.simple.RegisterModel
import uz.gita.anorbank.domain.useCase.AuthUseCase
import uz.gita.anorbank.ui.viewModel.VerificationScreenVM
import javax.inject.Inject

@HiltViewModel
class VerificationScreenVMImpl @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel(), VerificationScreenVM {
    override val inputCodeStateLD = MutableLiveData<Boolean>()
    override val codeStateLD = MutableLiveData<Boolean>()
    override val goBackLD = MutableLiveData<Unit>()
    override val timerLD = MutableLiveData<Int>()
    override val errorMessageLD = MutableLiveData<String>()
    override val resendButtonStateLD = MutableLiveData<Boolean>()
    override val sendCodeButtonLD = MutableLiveData<Boolean>()
    override val goPinCodeScreenLD = MutableLiveData<Unit>()

    private var timer: Job? = null

    init {
        resendButtonStateLD.value = false
        startTime()
    }

    override fun resend(user: RegisterModel) {
        sendCodeButtonLD.value = false
        codeStateLD.value = false
        inputCodeStateLD.value = false
        resendButtonStateLD.value = false
        authUseCase.registerUser(user).onEach {
            it.onSuccess {
                inputCodeStateLD.value = true
                startTime()
            }
            it.onFailure { error ->
                resendButtonStateLD.value = true
                errorMessageLD.value = error.message
            }
        }.launchIn(viewModelScope)
    }

    override fun sendCode(code: String) {
        if (code.length < 6) {
            codeStateLD.value = false
            return
        }
        inputCodeStateLD.value = false
        sendCodeButtonLD.value = false
        codeStateLD.value = false
        authUseCase.verifyUser(code).onEach {
            codeStateLD.value = false
            it.onSuccess {
                timer?.cancel()
                goPinCodeScreenLD.value = Unit
            }
            it.onFailure {
                inputCodeStateLD.value = true
            }
        }.launchIn(viewModelScope)
    }

    override fun goBack() {
        goBackLD.value = Unit
    }

    override fun startTime() {
        var time = 60
        timer?.cancel()
        timer = viewModelScope.launch(Dispatchers.IO) {
            while (time >= 0) {
                delay(1000)
                timerLD.postValue(time)
                time--
            }
            inputCodeStateLD.postValue(false)
            resendButtonStateLD.postValue(true)
        }
    }
}