package uz.gita.anorbank.data.model.request

import java.io.Serializable

data class RegisterRequest(
    val firstName: String,
    val lastName: String,
    val phone: String,
    val password: String
):Serializable