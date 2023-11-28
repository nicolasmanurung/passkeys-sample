package com.anaktoba.passkey

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.anaktoba.passkey.executor.JobExecutor
import com.anaktoba.passkey.generator.FidoChallengerGenerator
import com.anaktoba.passkeys_android.Passkeys
import com.anaktoba.passkeys_android.helper.GsonAdapter
import com.anaktoba.passkeys_android.model.AuthenticatorSelection
import com.anaktoba.passkeys_android.model.CreatePasskeyRequest
import com.anaktoba.passkeys_android.model.PubKeyCredParam
import com.anaktoba.passkeys_android.model.Rp
import com.anaktoba.passkeys_android.model.User

class MainActivity : AppCompatActivity() {
    private val challengeGenerator: FidoChallengerGenerator = FidoChallengerGenerator()
    private val jsonAdapter: GsonAdapter = GsonAdapter()
    private val passkeys by lazy {
        Passkeys(this)
    }

    val request = CreatePasskeyRequest(
        challenge = challengeGenerator.generateChallenge(),
        rp = Rp(id = "wellknownsample.web.app", name = "Passkey Demo"),
        user = User(
            displayName = "nicolasmanurung16@gmail.com",
            id = challengeGenerator.generateId(),
            name = "nicolasmanurung16@gmail.com"
        ),
        pubKeyCredParams = listOf(
            PubKeyCredParam(type = "public-key", alg = -7),
            PubKeyCredParam(type = "public-key", alg = -257),
        ),
        timeout = 1800000,
        attestation = "none",
        excludeCredentials = emptyList(),
        authenticatorSelection = AuthenticatorSelection(
            authenticatorAttachment = "platform",
            requireResidentKey = true,
            residentKey = "required",
            userVerification = "required"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("PASSKEYS", jsonAdapter.toJson(request).toString())
        passkeys.signUp(
            requestJson = jsonAdapter.toJson(request).toString(),
            activity = this,
            executor = JobExecutor,
            onSuccessCallback = {
                Log.d("PASSKEYS", it)
            },
            onErrorCallback = {
                Log.e("PASSKEYS", it)
            }
        )
    }
}