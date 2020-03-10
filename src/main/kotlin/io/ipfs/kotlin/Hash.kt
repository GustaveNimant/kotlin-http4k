package io.ipfs.kotlin

import java.util.Base64
import java.util.Stack
import java.security.MessageDigest

/**
 * Command : gradlew run --args="-hash <type> <length>"
 * Example : gradlew run --args="-hash sha 256"
 * Author : Emile Achadde 04 mars 2020 at 10:40:15+01:00
 */

fun hashStringOfTypeOfInput(typ: String, inp: String): String {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    if(isTrace(here)) println("$here: typ '$typ'")
    if(isTrace(here)) println("$here: inp '$inp'")

    val HEX_CHARS = "0123456789ABCDEF"
    val bytes = try {MessageDigest
        .getInstance(typ)
        .digest(inp.toByteArray())
    }
    catch (e: java.security.NoSuchAlgorithmException) {
	fatalErrorPrint("the hash function type '$typ' existed", "it did not","modify type with for example -hashfunction sha 256", here)}
    
    val strBui = StringBuilder(bytes.size * 2)
    
    bytes.forEach {
        val i = it.toInt()
        strBui.append(HEX_CHARS[i shr 4 and 0x0f])
        strBui.append(HEX_CHARS[i and 0x0f])
    }
    
    val result = strBui.toString()
    if(isTrace(here)) println ("$here: result '$result'")

    return result
    exiting(here)
}

fun hashFunctionTypeOfTypeOfLength (typ:String, len: Int): String {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)
    // Ex.: (-has)h sha 256

    if(isTrace(here)) println("$here: typ '$typ'")
    if(isTrace(here)) println("$here: len '$len'")

    val result = when (typ.toLowerCase()) {
	"sha" -> typ + "-" + len.toString()
        "md" -> typ + len.toString()
	else -> {
	    fatalErrorPrint("the hash function type '$typ' were 'sha' or 'md'", "'$typ'","modify type with for example -hashfunction sha 256", here)}
    }
    
    if(isDebug(here)) println ("$here: result '$result'")
    return result
    exiting(here)
}

fun hashFunctionType(): String {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    val parMap = ParameterMap
    if(isTrace(here)) println("$here: input parMap '$parMap'") 

    val wor_l = try {ParameterMap.getValue("hashfunction")}
                catch(e: java.util.NoSuchElementException) {
		    fatalErrorPrint("the hash function type were defined in the input", "it is not","enter for example -hashfunction sha 256", here)}
    
    val hasTyp = wor_l.component1()
    val hasLen = wor_l.component2().toInt()
    if (isDebug(here)) println("$here: hasTyp '$hasTyp'")
    if (isDebug(here)) println("$here: hasLen '$hasLen'")

    val result = hashFunctionTypeOfTypeOfLength (hasTyp, hasLen)

    // Ex.: (-has)h sha 256

    if(isDebug(here)) println ("$here: result '$result'")

    return result
    exiting(here)
}

fun hashInputString(): String {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    val parMap = ParameterMap
    if(isTrace(here)) println("$here: input parMap '$parMap'") 

    val wor_l = try {ParameterMap.getValue("hashinput")}
                catch(e: java.util.NoSuchElementException) {
		    fatalErrorPrint("and input string were defined in the input", "it is not","enter for example -hashinput some string", here)}
    
    
    val str = stringOfGlueOfStringList(" ", wor_l)
    val result = // file path case
    if (isFilePathOfWord(str)) {
	stringReadOfFilePath(str)
    }
    else {
	str
    }
    if(isDebug(here)) println ("$here: result '$result'")

    return result
    exiting(here)
}
