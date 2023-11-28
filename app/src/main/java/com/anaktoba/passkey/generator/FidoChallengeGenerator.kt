package com.anaktoba.passkey.generator

import android.util.Base64
import java.security.SecureRandom


/**
 * @author Nicolas Manurung (nicolas.manurung@dana.id)
 * @version FidoChallengeGenerator, v 0.1 26/11/23 17.57 by Nicolas Manurung
 */
class FidoChallengerGenerator : ChallengeGenerator {
    private val secureRandom = SecureRandom()
    override fun generateChallenge(): String {
        val challengeBytes = ByteArray(32)
        secureRandom.nextBytes(challengeBytes)
        return Base64.encodeToString(
            challengeBytes,
            Base64.NO_PADDING or Base64.NO_WRAP or Base64.URL_SAFE
        )
    }

    override fun generateId(): String {
        val idBytes = ByteArray(64)
        secureRandom.nextBytes(idBytes)
        return Base64.encodeToString(
            idBytes,
            Base64.NO_PADDING or Base64.NO_WRAP or Base64.URL_SAFE
        )
    }
}
