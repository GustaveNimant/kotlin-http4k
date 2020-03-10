package io.ipfs.kotlin

/**
 * What is it : The different Types of Immutable files
 * Definition : An Immutable is a file adressed by its content i.e. its hash or CID on Ipfs.
 * Definition : An immutableType is parametrized by the Type of its Hash (MultiHashType)
 * Definition : immutableBlock
 * Definition : immutableCode
 * Definition : immutableFriends
 * Definition : immutableIdentity
 * Definition : immutableLabel
 * Definition : immutableSmartContract
 * Definition : immutableSymbol
 * Definition : immutableTag
 * Definition : immutableText
 * Abbreviation : immTyp 
 * Author : Emile Achadde 23 février 2020 at 09:33:04+01:00
 * Revision : companion by Emile Achadde 28 février 2020 at 15:45:40+01:00
 * Revision : hashOf() by Emile Achadde 01 mars 2020 at 13:03:06+01:00
 */

sealed class immutableType () {
    data class immutableTypeSUB01 (val multiHash: MultiHashType) : immutableType()
    data class immutableTypeProgram (val multiHash: MultiHashType) : immutableType()
    data class immutableTypeFriends (val multiHash: MultiHashType) : immutableType()
    data class immutableTypeIdentity (val multiHash: MultiHashType) : immutableType()
    data class immutableTypeLabel (val multiHash: MultiHashType) : immutableType()
    data class immutableTypeSmartContract (val multiHash: MultiHashType) : immutableType()
    data class immutableTypeSymbol (val multiHash: MultiHashType) : immutableType()
    data class immutableTypeTag (val multiHash: MultiHashType) : immutableType()
    data class immutableTypeText (val multiHash: MultiHashType) : immutableType()

    fun hashOf (): String {
	val (here, caller) = moduleHereAndCaller()
	entering(here, caller)

	val mulHas = multiHashOf()
	val result = mulHas.hashOf()

	if(isTrace(here)) println ("$here: output result '$result'")
	
	exiting(here)
	return result
    }

    fun multiHashOf(): MultiHashType {
	val (here, caller) = moduleHereAndCaller()
	entering(here, caller)

	val result =
	    when (this) {
		is immutableTypeSUB01 -> this.multiHash
		is immutableTypeProgram -> this.multiHash
		is immutableTypeFriends -> this.multiHash
		is immutableTypeIdentity -> this.multiHash
		is immutableTypeLabel -> this.multiHash 
		is immutableTypeSmartContract -> this.multiHash
		is immutableTypeSymbol -> this.multiHash
		is immutableTypeTag -> this.multiHash 
		is immutableTypeText -> this.multiHash 
	    }
	if(isTrace(here)) println ("$here: output result '$result'")
	
	exiting(here)
	return result
    }

    fun nameOf (): String {
	val (here, caller) = moduleHereAndCaller()
	entering(here, caller)
	val mulHas = multiHashOf() 
	val result =
	    when (this) {
		is immutableTypeSUB01 -> "immutableTypeSUB01"
		is immutableTypeProgram -> "immutableTypeProgram"
		is immutableTypeFriends -> "immutableTypeFriends"
		is immutableTypeIdentity -> "immutableTypeIdentity"
		is immutableTypeLabel -> "immutableTypeLabel"
		is immutableTypeSmartContract -> "immutableTypeSmartContract"
		is immutableTypeSymbol -> "immutableTypeSymbol"
		is immutableTypeTag -> "immutableTypeTag"
		is immutableTypeText -> "immutableTypeText"
	    }
	if(isTrace(here)) println ("$here: output result '$result'")
	
	exiting(here)
	return result
    }

    fun stringOf (): String {
	val (here, caller) = moduleHereAndCaller()
	entering(here, caller)
	val mulHas = multiHashOf()
	val hash = mulHas.hashOf()
	val result = 
	    when (this) {
		is immutableTypeSUB01 -> "immutableTypeSUB01("+hash+")"
		is immutableTypeProgram -> "immutableTypeProgram("+hash+")"
		is immutableTypeFriends -> "immutableTypeFriends("+hash+")"
		is immutableTypeIdentity -> "immutableTypeIdentity("+hash+")"
		is immutableTypeLabel -> "immutableTypeLabel("+hash+")"
		is immutableTypeSmartContract -> "immutableTypeSmartContract("+hash+")"
		is immutableTypeSymbol -> "immutableTypeSymbol("+hash+")"
		is immutableTypeTag -> "immutableTypeTag("+hash+")"
		is immutableTypeText -> "immutableTypeText("+hash+")"
	    }

	if(isTrace(here)) println ("$here: output result '$result'")
	
	exiting(here)
	return result
    }

    companion object {
	fun make(typ: String, mulHas: MultiHashType): immutableType {
	    val (here, caller) = moduleHereAndCaller()
	    entering(here, caller)

	    if(isTrace(here)) println ("$here: input typ '$typ'")
	    if(isTrace(here)) println ("$here: input mulHas '$mulHas'")

	    val typLow = typ.toLowerCase()
	    val result =
		when (typLow) {
		    "block" -> immutableTypeSUB01(mulHas)
		    "code" -> immutableTypeProgram(mulHas)
		    "friends" -> immutableTypeFriends(mulHas)
		    "identity" -> immutableTypeIdentity(mulHas)
		    "mabel" -> immutableTypeLabel(mulHas)
		    "smartContract" -> immutableTypeSmartContract(mulHas)
		    "symbol" -> immutableTypeSymbol(mulHas)
		    "tag" -> immutableTypeTag(mulHas)
		    "text" -> immutableTypeText(mulHas)
		    else -> { fatalErrorPrint("Type is one of 'SUB01' 'Code' 'Friends' 'Identity' 'Label' 'SmartContract' 'Symbol' 'Tag' 'Text'", "'$typ'", "Check", here)}
	    } // when
	if(isTrace(here)) println ("$here: output result '$result'")
	
	exiting(here)
	return result
	}
    } // companion 
}
