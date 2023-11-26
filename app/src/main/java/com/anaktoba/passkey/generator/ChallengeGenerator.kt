package com.anaktoba.passkey.generator


/**
 * @author Nicolas Manurung (nicolas.manurung@dana.id)
 * @version ChallengeGenerator, v 0.1 26/11/23 17.57 by Nicolas Manurung
 */
interface ChallengeGenerator {

    fun generateChallenge(): String

}