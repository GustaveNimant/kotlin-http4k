package io.ipfs.kotlin.url

import io.ipfs.kotlin.*
import java.io.File
import java.util.Stack

/**
 * Author : Emile Achadde 26 février 2020 at 18:03:09+01:00
 */

data class UrlValue (val hosVal: HostValue, val porVal: PortValue) {

    fun isEmpty (): Boolean {
	return hosVal.toString().isNullOrEmpty() || (porVal.toString()).isNullOrEmpty()
    }

    override fun toString (): String {
       return hosVal.toString() + ":" + porVal.toString()
    }

}


  
