package io.ipfs.kotlin.url

import io.ipfs.kotlin.*
import java.io.File
import java.util.Stack

/**
 * What is it : the Register storing a Port knowing its Type.
 * Example : (PortWebui, "5001") 
 * What to do : provide host and port by asking if stored in ParameterMap 
 * Author : Emile Achadde 25 février 2020 at 19:03:02+01:00
 */

class PortRegister {

    var register : MutableMap<PortType, PortValue> = mutableMapOf<PortType, PortValue>()
	 
    fun isEmpty (): Boolean {
	val (here, caller) = moduleHereAndCaller()
	entering(here, caller)

	val result = register.isEmpty()

	if(isTrace(here)) println ("$here: output result $result")
        return result
     }

    fun isStored (porTyp: PortType): Boolean {
	val (here, caller) = moduleHereAndCaller()
	entering(here, caller)
	
	if(isTrace(here)) println ("$here: input porTyp '$porTyp'")
	
	val result = if (register.containsKey(porTyp)) {
	    (register.get(porTyp)!!.isEmpty())
	}
	else {false}
	
	if(isTrace(here)) println ("$here: output result '$result'")
	
	exiting(here)
	return result
    }
    
    fun store (porTyp: PortType, porVal: PortValue) {
	val (here, caller) = moduleHereAndCaller()
	entering(here, caller)
	
	if(isTrace(here)) println ("$here: input porTyp '$porTyp'")

	if (isStored(porTyp)) {
	    val value = retrieve(porTyp)
	    if (value != porVal) {
		fatalErrorPrint("already stored Port Value '$value' for Port Type '$porTyp' were equal to new one", porVal.toString(), "Check", here)
	    }
	}
	else {
	    register.put(porTyp, porVal)
	}
	if(isTrace(here)) println ("$here: ($porTyp, $porVal) couple has been stored")
    }
    
    fun retrieve(porTyp: PortType): PortValue? {
         val (here, caller) = moduleHereAndCaller()
    	 entering(here, caller)

	 val result = register.get(porTyp)
	 
	 if (result!!.isEmpty()) {
	   fatalErrorPrint("an Port Value existed for Port Type '$porTyp'", "it did not","Check", here)
         }
	 if(isTrace(here)) println ("$here: output result '$result'")
	 exiting(here)
	 return result
    }

}


