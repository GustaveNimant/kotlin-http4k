package io.ipfs.kotlin

import java.io.File
import java.io.InputStream

/**
 * Author : Emile Achadde 25 février 2020 at 17:00:54+01:00
 */

fun byteArrayOfFilePath(filPat: String): ByteArray {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    val file = File(filPat)
    val result:ByteArray = file.readBytes()
    
    exiting(here)
    return result
}

fun fileExtensionOfFilePath (filPat:String): String {
    val file = File(filPat)
    val ext = file.extension
    return ext
}

fun inputStreamOfFilePath(filPat: String): InputStream {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    val file = File(filPat)
    val result:InputStream = file.inputStream()
    
    exiting(here)
    return result
}

fun isFilePathOfWord(wor: String): Boolean {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input wor '$wor'")
    
    val pattern = Regex("""^((\.)?/(\.)?\w[a-zA-Z_0-9]*)(/([a-zA-Z_0-9]+))*\.\w+$""")
    if (isTrace(here)) println("$here: input wor '$wor'")
    val result = pattern.matches(wor)

    if (isTrace(here)) println ("$here: output result '$result'")	
    exiting(here)
    return result
}

fun lineListOfFileName (filPat: String) : List<String> {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input filPat '$filPat'")

    val result = try {
	File(filPat).readLines()
    }
    catch(e: java.io.IOException) {
	fatalErrorPrint("file '$filPat' were read", "it failed reading", "Check",here)
    }
    
    if (isTrace(here)) println ("$here: output result '$result'")	
    exiting(here)
    return result
}

fun outputWriteOfFilePath(filPat: String, content: String) {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input filPat '$filPat'")
    
    File(filPat).bufferedWriter().use { out -> out.write(content)}

    val lenStr = content.length
    println ("$here: $lenStr Bytes written to file '$filPat'")	
    exiting(here)
}

fun provideAnyFileNameOfWhat(what: String): String {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input what '$what'")
    val whatLc = what.toLowerCase()
    var result =
      when (whatLc) {
        "lexeme" -> "test.lex"
	"block" -> what+".yml"
	"yml" -> "test.yml"
        else -> what+".txt"
      }
    println("$here: enter file name for '$what'. Default '$result'")
    val any_f = standardInputReadLine()
    if (! (any_f.isNullOrBlank() || any_f.equals("null"))) {
        result = any_f
    }
    
    println("$here: output result '$result'")

    exiting(here)
    return result
}

fun standardInputReadLine(): String {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)
	
    val str = readLine().toString()
    
    exiting(here)
    return str
}

fun stringListOfFilePath(filPat: String): List<String> {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    val file = File(filPat)
    val bufferedReader = file.bufferedReader()

    val result:List<String> = try {
	bufferedReader.readLines()
    }
    catch(e: java.io.IOException) {
	fatalErrorPrint("file '$filPat' were read", "it failed reading", "Check",here)
    }

    exiting(here)
    return result
}

fun stringReadOfFilePath(filPat: String): String {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    val file = File(filPat)
    val result: String = 
	try {
	    file.readText() 
	}
    catch(e: java.io.IOException) {
	fatalErrorPrint("file '$filPat' were read", "it failed reading", "Check",here)
    }
    
    exiting(here)
    return result
}

