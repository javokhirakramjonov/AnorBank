package uz.gita.anorbank.ui.viewModel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.anorbank.data.enums.RegisterErrorEnum
import uz.gita.anorbank.data.model.simple.RegisterModel
import uz.gita.anorbank.domain.useCase.AuthUseCase
import uz.gita.anorbank.ui.viewModel.RegisterScreenVM
import javax.inject.Inject

@HiltViewModel
class RegisterScreenVMImpl @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel(), RegisterScreenVM {
    override val errorMessageLD = MutableLiveData<String>()
    override val continueButtonStateLD = MutableLiveData<Boolean>()
    override val invalidFieldsLD = MutableLiveData<List<RegisterErrorEnum>>()

    override fun checkData(user: RegisterModel, privacyPolicyState: Boolean) {
        val list = ArrayList<RegisterErrorEnum>()
        if (!("[A-Za-z]*".toRegex().matches(user.firstName) && user.firstName.length in 3..20))
            list.add(RegisterErrorEnum.FIRST_NAME)
        if (!("[A-Za-z]*".toRegex().matches(user.lastName) && user.lastName.length in 3..20))
            list.add(RegisterErrorEnum.FIRST_NAME)
        if (!("[0-9]{2} [0-9]{3} [0-9]{2} [0-9]{2}".toRegex().matches(user.phone)))
            list.add(RegisterErrorEnum.PHONE)
        if (user.password1.length < 6)
            list.add(RegisterErrorEnum.PASSWORD_LENGTH)
        if (user.password1 != user.password2)
            list.add(RegisterErrorEnum.PASSWORD)
        invalidFieldsLD.value = list
        continueButtonStateLD.value = list.isEmpty() && privacyPolicyState
    }

    override fun goVerification(user: RegisterModel) {
        authUseCase.registerUser(user).onEach {
            it.onSuccess {
                errorMessageLD.value = "Success"
            }
            it.onFailure { error ->
                errorMessageLD.value = error.message
            }
        }.launchIn(viewModelScope)
    }

}