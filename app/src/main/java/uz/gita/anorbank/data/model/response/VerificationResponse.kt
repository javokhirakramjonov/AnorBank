package uz.gita.anorbank.data.model.response

data class VerificationResponse(
    val accessToken: String,
    val refreshToken: String
)