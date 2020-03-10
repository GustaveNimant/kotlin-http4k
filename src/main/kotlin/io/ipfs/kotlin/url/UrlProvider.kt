package io.ipfs.kotlin.url

import io.ipfs.kotlin.*
import java.io.File
import java.util.Stack

/**
 * What is it : the Provider providing an Url knowing its UrlType.
 * Example : (LocalIpfsApi, "127.0.0.1:5001") 
 * Author : Emile Achadde 25 février 2020 at 19:03:02+01:00
 * Revision : make by Emile Achadde 28 février 2020 at 09:46:19+01:00
 */

class UrlProvider {

    val register = UrlRegister()
    
    private fun build(hosVal: HostValue, porVal: PortValue): UrlValue {
	val (here, caller) = moduleHereAndCaller()
	entering(here, caller)
	
	if(isTrace(here)) println ("$here: input hosVal '$hosVal'")
	if(isTrace(here)) println ("$here: input porVal '$porVal'")
    
	val result = UrlValue(hosVal, porVal)
	
	if(isTrace(here)) println ("$here: output UrlValue $result")
	
	exiting(here)
	return result
    }

    fun portTypeFromParameterMap(): PortType {
	val (here, caller) = moduleHereAndCaller()
	entering(here, caller)
	
	val result = 
	    if (ParameterMap.containsKey("port")) { 
              val wor = ParameterMap.getValue("port").first() // -port <type> [<integer>] 
	      PortType.make(wor)
	    }
	else {
	    PortType.PortWebui
	}
	
	if(isTrace(here)) println ("$here: output result $result")

	exiting(here)
	return result 
    }

    private fun hostTypeFromParameterMap(): HostType {
	val (here, caller) = moduleHereAndCaller()
	entering(here, caller)
	
	val result = 
	    if (ParameterMap.containsKey("host")) {
		val wor = ParameterMap.getValue("host").first() // -host <type> <hostname>
		HostType.make (wor)
	    }
	else {
               HostType.HostLocal
	}

	if(isTrace(here)) println ("$here: output result $result")
	return result 
    }
    
    private fun buildAndStoreUrl(UrlTyp: UrlType, hosVal: HostValue, porVal: PortValue) {
	val (here, caller) = moduleHereAndCaller()
	entering(here, caller)

	if(isTrace(here)) println ("$here: input UrlTyp '$UrlTyp'")
	if(isTrace(here)) println ("$here: input hosVal '$hosVal'")
	if(isTrace(here)) println ("$here: input porVal '$porVal'")
    
	val UrlVal = build(hosVal, porVal)
	register.store (UrlTyp, UrlVal)
	
	exiting(here)
	return
    }
    
    public fun provideUrl(UrlTyp: UrlType) : UrlValue {
	val (here, caller) = moduleHereAndCaller()
	entering(here, caller)
	
	if (register.isEmpty()){
	    val hosPro = HostProvider ()
	    val hosTyp = hostTypeFromParameterMap() 
	    val hosVal = hosPro.provideHost(hosTyp)   
	    val porPro = PortProvider ()
	    val porTyp = portTypeFromParameterMap() 
	    val porVal = porPro.providePort(porTyp)   
	    buildAndStoreUrl(UrlTyp, hosVal, porVal)
	}
	
	val result = register.retrieve(UrlTyp)!!
	
	if (isTrace(here)) println("$here: output result '$result'")
	exiting(here)
	return result
    }

    public fun printOfUrlType (UrlTyp: UrlType) {
	val (here, caller) = moduleHereAndCaller()
	entering(here, caller)
	
	val url = provideUrl (UrlTyp)
	val str = url.toString()
	println ("UrlType $UrlTyp => $str")
	exiting(here)
    }

}
