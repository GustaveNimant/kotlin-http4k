package io.ipfs.kotlin.url

import io.ipfs.kotlin.*
import java.io.File
import java.util.Stack

/**
 * What is it : the Provider and Register storing an Port knowing its Type.
 * Example : (LocalIpfsApi, "127.0.0.1:5001") 
 * What to do : provide host and port by asking if stored in ParameterMap 
 * Author : Emile Achadde 25 février 2020 at 19:03:02+01:00
 */

class PortProvider {

    val register = PortRegister()

    private fun portIntFromParameterMap(): Int {
	val (here, caller) = moduleHereAndCaller()
	entering(here, caller)
	
	val result = 
	    if (ParameterMap.containsKey("port")) { 
              val str = ParameterMap.getValue("port").first()
	      str.toInt()					    
	    }
	else {
	    5001
	}
	exiting(here)
	return result 
    }

    private fun portNameFromParameterMap(): String {
	val (here, caller) = moduleHereAndCaller()
	entering(here, caller)

	val result = 
	    if (ParameterMap.containsKey("port")) { 
	      ParameterMap.getValue("port").first()
	    }
	else {
	    fatalErrorPrint ("port has been defined by User", "it has not", "Enter commanf : --args:\"port <int>\"", here)
	}
	exiting(here)
	return result 
    }

    private fun build(PorTyp: PortType): PortValue {
	val (here, caller) = moduleHereAndCaller()
	entering(here, caller)

	if(isTrace(here)) println ("$here: input PorTyp '$PorTyp'")

	val result = 
	    when (PorTyp) {
		is PortType.PortUserDefined -> {
		    val int = portIntFromParameterMap()
		    PortValue(int)
		}
		is PortType.PortWebui -> PortValue(5001)
		is PortType.PortGateway -> PortValue(5001)
	    }
	
	if(isTrace(here)) println ("$here: output result $result")
	
	exiting(here)
	return result
    }

    private fun buildAndStoreUrl(PorTyp: PortType) {
	val (here, caller) = moduleHereAndCaller()
	entering(here, caller)

	if(isTrace(here)) println ("$here: input PorTyp '$PorTyp'")
    
	val PorVal = build(PorTyp)
	register.store (PorTyp, PorVal)
	
	exiting(here)
	return
    }
    
    fun providePort(PorTyp: PortType) : PortValue {
	val (here, caller) = moduleHereAndCaller()
	entering(here, caller)
	
	if (register.isEmpty()){
	    buildAndStoreUrl(PorTyp)
	}
	
	val result = register.retrieve(PorTyp)!!
	
	if (isTrace(here)) println("$here: output result '$result'")
	exiting(here)
	return result
    }
    

 }
