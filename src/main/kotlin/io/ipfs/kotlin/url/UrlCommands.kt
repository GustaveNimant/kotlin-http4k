package io.ipfs.kotlin.url

import io.ipfs.kotlin.*
import io.ipfs.kotlin.defaults.*

import java.util.Stack
import kotlin.system.exitProcess

/**
 * Author : Emile Achadde 27 février 2020 at 14:04:42+01:00
 */

fun executeHostOfWordList(wor_l: List<String>) {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)
    
    // Ex.: -host <HostType> <Integer>
    var done = false
    if(isTrace(here)) println ("$here: input wor_l '$wor_l'")
    var wor_s = wordStackOfWordList(wor_l)

    val hosReg = HostRegister()
    
    while (!done) {
	try {
	    val wor = wor_s.pop()
	    if(isLoop(here)) println("$here: wor '$wor'")
	    
	    val hosTyp = HostType.make (wor)
	    when (hosTyp) {
		is HostType.HostUserDefined,
		is HostType.HostLocal,
		is HostType.HostRemote -> {
 		    val worNex = wor_s.pop()
		    if(isLoop(here)) println("$here: worNex '$worNex'")
		    val hosVal = HostValue(worNex)
		    hosReg.store(hosTyp, hosVal)
		}		    
	    } // when hosTyp
	    } // try
	catch (e: java.util.EmptyStackException) {done = true} // catch
	    
    } // while

    if(isTrace(here)){
    	println ("Host Register is:")
	for ( (k, v) in hosReg.register) {
	    println ("$k => $v")
	}
    }
    exiting(here)
}

fun executePortOfWordList(wor_l: List<String>) {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)
    
    // Ex.: -port <PortType> <Integer>
    var done = false
    if(isTrace(here)) println ("$here: input wor_l '$wor_l'")
    var wor_s = wordStackOfWordList(wor_l)

    val porReg = PortRegister()
    
    while (!done) {
	try {
	    val wor = wor_s.pop()
	    if(isLoop(here)) println("$here: wor '$wor'")
	    
	    val porTyp = PortType.make (wor)
	    when (porTyp) {
		is PortType.PortUserDefined -> {
 		    val worNex = wor_s.pop()
		    if(isLoop(here)) println("$here: worNex '$worNex'")
		    val int: Int = worNex.toInt() 
		    val porVal = PortValue(int)
		    porReg.store(porTyp, porVal)
		}		    
		is PortType.PortGateway -> {
		    val porVal = PortValue(5001)
		    porReg.store(porTyp, porVal)
		}
		is PortType.PortWebui -> {
		    val porVal = PortValue(5001)
		    porReg.store(porTyp, porVal)
		}
	    } // when porTyp
	    } // try
	catch (e: java.util.EmptyStackException) {done = true} // catch
	    
    } // while

    if(isTrace(here)){
    	println ("Port Register is:")
	for ( (k, v) in porReg.register) {
	    println ("$k => $v")
	}
    }
    exiting(here)
}

