package io.ipfs.kotlin

import io.ipfs.kotlin.defaults.*

/**
 * Provide : the MultiHashValue for a MultiHashType
 * Needs   : Hash Function name stored in ParameterMap  
 * Needs   : Hash Hashable information (directory, file, string) stored in ParameterMap  
 * Done    : LocalIpfs().add.string(str).MultiHash
 * Author  : Emile Achadde 22 février 2020 at 10:32:18+01:00;
 * Improve : Implement Hash Stuff
 */

class MultiHashProvider {

    val register = MultiHashRegister()
    
    fun build (funNam: String, hasInf: String): MultiHashValue {
	val (here, caller) = moduleHereAndCaller()
	entering(here, caller)
	
	println("$here: input funNam '$funNam'")
	println("$here: input hasInf '$hasInf'")
	
	val hash = hashStringOfTypeOfInput(funNam, hasInf)
	val result = MultiHashValue(funNam, hash)
	
	println("$here: output result $result")
	
	exiting(here)
	return result 
    }
    
    fun buildAndStore(mulTyp: MultiHashType){
	val (here, caller) = moduleHereAndCaller()
	entering(here, caller)
	
	println("$here: input mulTyp '$mulTyp'")
	// Improve get from ParameterMap Input
	val hasFunTyp = hashFunctionType()
	val hasInpStr = hashInputString()
	val mulH = build(hasFunTyp, hasInpStr)
	register.store(mulTyp, mulH)
	
	exiting(here)
    }
    
    fun provide(mulTyp: MultiHashType) : MultiHashValue {
	val (here, caller) = moduleHereAndCaller()
	entering(here, caller)
	
	println("$here: input mulTyp '$mulTyp'")
	
	if (register.isStored(mulTyp)){
	    register.retrieve(mulTyp)
	}
	else {
	    buildAndStore(mulTyp)
	}
	
	val result = register.retrieve(mulTyp)
	if (isTrace(here)) println("$here: output result '$result'")
	
	exiting(here)
	return result
    }
    
    fun print (mulTyp: MultiHashType) {
	val (here, caller) = moduleHereAndCaller()
	entering(here, caller)
	
	val result = provide (mulTyp)
	println ("MultiHashValue: $result")
	exiting(here)
    }

}
