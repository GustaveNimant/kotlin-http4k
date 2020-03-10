package io.ipfs.kotlin.url

import io.ipfs.kotlin.*
import java.io.File
import java.util.Stack

/**
 * What is it : the Register storing an Url knowing its Type.
 * Example : (LocalIpfsApi, "127.0.0.1:5001") 
 * Remark : Register(UrlType, UrlValue)
 * Author : Emile Achadde 25 février 2020 at 19:03:02+01:00
 */

class UrlRegister {

    var register : MutableMap<UrlType, UrlValue> = mutableMapOf<UrlType, UrlValue>()
	 
    fun isEmpty (): Boolean {
	val (here, caller) = moduleHereAndCaller()
	entering(here, caller)

	val result = register.isEmpty()

	if(isTrace(here)) println ("$here: output result $result")
        return result
     }

    fun isStored (urlTyp: UrlType): Boolean {
	val (here, caller) = moduleHereAndCaller()
	entering(here, caller)
	
	if(isTrace(here)) println ("$here: input urlTyp '$urlTyp'")
	
	val result = if (register.containsKey(urlTyp)) {
	    (register.get(urlTyp)!!.isEmpty())
	}
	else {false}
	
	if(isTrace(here)) println ("$here: output result '$result'")
	
	exiting(here)
	return result
    }
    
    fun store (urlTyp: UrlType, urlVal: UrlValue) {
	val (here, caller) = moduleHereAndCaller()
	entering(here, caller)
	
	if(isTrace(here)) println ("$here: input urlTyp '$urlTyp'")

	if (isStored(urlTyp)) {
	    val value = retrieve(urlTyp)
	    if (value != urlVal) {
		fatalErrorPrint("already stored Url Value '$value' for Url Type '$urlTyp' were equal to new one", urlVal.toString(), "Check", here)
	    }
	}
	else {
	    register.put(urlTyp, urlVal)
	}
	if(isTrace(here)) println ("$here: ($urlTyp, $urlVal) couple has been stored")
    }
    
    fun retrieve(urlTyp: UrlType): UrlValue? {
         val (here, caller) = moduleHereAndCaller()
    	 entering(here, caller)

	 val result = register.get(urlTyp)
	 
	 if (result!!.isEmpty()) {
	   fatalErrorPrint("an Url Value existed for Url Type '$urlTyp'", "it did not","Check", here)
         }
	 if(isTrace(here)) println ("$here: output result '$result'")
	 exiting(here)
	 return result
    }
}
