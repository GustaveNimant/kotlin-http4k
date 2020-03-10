package io.ipfs.kotlin

import java.util.Stack
import kotlin.system.exitProcess

/**
 * Execution : gradlew run --args="-ipfs get QmTzX91dhqHRunjCtrt4LdTErREUA5Gg1wFMiJz1bEiQxp"
 * val multihash = LocalIpfs().add.string("test-string").Hash
 * val content = LocalIpfs().get.cat(multihash)
 * val commit = LocalIpfs().info.version()!!.Commit
 * Author : Emile Achadde 22 février 2020 at 15:32:44+01:00
 */

fun executeGenerateOfWordList(wor_l: List<String>) {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)
    //                     1         2         4      5
    //                            ELEMENT  ABBREVIATION 
    // Ex.: (-gen)erate provider immutable    imm
    
    var done = false
    if(isTrace(here)) println ("$here: input wor_l '$wor_l'")
    var wor_s = wordStackOfWordList(wor_l)
    
    while (!done) {
	try {
	    val worKin = wor_s.pop()
	    val wor_3 = try {
		worKin.substring(0,3)
	    }
	    catch(e: java.lang.StringIndexOutOfBoundsException)
	    {
		fatalErrorPrint ("command Kind were one of 'pro'vider 'reg'ister 'typ'e 'val'ue 'hel'p","'"+worKin+"'", "Check input", here)
	    }
	    
	    if(isLoop(here)) println("$here: worKin '$worKin'")
	    
	    when (wor_3) {
		"pro", "reg", "typ", "val" -> { //(-gen)erate provider module-name abbreviation
		    val modNam = wor_s.pop()
		    val abbNam = wor_s.pop()

		    if(isDebug(here)) println ("modNam: '$modNam'")
		    if(isDebug(here)) println ("abbNam: '$abbNam'")
		    wor_s.clear()
		    generate (worKin, modNam, abbNam)
		}
		"hel" -> {
		    wor_s.clear()
		    val hel_l = helpList()
		    val h_l = hel_l.filter({h -> h.contains("-gen ")})
		    printOfStringList(h_l)
    		}
		else -> {
		    fatalErrorPrint ("command Kind were one of 'pro'vider 'reg'ister 'typ'e 'val'ue 'hel'p","'"+worKin+"'", "Check input", here)
		} // else
	    } // when
	} // try
	catch (e: java.util.EmptyStackException) {done = true} // catch
	
    } // while
    exiting(here)
}

fun generate (kin:String, moduleName: String, abbreviation: String) {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)
    //                     1         2         4      5
    //                            ELEMENT  ABBREVIATION 
    // Ex.: (-gen)erate provider immutable    imm

    if(isTrace(here)) println("$here: kin '$kin'")
    if(isTrace(here)) println("$here: moduleName '$moduleName'")
    if(isTrace(here)) println("$here: abbreviation '$abbreviation'")

    val kin_3 = kin.substring(0,3)
    val kind = when (kin_3) {
	"pro" -> "provider"	
	"reg" -> "register"
	"typ" -> "type"	
	"val" -> "value"
	else -> {
	    fatalErrorPrint ("Kind of module to be generated were one of 'pro'vider 'reg'ister 'typ'e 'val'ue","'"+kin+"'", "Check input", here)
	    }
    }
    
    val temNam = "./generator/" + kind + ".template"
    if(isDebug(here)) println ("$here template file '$temNam'")
    val str = stringReadOfFilePath(temNam)
    if(isDebug(here)) println ("$here str '$str'")
    val strELE = str.replace("{{ELEMENT}}", moduleName)
    val strABB = strELE.replace("{{ABBREVIATION}}", abbreviation)

    val filNam = "./generator/" + moduleName + "." + kind + ".kt"
    if(isDebug(here)) println ("$here: filNam '$filNam'")
    
    outputWriteOfFilePath(filNam, strABB)
    
    exiting(here)
}
