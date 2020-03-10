package io.ipfs.kotlin

import io.ipfs.kotlin.defaults.*

/**
 * What is it : it stores any MultiHashValue (information such as directory, file, string) indexed by its MultiHashType  
 *
 * 
 * Author : Emile Achadde 03 mars 2020 at 19:15:48+01:00
 */

class MultiHashRegister {
    
    var register : MutableMap<MultiHashType, MultiHashValue> = mutableMapOf<MultiHashType, MultiHashValue>()

fun isEmpty (): Boolean {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)
    
    val result = register.isEmpty()
    
    if(isTrace(here)) println ("$here: output result $result")
    
    return result
}

fun store (mulTyp: MultiHashType, mulVal: MultiHashValue) {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)
    
    if(isTrace(here)) println ("$here: input mulTyp '$mulTyp'")
    if(isTrace(here)) println ("$here: input mulVal '$mulVal'")
    
    if (isStored(mulTyp)) {
	val value = retrieve(mulTyp)
	if (value != mulVal) {
	    fatalErrorPrint("MultiHashType already stored with value '$mulVal'", "stored value "+value.toString(), "Check", here)
		}
    }
    else {
	register.put(mulTyp, mulVal)
    }
    if(isTrace(here)) println ("$here: couple has been stored")
}

fun isStored (mulTyp: MultiHashType): Boolean {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)
    
    if(isTrace(here)) println ("$here: input mulTyp '$mulTyp'")

    val result = register.contains(mulTyp)

    if(isTrace(here)) println ("$here: output result '$result'")

    exiting(here)
    return result
}

fun retrieve (mulTyp: MultiHashType): MultiHashValue {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)
    
    val result = register.get(mulTyp)!! // Improve
    if(isTrace(here)) println ("$here: output result '$result'")
    
    exiting(here)
    return result
}
}
