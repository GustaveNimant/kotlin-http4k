package io.ipfs.kotlin

import io.ipfs.kotlin.defaults.*

/**
 * Storage   : immutableValue (content of an Immutable file) by its ImmutableType
 * Needs     : immutableType (MultiHashType (strHas)) where strHas is the hash string
 * Needed by : 
 * Author : Emile Achadde 01 mars 2020 at 10:30:45+01:00
 */

class immutableRegister {
    
    var register : MutableMap<immutableType, immutableValue> = mutableMapOf<immutableType, immutableValue>()

    fun isEmpty (): Boolean {
	val (here, caller) = moduleHereAndCaller()
	entering(here, caller)
	
	val result = register.isEmpty()
	
	if(isTrace(here)) println ("$here: output result $result")
	
	return result
    }

    fun store (immTyp: immutableType, immVal: immutableValue) {
	val (here, caller) = moduleHereAndCaller()
	entering(here, caller)
	
	if(isTrace(here)) println ("$here: input immTyp '$immTyp'")
	if(isTrace(here)) println ("$here: input immVal '$immVal'")
	
	if (isStored(immTyp)) {
	    val con = retrieve(immTyp)
	    if (con != immVal) {
		fatalErrorPrint("immutableValue already stored for immTyp '$immTyp' were equal to new one", immVal.toString(), "Check", here)
	    }
	}
	else {
	    register.put(immTyp, immVal)
	}
	if(isTrace(here)) println ("$here: immVal couple has been stored")
    }
    
    fun isStored (immTyp: immutableType): Boolean {
	val (here, caller) = moduleHereAndCaller()
	entering(here, caller)
	
	if(isTrace(here)) println ("$here: input immTyp '$immTyp'")
	
	val immVal = register.get(immTyp)
	val result = when (immVal) {
	    is immutableValue -> register.contains(immTyp) 
	    else -> false
	}
	
	if(isTrace(here)) println ("$here: output result '$result'")
	
	exiting(here)
	return result
    }
    
    fun retrieve (immTyp: immutableType): immutableValue {
	val (here, caller) = moduleHereAndCaller()
	entering(here, caller)
	
	val immVal = register.get(immTyp)
	val result = when (immVal) {
	    is immutableValue -> immVal 
	    else -> {fatalErrorPrint ("", "", "", here)}
	}
	if(isTrace(here)) println ("$here: output result '$result'")
	
	exiting(here)
	return result
    }
}
