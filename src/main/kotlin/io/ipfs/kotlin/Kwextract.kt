package io.ipfs.kotlin

import kotlin.system.exitProcess
import io.ipfs.kotlin.defaults.*
import java.io.File
import java.util.Stack

/**
 * kwextract (file: String) : Map<String, String>
 * Author : Emile Achadde 04 mars 2020 at 19:21:12+01:00
 */

fun kwextract(filPat: String): MutableMap<String, String> {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    if(isTrace(here)) println("$here: input filPat $filPat")

    val lin_l = try {lineListOfFileName(filPat)}
    catch(e: java.io.FileNotFoundException) {
	fatalErrorPrint("input file path '$filPat' existed","it did not","Check the file name",here)}
    
    var result = mutableMapOf<String, String>()
    for (line in lin_l) {
	if(isLoop(here)) println("$here: line $line")

	var pattern = Regex("""\$(qm|source|parents|mutable|previous|next|tic|spot):\s*([^\\\$]*?)\s*\$""")
	if (pattern.containsMatchIn(line)) {
	    var matRes = pattern.find(line)
	    var (key, value) = matRes!!.destructured
	    result.put(key, value)
	    if(isLoop(here)) println("$here: for key '$key' value '$value'")
	}
    }
    
    if(isTrace(here)) println("$here: output result $result")
    exiting(here)
    return result
    }
    
