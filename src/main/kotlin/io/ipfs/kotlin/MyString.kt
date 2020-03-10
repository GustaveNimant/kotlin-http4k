package io.ipfs.kotlin

import java.util.Stack
import java.lang.Character.MIN_VALUE as nullChar
import java.util.Base64

fun isAlphabeticalOfChar(cha: Char): Boolean {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)
    
    val pattern = Regex("[a-zA-Z_]")
    if (isTrace(here)) println("$here: input cha '$cha'")
    val result = pattern.matches(cha.toString())

    exiting(here + " with result '$result'")
    return result
}

fun isAlphanumericalOfChar(cha: Char): Boolean {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)
    
    val pattern = Regex("[a-zA-Z_0-9]")
    if (isTrace(here)) println("$here: input cha '$cha'")
    val result = pattern.matches(cha.toString())

    exiting(here + " with result '$result'")
    return result
}

fun isIntegerOfString(str: String): Boolean {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)
    
    val pattern = Regex("[0-9][0-9]*")
    if (isTrace(here)) println("$here: input str '$str'")
    val result = pattern.matches(str)
    if (isTrace(here)) println("$here: input result '$result'")
    exiting(here)
    return result
}

fun isNumericalOfChar(cha: Char): Boolean {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)
    
    val pattern = Regex("[0-9]")
    if (isTrace(here)) println("$here: input cha '$cha'")
    val result = pattern.matches(cha.toString())

    exiting(here + " with result '$result'")
    return result
}

fun lineStackOfLineList (str_l: List<String>) : Stack<String> {
    var stack = Stack<String>()
    str_l.reversed().forEach { l -> stack.push(l) }
    return stack
}

fun nextStringAndStackOfEndCharOfCharacterStack(del: Char, cha_s: Stack<Char>): Pair<String, Stack<Char>> {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input del '$del'")
    if (isTrace(here)) println("$here: input cha_s '$cha_s'")
    var done = false
    var str = ""
    var cha = cha_s.pop()
    
    while (! done){
      if (isDebug(here)) println("$here: cha '$cha'")
      str = str.plus(cha.toString())
      try {
      	  cha = cha_s.pop()
          done = cha.equals(del)
	  if (done) {cha_s.push(cha)}
      }
      catch (e: java.util.EmptyStackException) {
            done = true			       
      }
    }

    assert (str.isNotEmpty())
    
    if (isTrace(here)) println("$here: output str '$str'")
    if (isTrace(here)) println("$here: output cha_s '$cha_s'")
    exiting(here)
    return Pair (str, cha_s)
}

fun nextWordAndEndCharOfEndCharListOfString(cha_l: List<Char>, str: String): Pair<String, Char> {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input cha_l '$cha_l'")
    if (isTrace(here)) println("$here: input str '$str'")
    
    var word = ""
    var end_cha = 'x'
    for (c in str){
	  if (isDebug(here)) println("$here: c '$c'")
	  if (cha_l.contains(c)) {
	  end_cha = c
	  break
	  }
	  word = word.plus(c.toString())
    }

    assert (word.isNotEmpty())
    
    if (isTrace(here)) println("$here: output word '$word'")
    exiting(here)
    return Pair(word, end_cha)
}

fun nextWordAndStackOfEndCharOfCharacterStack(del: Char, cha_s: Stack<Char>): Pair<String, Stack<Char>> {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input del '$del'")
    if (isTrace(here)) println("$here: input cha_s '$cha_s'")
    var done = false
    var str = ""
    var cha = cha_s.pop()
    
    while (! done){
      if (isDebug(here)) println("$here: cha '$cha'")
      str = str.plus(cha.toString())
      try {
      	  cha = cha_s.pop()
          done = cha.equals(del)

	  if (cha.equals(' ')) {
	    fatalErrorPrint ("word ends with '$del'", "a blank", "Check", here)
	  }

	  if (done) {cha_s.push(cha)}
      }
      catch (e: java.util.EmptyStackException) {
            done = true			       
      }
    }

    assert (str.isNotEmpty())
    
    if (isTrace(here)) println("$here: output str '$str'")
    if (isTrace(here)) println("$here: output cha_s '$cha_s'")
    exiting(here)
    return Pair (str, cha_s)
}

fun nextWordInBracketsOfString(str: String): String {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)
// {someword}

    if (isTrace(here)) println("$here: input str '$str'")
    
    var word = ""    
    for (c in str){
	  if (isDebug(here)) println("$here: c '$c'")
	  if (c.equals('{')) {continue}
	  if (c.equals('}')) {break}
	  word = word.plus(c.toString())
    }

    assert (word.isNotEmpty())
    
    if (isTrace(here)) println("$here: output word '$word'")
    exiting(here)
    return word
}

