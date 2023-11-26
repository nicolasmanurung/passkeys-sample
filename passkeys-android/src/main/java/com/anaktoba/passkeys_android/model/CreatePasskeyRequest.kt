package com.anaktoba.passkeys_android.model


/**
 * @author Nicolas Manurung (nicolas.manurung@dana.id)
 * @version CreatePasskeyRequest, v 0.1 26/11/23 17.54 by Nicolas Manurung
 */
data class CreatePasskeyRequest(
    val attestation: String,
    val authenticatorSelection: AuthenticatorSelection,
    val challenge: String,
    val excludeCredentials: List<Any>,
    val pubKeyCredParams: List<PubKeyCredParam>,
    val rp: Rp,
    val timeout: Int,
    val user: User
)

data class AuthenticatorSelection(
    val authenticatorAttachment: String,
    val requireResidentKey: Boolean,
    val residentKey: String,
    val userVerification: String
)

data class PubKeyCredParam(
    val alg: Int,
    val type: String
)

data class Rp(
    val id: String,
    val name: String
)

data class User(
    val displayName: String,
    val id: String,
    val name: String
)