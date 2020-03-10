package io.ipfs.kotlin.url

import io.ipfs.kotlin.*
import java.io.File
import java.util.Stack

/**
 * What is it : the Value of a Port
 * Example : 5001 
 * Author : Emile Achadde 25 février 2020 at 19:03:02+01:00
 * Revision : isValide() by Emile Achadde 27 février 2020 at 13:41:03+01:00
 */

data class PortValue (val port: Int) {

    fun isEmpty (): Boolean {
	return (port.toString()).isNullOrEmpty()
    }

    private fun isValid(): Boolean {
	val pattern = Regex("""^\d\d\d\d$""")
	val result = pattern.matches(port.toString())
	return result
    }

    override fun toString(): String {
	assert(isValid())
	return port.toString()
    }
}


  
