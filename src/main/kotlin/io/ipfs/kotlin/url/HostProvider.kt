package io.ipfs.kotlin.url

import io.ipfs.kotlin.*
import java.io.File
import java.util.Stack

/**
 * What is it : the Provider storing an Host knowing its Type.
 * Author : Emile Achadde 26 février 2020 at 17:39:09+01:00
 */

class HostProvider {

    val register = HostRegister()

    private fun hostIntFromParameterMap(): Int {
	val (here, caller) = moduleHereAndCaller()
	entering(here, caller)
	
	val result = 
	    if (ParameterMap.containsKey("host")) { 
              val str = ParameterMap.getValue("host").first()
	      str.toInt()					    
	    }
	else {
	    5001
	}
	exiting(here)
	return result 
    }

    private fun hostNameFromParameterMap(): String {
	val (here, caller) = moduleHereAndCaller()
	entering(here, caller)

	val result = 
	    if (ParameterMap.containsKey("host")) { 
	      ParameterMap.getValue("host").first()
	    }
	else {
	    fatalErrorPrint ("host has been defined by User", "it has not", "Enter commanf : --args:\"host <int>\"", here)
	}

	exiting(here)
	return result 
    }

    private fun build(HosTyp: HostType): HostValue {
	val (here, caller) = moduleHereAndCaller()
	entering(here, caller)

	if(isTrace(here)) println ("$here: input HosTyp '$HosTyp'")

	val result = 
	    when (HosTyp) {
		is HostType.HostUserDefined -> {
		    val nam = hostNameFromParameterMap()
		    HostValue(nam)
		}
		is HostType.HostLocal -> HostValue("127.0.0.1")
		is HostType.HostRemote -> HostValue("somehost")
	    }
	
	if(isTrace(here)) println ("$here: output result $result")
	
	exiting(here)
	return result
    }

    private fun buildAndStoreUrl(HosTyp: HostType) {
	val (here, caller) = moduleHereAndCaller()
	entering(here, caller)

	if(isTrace(here)) println ("$here: input HosTyp '$HosTyp'")
    
	val PorVal = build(HosTyp)
	register.store (HosTyp, PorVal)
	
	exiting(here)
	return
    }
    
    fun provideHost(HosTyp: HostType) : HostValue {
	val (here, caller) = moduleHereAndCaller()
	entering(here, caller)
	
	if (register.isEmpty()){
	    buildAndStoreUrl(HosTyp)
	}
	
	val result = register.retrieve(HosTyp)!!
	
	if (isTrace(here)) println("$here: output result '$result'")
	exiting(here)
	return result
    }
    

 }