fun nextWordOfEndCharListOfString(cha_l: List<Char>, str: String): String {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input cha_l '$cha_l'")
    if (isTrace(here)) println("$here: input str '$str'")
    
    var word = ""    
    for (c in str){
	  if (isDebug(here)) println("$here: c '$c'")
	  if (cha_l.contains(c)) {break}
	  word = word.plus(c.toString())
    }

    assert (word.isNotEmpty())
    
    if (isTrace(here)) println("$here: output word '$word'")
    exiting(here)
    return word
}

fun nextWordOfEndCharOfString(del: Char, str: String): String {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input del '$del'")
    if (isTrace(here)) println("$here: input str '$str'")
    
    var word = ""    
    for (c in str){
	  if (isDebug(here)) println("$here: c '$c'")
	  if (c.equals(del)) {break}
	  word = word.plus(c.toString())
    }

    assert (word.isNotEmpty())
    
    if (isTrace(here)) println("$here: output word '$word'")
    exiting(here)
    return word
}

fun printOfStringArray (str_a: Array<String>) {
    val content = stringOfGlueOfStringList ("\n", str_a.toList())
    println (content)
}

fun printOfStringList (str_l: List<String>) {
    val content = stringOfGlueOfStringList ("\n", str_l)
    println (content)
}

fun reductionOfString(str: String, len: Int): String {
    val sizStr = str.length
    val result =
	if (sizStr > 3 * len) {
	    str.substring(0, len) + " ... " + str.substring(sizStr-len)
	}
        else {str}
    return result
}

fun stringOfGlueOfStringList (glue: String, str_l: List<String>) : String {
 val str = str_l.fold("", {acc, s -> acc + s + glue })
 return str.trim() 
}

fun stringListListOfDelimiterOfStringList (del: String, str_l: List<String>): List<List<String>> {
// Ex.: args=["-debug", "all", "-verbose", "all", "-trace", "all", "-loop"]
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    if(false) println("$here: input del '$del'")
    if(false) println("$here: input str_l $str_l")

    val lenDel = del.length
    var from_l: MutableList<Int> = mutableListOf()
    var to_l : List<Int>
    var i_l : List<Int>

    // from_l array of indices where command starts with del. (ex: '-')
    // to_l array of indices where command ends to (excluded)
    var ind = -1
    for (str in str_l) {
	if(false) println("$here: for str '$str'")
	ind = ind + 1
	if (str.substring(0, lenDel) == del) {
	    from_l.add (ind)
	    if(false) println("added ind '$ind'")
	}
    }

    i_l = from_l.plus (str_l.size)
    to_l = i_l.minus (0)

// Split from to
    var res_l = mutableListOf(listOf(""))

    for (i in 0 .. (from_l.size-1)) {
	val ind_from = from_l [i]
	val ind_to = to_l[i]
	val sub = str_l.subList(ind_from, ind_to)
	if(false) println("$here: for i $i from $ind_from to $ind_to sub $sub res_l $res_l")
	res_l.add(sub)
    }
    
    val result = (res_l.toList()).drop(1)
    if(false) println("$here: output result $result")

    exiting (here)
    return result
}

fun stringOfGlueOfWordStack (glue: String, str_s: Stack<String>) : String {
    val str_l = wordListOfWordStack(str_s).reversed()
    val str = stringOfGlueOfStringList (glue, str_l)
    return str.trim() 
}

fun stringOfStringList (str_l: List<String>) : String {
 val str = str_l.fold("", {acc, s -> acc + s })
 return str 
}

fun stringStackOfStringList (str_l: List<String>) : Stack<String> {
    var stack = Stack<String>()
    str_l.reversed().forEach { l -> stack.push(l) }
    return stack
}

fun wordListOfString (str: String): List<String> {
    val trimedString = str.trim(' ')    
    val regex = Regex("""\s+""")

    val result = trimedString.split(regex)

    return result
}

fun wordListOfWordStack (wor_s: Stack<String>): List<String> {
    var wor_l = mutableListOf("")
    wor_s.forEach {w -> wor_l.add (w) }
    return wor_l
}

fun wordStackOfString (lin: String) : Stack<String> {
    var stack = Stack<String>()
    var wor_l = wordListOfString (lin)
    wor_l.reversed().forEach { w -> stack.push(w)}
    return stack
}

fun wordStackOfWordList (wor_l: List<String>) : Stack<String> {
    var stack = Stack<String>()
    wor_l.reversed().forEach { w -> stack.push(w)}
    return stack
}

