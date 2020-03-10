package io.ipfs.kotlin

/**
 * What is it : The different Types of Immutable files
 * Definition : An Immutable is a file adressed by its content i.e. its hash or CID on Ipfs.
 * Definition : An ImmutableType is parametrized by the Type of its Hash (MultiHashType)
 * Definition : ImmutableBlock
 * Definition : ImmutableCode
 * Definition : ImmutableFriends
 * Definition : ImmutableIdentity
 * Definition : ImmutableLabel
 * Definition : ImmutableSmartContract
 * Definition : ImmutableSymbol
 * Definition : ImmutableTag
 * Definition : ImmutableText
 * Abbreviation : immTyp 
 * Author : Emile Achadde 23 février 2020 at 09:33:04+01:00
 * Revision : companion by Emile Achadde 28 février 2020 at 15:45:40+01:00
 * Revision : hashOf() by Emile Achadde 01 mars 2020 at 13:03:06+01:00
 */

sealed class ImmutableType () {
    data class ImmutableTypeBlock (val multiHash: MultiHashType) : ImmutableType()
    data class ImmutableTypeProgram (val multiHash: MultiHashType) : ImmutableType()
    data class ImmutableTypeFriends (val multiHash: MultiHashType) : ImmutableType()
    data class ImmutableTypeIdentity (val multiHash: MultiHashType) : ImmutableType()
    data class ImmutableTypeLabel (val multiHash: MultiHashType) : ImmutableType()
    data class ImmutableTypeSmartContract (val multiHash: MultiHashType) : ImmutableType()
    data class ImmutableTypeSymbol (val multiHash: MultiHashType) : ImmutableType()
    data class ImmutableTypeTag (val multiHash: MultiHashType) : ImmutableType()
    data class ImmutableTypeText (val multiHash: MultiHashType) : ImmutableType()

    fun hashOf (): String {
	val (here, caller) = moduleHereAndCaller()
	entering(here, caller)

	val mulHas = multiHashTypeOf()
	val result = mulHas.hashOf()

	if(isTrace(here)) println ("$here: output result '$result'")
	
	exiting(here)
	return result
    }

    fun multiHashTypeOf(): MultiHashType {
	val (here, caller) = moduleHereAndCaller()
	entering(here, caller)

	val result =
	    when (this) {
		is ImmutableTypeBlock -> this.multiHash
		is ImmutableTypeProgram -> this.multiHash
		is ImmutableTypeFriends -> this.multiHash
		is ImmutableTypeIdentity -> this.multiHash
		is ImmutableTypeLabel -> this.multiHash 
		is ImmutableTypeSmartContract -> this.multiHash
		is ImmutableTypeSymbol -> this.multiHash
		is ImmutableTypeTag -> this.multiHash 
		is ImmutableTypeText -> this.multiHash 
	    }
	if(isTrace(here)) println ("$here: output result '$result'")
	
	exiting(here)
	return result
    }

    fun nameOf (): String {
	val (here, caller) = moduleHereAndCaller()
	entering(here, caller)
	val result =
	    when (this) {
		is ImmutableTypeBlock -> "ImmutableTypeBlock"
		is ImmutableTypeProgram -> "ImmutableTypeProgram"
		is ImmutableTypeFriends -> "ImmutableTypeFriends"
		is ImmutableTypeIdentity -> "ImmutableTypeIdentity"
		is ImmutableTypeLabel -> "ImmutableTypeLabel"
		is ImmutableTypeSmartContract -> "ImmutableTypeSmartContract"
		is ImmutableTypeSymbol -> "ImmutableTypeSymbol"
		is ImmutableTypeTag -> "ImmutableTypeTag"
		is ImmutableTypeText -> "ImmutableTypeText"
	    }
	if(isTrace(here)) println ("$here: output result '$result'")
	
	exiting(here)
	return result
    }

    fun stringOf (): String {
	val (here, caller) = moduleHereAndCaller()
	entering(here, caller)
	val mulHas = multiHashTypeOf()
	val hash = mulHas.hashOf()
	val result = 
	    when (this) {
		is ImmutableTypeBlock -> "ImmutableTypeBlock("+hash+")"
		is ImmutableTypeProgram -> "ImmutableTypeProgram("+hash+")"
		is ImmutableTypeFriends -> "ImmutableTypeFriends("+hash+")"
		is ImmutableTypeIdentity -> "ImmutableTypeIdentity("+hash+")"
		is ImmutableTypeLabel -> "ImmutableTypeLabel("+hash+")"
		is ImmutableTypeSmartContract -> "ImmutableTypeSmartContract("+hash+")"
		is ImmutableTypeSymbol -> "ImmutableTypeSymbol("+hash+")"
		is ImmutableTypeTag -> "ImmutableTypeTag("+hash+")"
		is ImmutableTypeText -> "ImmutableTypeText("+hash+")"
	    }

	if(isTrace(here)) println ("$here: output result '$result'")
	
	exiting(here)
	return result
    }

    companion object {
	fun make(typ: String, mulHas: MultiHashType): ImmutableType {
	    val (here, caller) = moduleHereAndCaller()
	    entering(here, caller)

	    if(isTrace(here)) println ("$here: input typ '$typ'")
	    if(isTrace(here)) println ("$here: input mulHas '$mulHas'")

	    val typLow = typ.toLowerCase()
	    val result =
		when (typLow) {
		    "block" -> ImmutableTypeBlock(mulHas)
		    "code" -> ImmutableTypeProgram(mulHas)
		    "friends" -> ImmutableTypeFriends(mulHas)
		    "identity" -> ImmutableTypeIdentity(mulHas)
		    "mabel" -> ImmutableTypeLabel(mulHas)
		    "smartContract" -> ImmutableTypeSmartContract(mulHas)
		    "symbol" -> ImmutableTypeSymbol(mulHas)
		    "tag" -> ImmutableTypeTag(mulHas)
		    "text" -> ImmutableTypeText(mulHas)
		    else -> { fatalErrorPrint("Type is one of 'Block' 'Code' 'Friends' 'Identity' 'Label' 'SmartContract' 'Symbol' 'Tag' 'Text'", "'$typ'", "Check", here)}
	    } // when
	if(isTrace(here)) println ("$here: output result '$result'")
	
	exiting(here)
	return result
	}
    } // companion 
}
