package com.anaktoba.passkey

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anaktoba.passkey.executor.JobExecutor
import com.anaktoba.passkey.generator.ChallengeGenerator
import com.anaktoba.passkey.generator.FidoChallengerGenerator
import com.anaktoba.passkeys_android.Passkeys
import com.anaktoba.passkeys_android.helper.GsonAdapter
import com.anaktoba.passkeys_android.helper.JSONAdapter
import com.anaktoba.passkeys_android.model.AuthenticatorSelection
import com.anaktoba.passkeys_android.model.CreatePasskeyRequest
import com.anaktoba.passkeys_android.model.PubKeyCredParam
import com.anaktoba.passkeys_android.model.Rp
import com.anaktoba.passkeys_android.model.User

class MainActivity : AppCompatActivity() {
    private val challengeGenerator: FidoChallengerGenerator = FidoChallengerGenerator()
    private val jsonAdapter : GsonAdapter = GsonAdapter()
    private val passkeys by lazy {
        Passkeys(this)
    }

    val request = CreatePasskeyRequest(
        challenge = challengeGenerator.generateChallenge(),
        rp = Rp("DANA", "a.m.dana.id"),
        user = User(displayName = "DANA", id = "DANA", name = "DANA"),
        pubKeyCredParams = listOf(PubKeyCredParam(type = "public-key", alg = -7)),
        timeout = 1800000,
        attestation = "none",
        excludeCredentials = emptyList(),
        authenticatorSelection = AuthenticatorSelection(
            "platform",
            false,
            "required",
            "required"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        passkeys.signUp(
            requestJson = jsonAdapter.toJson(request).toString(),
            activity = this,
            executor = JobExecutor,
            onSuccessCallback = {

            },
            onErrorCallback = {

            }
        )
    }
}