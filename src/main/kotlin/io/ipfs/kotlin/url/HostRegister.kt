package io.ipfs.kotlin.url

import io.ipfs.kotlin.*
import java.io.File
import java.util.Stack

/**
 * What is it : the Register storing a Host knowing its Type.
 * Example : (HostLocal, "5001") 
 * Author : Emile Achadde 26 février 2020 at 17:42:58+01:00
 */

class HostRegister {

    var register : MutableMap<HostType, HostValue> = mutableMapOf<HostType, HostValue>()
	 
    fun isEmpty (): Boolean {
	val (here, caller) = moduleHereAndCaller()
	entering(here, caller)

	val result = register.isEmpty()

	if(isTrace(here)) println ("$here: output result $result")
        return result
     }

    fun isStored (hosTyp: HostType): Boolean {
	val (here, caller) = moduleHereAndCaller()
	entering(here, caller)
	
	if(isTrace(here)) println ("$here: input hosTyp '$hosTyp'")
	
	val result = if (register.containsKey(hosTyp)) {
	    (register.get(hosTyp)!!.isEmpty())
	}
	else {false}
	
	if(isTrace(here)) println ("$here: output result '$result'")
	
	exiting(here)
	return result
    }
    
    fun store (hosTyp: HostType, hosVal: HostValue) {
	val (here, caller) = moduleHereAndCaller()
	entering(here, caller)
	
	if(isTrace(here)) println ("$here: input hosTyp '$hosTyp'")
	if(isTrace(here)) println ("$here: input hosVal '$hosVal'")

	if (isStored(hosTyp)) {
	    val value = retrieve(hosTyp)
	    if (value != hosVal) {
		val v = hosVal.toString()
		fatalErrorPrint("already stored Host Value '$value' for Host Type '$hosTyp' were equal to new one", v, "Check", here)
	    }
	}
	else {
	    register.put(hosTyp, hosVal)
	}
	if(isTrace(here)) println ("$here: ($hosTyp, $hosVal) couple has been stored")
    }
    
    fun retrieve(hosTyp: HostType): HostValue? {
         val (here, caller) = moduleHereAndCaller()
    	 entering(here, caller)

	 val result = register.get(hosTyp)
	 
	 if (result!!.isEmpty()) {
	   fatalErrorPrint("an Host Value existed for Host Type '$hosTyp'", "it did not","Check", here)
         }
	 if(isTrace(here)) println ("$here: output result '$result'")
	 exiting(here)
	 return result
    }

}


