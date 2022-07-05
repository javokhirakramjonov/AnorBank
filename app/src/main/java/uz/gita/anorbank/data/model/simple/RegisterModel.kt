package uz.gita.anorbank.data.model.simple

import uz.gita.anorbank.data.model.request.RegisterRequest
import java.io.Serializable

data class RegisterModel(
    val firstName: String,
    val lastName: String,
    val phone: String,
    val password1: String,
    val password2: String
) : Serializable {
    fun toRequest(): RegisterRequest {
        return RegisterRequest(
            firstName,
            lastName,
            getPhone(phone),
            password1
        )
    }
    private fun getPhone(phone: String) : String {
        val temp = StringBuilder()
        for(i in phone.toCharArray()) {
            if(i != ' ')
                temp.append(i)
        }
        return temp.toString()
    }
}