package com.anaktoba.passkeys_android

import android.app.Activity
import android.content.Context
import androidx.credentials.CreateCredentialResponse
import androidx.credentials.CreatePublicKeyCredentialRequest
import androidx.credentials.CredentialManager
import androidx.credentials.CredentialManagerCallback
import androidx.credentials.exceptions.CreateCredentialException
import java.util.concurrent.Executor


/**
 * @author Nicolas Manurung (nicolas.manurung@dana.id)
 * @version Passkeys, v 0.1 26/11/23 17.25 by Nicolas Manurung
 */
class Passkeys(
    private val context: Context,
) {
    private val credentialManager by lazy {
        CredentialManager.create(context)
    }

    fun signUp(
        requestJson: String,
        activity: Activity,
        executor: Executor,
        onSuccessCallback: (CreateCredentialResponse) -> Unit = {},
        onErrorCallback: () -> Unit = {},
    ) {

        credentialManager.createCredentialAsync(
            context = activity,
            request = CreatePublicKeyCredentialRequest(requestJson),
            cancellationSignal = null,
            executor = executor,
            callback = object :
                CredentialManagerCallback<CreateCredentialResponse, CreateCredentialException> {
                override fun onError(e: CreateCredentialException) {
                    onErrorCallback.invoke()
                }

                override fun onResult(result: CreateCredentialResponse) {
                    onSuccessCallback.invoke(result)
                }

            }
        )
    }
}