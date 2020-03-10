package io.ipfs.kotlin

import kotlin.system.exitProcess
import java.io.File
import java.io.InputStream
import java.lang.Character.MIN_VALUE as nullChar
import java.util.Base64
import java.util.Stack

/**
 * What is it : some basic tools
 * Done : 25 février 2020 MutableTree => Tree
 * Remark : need to define TrreNode as data class to get non empty results.
 * Author : Emile Achadde 25 février 2020 at 16:31:52+01:00
 */

class MutableTreeNode<T>(value:T){
    var value:T = value

    var parent:MutableTreeNode<T>? = null

    var children:MutableList<MutableTreeNode<T>> = mutableListOf()

    fun addChild(node:MutableTreeNode<T>){
        children.add(node)
        node.parent = this
    }

    fun toTreeNode(): TreeNode<T> {

	val sib_l = children.toList()
	val nodImm = TreeNode(value, sib_l.map { sib -> sib.toTreeNode()})
	return nodImm
	}

    override fun toString(): String {
        var s = "${value}"
        if (!children.isEmpty()) {
            s += " {" + children.map { it.toString() } + " }"
        }
        return s
    }
}

// data class LeafNode<T>(val value:T):TreeNode<T>

data class TreeNode<T>(val value:T, val children : List<TreeNode<T>>)

data class pairString (val first: String, val second: String)

var ParameterMap = mapOf<String, List<String>>() // Global Immutable

var level = 0

var dots = "........|........|........|........|........|........|........|"

fun <T> teeStackOfTeeList (tee_l: List<T>): Stack<T> {
    var stack = Stack<T>()
    tee_l.reversed().forEach { t -> stack.push (t)}
    return stack

}

fun <T> teeStackFromTeeOfTeeStack (tee:T, tee_s: Stack<T>): Stack<T> {
// return the subStack where all elements untill tee (excluded) are poped-off

   var cha = tee_s.pop()
   var done = false
   
   if (cha == tee) {return tee_s}

   while (! done) {
      try {
      	cha = tee_s.pop()
	done = tee == cha
      }
      catch (e: java.util.EmptyStackException) {
            done = true			       
      }
    }
	
    return tee_s
}

fun callerName(): String {
    val sta = Thread.currentThread().stackTrace
    val result = 
	try {
	    (sta[3]).getMethodName()
	}
    catch (e: ArrayIndexOutOfBoundsException) {
	"None"}

    return result	
}

fun characterStackOfString (str: String) : Stack<Char> {
    var stack = Stack<Char>()
    str.reversed().forEach { c -> stack.push(c)}
    return stack
}

fun countOfCharOfString (cha: Char, str: String) : Int {
    val (here, caller) = hereAndCaller()
    entering(here, caller)

    var count = 0

    for (c in str) {
        if (c == cha){count = count + 1}
    }	
		
    exiting(here + " with count " + count.toString())
    return count
}

fun entering(here: String, caller: String):Unit {
    if(isEnterExit(here)) {
	level = level + 1
	if (level > 70) {
	    println ("Error maximum number of nesting levels reached")
	} else {
            var points = dots.substring(0, level)
            println("$points Entering  in $here called by $caller")
	}
    }
}

fun exiting(here: String):Unit {
    if(isEnterExit(here)) {
	var points = dots.substring(0, level)
	println("$points Exiting from $here")
	level = level - 1	
    }
}

fun fatalErrorPrint (expecting: String, found: String, cure: String, where: String): Nothing {
  val message: String = "\n$where: Expecting $expecting\n" + "$where: Found $found\n" + "$where: Cure: $cure\n"

  throw Exception(message)
}

fun functionName(): String {
    val sta = Thread.currentThread().stackTrace[2]
    val str = sta.getMethodName()
    return str	
}

fun hereAndCaller(): Pair<String, String> {
    val sta = Thread.currentThread().stackTrace
    val here = (sta[2]).getMethodName()
    val caller = 
	try {
	    (sta[3]).getMethodName()
	}
    catch (e: ArrayIndexOutOfBoundsException) {
	"None"}
    
    val result = Pair(here, caller) 
    return result
}

fun isDebug(here:String): Boolean {
  if (ParameterMap.containsKey("debug")) { 
    val debug_l = ParameterMap.getValue("debug")
    val result = debug_l.contains("all") || debug_l.contains(here)
    return result 
  }
  else {return false}
}

fun isEnterExit(here:String): Boolean {
  if (ParameterMap.containsKey("enterexit")) { 
    val entexi_l = ParameterMap.getValue("enterexit")
    val result = entexi_l.contains("all") || entexi_l.contains(here)
    return result
  }
  else {return false}
}

fun isLoop(here:String): Boolean {
  if (ParameterMap.containsKey("loop")) { 
    val loop_l = ParameterMap.getValue("loop")
    val result = loop_l.contains("all") || loop_l.contains(here)
    return result 
  }
  else {return false}
}

fun isTrace(here:String): Boolean {
  if (ParameterMap.containsKey("trace")) { 
    val trace_l = ParameterMap.getValue("trace")
    val result = trace_l.contains("all") || trace_l.contains(here)
    return result
  }
  else {return false}
}

fun isVerbose(here:String): Boolean {
  if (ParameterMap.containsKey("verbose")) { 
    val verbose_l = ParameterMap.getValue("verbose")
    val result = verbose_l.contains("all") || verbose_l.contains(here)
    return result 
  }
  else {return false}
}

fun isWhen(here:String): Boolean {
  if (ParameterMap.containsKey("when")) {
    val when_l = ParameterMap.getValue("when")
    val result = when_l.contains("all") || when_l.contains(here)
    return result
  }
  else {return false}
}

fun moduleName(): String {
    val sta = Thread.currentThread().stackTrace[2]
    val str = sta.getFileName()
    val result = str.replace(".kt", "")  
    return result
}

fun moduleAndfunctionName(): Pair<String, String> {
    val sta = Thread.currentThread().stackTrace[2]
    val strFun = sta.getMethodName()
    val strFil = sta.getFileName()
    val strMod = strFil.replace(".kt", "")  
    return Pair(strMod, strFun)
}

fun moduleAndHereAndCaller(): Triple<String, String, String> {
    val sta = Thread.currentThread().stackTrace
    val strFil = (sta[2]).getFileName()
    val module = strFil.replace(".kt", "")  
    val here = (sta[2]).getMethodName()
    val caller = 
	try {
	    (sta[3]).getMethodName()
	}
    catch (e: ArrayIndexOutOfBoundsException) {
	"None"}
    
    val result = Triple(module, here, caller) 
    return result
}

fun moduleHereAndCaller(): Pair<String, String> {
    val sta = Thread.currentThread().stackTrace
    val strFil = (sta[2]).getFileName()
    val module = strFil.replace(".kt", "")  
    val here = (sta[2]).getMethodName()
    val caller = 
	try {
	    (sta[3]).getMethodName()
	}
    catch (e: ArrayIndexOutOfBoundsException) {
	"None"}
    
    val result = Pair(module+"."+here, caller) 
    return result
}

fun notYetImplemented(fun_nam: String){
    throw Exception("Error: function '$fun_nam' is not yet implemented")}

