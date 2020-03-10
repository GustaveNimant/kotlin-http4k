package io.ipfs.kotlin.parser

import io.ipfs.kotlin.*
import java.io.File
import java.util.Stack
import java.lang.Character.MIN_VALUE as nullChar

fun fullnameListOfLexemeList (lex_l: List<Lexeme>) : List<String> {
  val str_l = lex_l.map({l -> fullnameOfLexeme (l) })
  return str_l 
}

fun hasKeywordPreviousOfLexemeList (met_l: List<Lexeme>): Boolean {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input met_l '$met_l'")
    
    val lex = Lexeme.KeywordWithQmHash("previous")
    val result = met_l.contains(lex)

    if (isTrace(here)) println("$here: output result '$result'")
    exiting(here)
    return result
}

fun isAuthorNameOfString(str: String): Boolean {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input str '$str'")

    val pattern = Regex("""[a-zA-Z]\w*(@?)(\w*)""")
    val result = pattern.matches(str)

    if (isTrace(here)) println("$here: output result '$result'")
    exiting(here)
    return result
}

fun isDateOfString(str: String): Boolean {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)
    
    if (isTrace(here)) println("$here: input str '$str'")
    
    val pattern_us = Regex("""\d\d\/\d\d\/\d\d\d\d""")
    val result_us = pattern_us.matches(str)
    val pattern_eu = Regex("""\d\d-\d\d-\d\d\d\d""")
    val result_eu = pattern_eu.matches(str)
    val result = result_us || result_eu

    if (isTrace(here)) println("$here: output result '$result'")
    exiting(here)
    return result
}

fun isFilePathOfString(str: String): Boolean {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)
    
    if (isTrace(here)) println("$here: input str '$str'")
    val pattern = Regex("""^(/(\.)?\w[a-zA-Z_0-9]*)(/([a-zA-Z_0-9]+))*\.\w+$""")
    val result = pattern.matches(str)

    if (isTrace(here)) println("$here: output result '$result'")
    exiting(here)
    return result
}

fun isFromUserKeywordValueOfString(str: String): Boolean {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)
    
    if (isTrace(here)) println("$here: input str '$str'")
    val pattern = Regex("""\w+""")
    val result = pattern.matches(str)
    
    if (isTrace(here)) println("$here: output result '$result'")
    exiting(here)
    return result
}

fun isKeywordNameOfString(str: String): Boolean {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input str '$str'")
    val result = str.endsWith('$')

    if (isTrace(here)) println("$here: output result '$result'")
    exiting(here)
    return result
}

fun isKeywordOfString(str: String): Boolean {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input str '$str'")
    val result = str.startsWith('$') && str.endsWith(':')

    if (isTrace(here)) println("$here: output result '$result'")
    exiting(here)
    return result
}

fun isKeywordWithQmHashOfLexeme(lex: Lexeme): Boolean {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    println("$here: input lex '$lex'")
    val result = lex is Lexeme.KeywordWithQmHash

    if (isTrace(here)) println("$here: output result '$result'")
    exiting(here)
    return result
}

fun isNextNameOfString(str: String): Boolean {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)
    
    if (isTrace(here)) println("$here: input str '$str'")
    val pattern = Regex("""\w[a-zA-Z_0-9]*""")
    val result = pattern.matches(str)

    if (isTrace(here)) println("$here: output result '$result'")
    exiting(here)
    return result
}

fun isQmHashOfString(str: String): Boolean {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)
    
    val pattern = Regex("^Qm([a-zA-Z0-9]+)")
    if (isTrace(here)) println("$here: input str '$str'")
    val result = pattern.matches(str)

    if (isTrace(here)) println("$here: output result '$result'")
    exiting(here)
    return result
}

fun isSignatureOfString(str: String): Boolean {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)
    
    val pattern = Regex("(.*)")
    if (isTrace(here)) println("$here: input str '$str'")
    val result = pattern.matches(str)

    if (isTrace(here)) println("$here: output result '$result'")
    exiting(here)
    return result
}

fun isSpotOfString(str: String): Boolean {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)
    
    if (isTrace(here)) println("$here: input str '$str'")

    val pattern = Regex("""\d+""")
    val result = pattern.matches(str)

    if (isTrace(here)) println("$here: output result '$result'")
    exiting(here)
    return result
}

fun isTextVariableOfString(str: String): Boolean {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)
    
    val pattern = Regex("""\w+""")
    if (isTrace(here)) println("$here: input str '$str'")
    val result = pattern.matches(str)

    if (isTrace(here)) println("$here: output result '$result'")
    exiting(here)
    return result
}

fun isTextWordOfString(str: String): Boolean {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)
    
    val pattern = Regex("""\w+""")
    if (isTrace(here)) println("$here: input str '$str'")
    val result = pattern.matches(str)

    exiting(here + " with result '$result'")
    return result
}

fun isTicOfString(str: String): Boolean {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)
    
    if (isTrace(here)) println("$here: input str '$str'")

    val pattern = Regex("""\d+""")
    val result = pattern.matches(str)

    if (isTrace(here)) println("$here: output result '$result'")
    exiting(here)
    return result
}

fun isZ2HashOfString(str: String): Boolean {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)
    
    val pattern = Regex("^z2([a-zA-Z0-9]+)")
    if (isTrace(here)) println("$here: input str '$str'")
    val result = pattern.matches(str)

    if (isTrace(here)) println("$here: output result '$result'")
    exiting(here)
    return result
}

fun keywordNameOfString (str: String): String {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

   println("$here: str '$str'")

    assert (isKeywordNameOfString(str))
    var result = str.trimEnd({ c -> c.equals('$')})

    if (isTrace(here)) println("$here: output result '$result'")
    exiting(here)
    return result
}

fun keywordOfString(str: String): String {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

   if (isTrace(here)) println("$here: input str '$str'")

    assert (isKeywordOfString(str))
    val result = (str.substring(1)).trimEnd({c -> c.equals(':')})

    if (isTrace(here)) println("$here: output result '$result'")
    exiting(here)
    return result
}

fun lexemeListFromUserKeywordValueOfStringDollared (str: String) : List<Lexeme> {
// 'any_string'$
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    val end_cha = str.last()
    val lin = if (! end_cha.equals('$')) {
       str.plus("$")
    }
    else {
       str
    }
    if (isTrace(here)) println("$here: input lin '$lin'")

    var Done = false
    var position = 0
    var lexemeList = mutableListOf<Lexeme>()
    
    while (!Done) {
    	  try {
    	      var cha = lin.get(position)
	      if (isLoop(here)) println ("$here: while position $position cha '$cha'")
	      when (cha){
	      ' ' -> {
	      	  val lex = tokenOfChar(cha, position, lin)
		  lexemeList.add (lex)
		  position = position + 1
		  }
	      '$' -> {
	      	  val lex = tokenOfChar(cha, position, lin)
		  lexemeList.add (lex)
		  Done = true
		  }
	      in 'a' .. 'z', in 'A' .. 'Z' -> {
	      	  val sub = lin.substring(position)
		  val word = nextWordOfEndCharOfString('$', sub)
		  if (isFromUserKeywordValueOfString(word)) {
    		    val lex = Lexeme.FromUserKeywordValue(word)
		    lexemeList.add (lex)	
		    position = position + word.length 
		  }
		  else {
		       val message = "$here: Error word '$word' is not a valid FromUserKeywordValue"
		       throw Exception(message)
		  }
	          }
		  else -> {
		    val message = "$here: Error unknown character '$cha' at $position in lin '$lin'"
		       throw Exception(message)
  
		} 
   		} // when
		} // try
	  catch (e: java.lang.StringIndexOutOfBoundsException) {
	  	val cha = lin.get(position)
	  	if (cha.equals('$')) {
	     	   val lex = Lexeme.TokenEndOfLine
		   lexemeList.add (lex)
	     	   if (isLoop(here)) println("$here: setting End Of Line")
	     	   Done = true			
	     	}
		else {
		     val message = "$here: Error character '$cha' should be '$' at $position in lin '$lin'"
	    	     throw Exception(message)
		}
	  }
   }

   if (isTrace(here)) println("$here: output lexemeList "+lexemeList)
   val result = lexemeList.toList()

   exiting(here)
   return result
}

fun lexemeListMetaOfDollarString (lin: String) : Pair<List<Lexeme>, Int> {
// $keyword: specific_value
// keyword is well defined Meta

    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input lin '$lin'")
    
    val lexemeList = mutableListOf<Lexeme>()

    var Done = false
    var position = 0
    
    while (!Done) {
      try {
    	var cha = lin.get(position)	
 	when (cha) {
// Remainder	
	'A' -> {
	    val str = lin.substring(position)
	    val (lex_l, pos) = lexemeListOfAuthorLine (str)
	    lexemeList.addAll (lex_l) 
	    position = position + pos
	    Done = true
	    }
	'D' -> {
	    val str = lin.substring(position)
	    val (lex_l, pos) = lexemeListOfDateLine (str)
	    lexemeList.addAll (lex_l)
	    position = position + pos
	    Done = true
	    }
        'm' -> {
            val nextCharacter = lin.get(position + 1)
	    if (nextCharacter.equals ('e')){
	       val str = lin.substring(position)
	       val (lex_l, pos) = lexemeListOfMembersKeyword (str)
	       lexemeList.addAll (lex_l)
	       position = position + pos
	    }
	    else if (nextCharacter.equals ('u')){
	       val str = lin.substring(position)
	       val (lex_l, pos) = lexemeListOfMutableLine (str)
	       lexemeList.addAll (lex_l)
	       position = position + pos
	    }
	    else {
	    	 val message = "$here: Error next character '$nextCharacter' should be 'e' or 'u'"
	    	 throw Exception(message)
	    }
	    Done = true
	    }
        'n' -> {
	    val str = lin.substring(position)
	    val (lex_l, pos) = lexemeListOfNextLine (str)
	    lexemeList.addAll (lex_l)
	    position = position + pos
	    Done = true
	    }
	'p' -> {
	    val nextCharacter = lin.get(position + 1)
	    if (nextCharacter.equals ('a')){
	       val str = lin.substring(position)
	       val (lex_l, pos) = lexemeListOfParentsLine (str) 
	       lexemeList.addAll (lex_l)
	       position = position + pos
	    }
	    else if (nextCharacter.equals ('r')){
	       val str = lin.substring(position)
	       val (lex_l, pos) = lexemeListOfPreviousLine (str)
	       lexemeList.addAll (lex_l)
	       position = position + pos
	    }
	    else {
	    	 val message = "$here: Error next character '$nextCharacter' should be 'a' or 'r'"
	    	 throw Exception(message)
	    }
	    Done = true  
	    }
        'q' -> {
	    val str = lin.substring(position)
	    val (lex_l, pos) = lexemeListOfQmHashLine (str) 
	    lexemeList.addAll (lex_l)
	    position = position + pos
	    Done = true
	    }
        's' -> {
	    val str = lin.substring(position)
	    val (lex_l, pos) = lexemeListOfSpotLine (str) 
	    lexemeList.addAll (lex_l)
	    position = position + pos
	    Done = true
	    }
        'S' -> {
	    val nextCharacter = lin.get(position + 1)
	    if (nextCharacter.equals ('i')){
	       val str = lin.substring(position)
	       val (lex_l, pos) = lexemeListOfSignatureLine (str)
	       lexemeList.addAll (lex_l)
	       position = position + pos
	    }
	    else if (nextCharacter.equals ('o')){
	       val str = lin.substring(position)
	       val (lex_l, pos) = lexemeListOfSourceLine (str)
	       lexemeList.addAll(lex_l)
	       position = position + pos
	    }
	    else {
	    	 val message = "$here: Error next character '$nextCharacter' should be 'i' or 'o'"
	    	 throw Exception(message)
	    }
	    Done = true  
	    }
        't' -> {
	    val str = lin.substring(position)
	    val (lex_l, pos) = lexemeListOfTicLine (str)
	    lexemeList.addAll (lex_l)
	    position = position + pos
	    Done = true
	    }
	else -> {
	    	 val message = "$here: Error unexpected current character '$cha' at position $position"
	    	 throw Exception(message)
	    }
	    } // when
      } // try
      catch (e: java.lang.StringIndexOutOfBoundsException) {
	val cha = lin.get(position-1)
	if (cha.equals('$')) {
	  val lex = Lexeme.TokenEndOfLine
          lexemeList.add (lex)
	  if (isLoop(here)) println("$here: setting End Of Line")
	    Done = true			
	  }
	  else {
	    val message = "$here: Error previous character '$cha' (position $position) should be '$'"
	    throw Exception(message)
	  }
       } // catch
   } // while
   
   if (isTrace(here)) println("$here: output position $position")
   if (isTrace(here)) println("$here: output lexemeList: "+ fullnameListOfLexemeList(lexemeList))

   val result = Pair (lexemeList.toList(), position)
   exiting(here)
   return result

}

fun lexemeListMetaValueOfStringDollared (lin: String) : List<Lexeme> {
// $KeywordMeta: MetaValue$
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input lin '$lin'")
    
    val lexemeList = mutableListOf<Lexeme>()

    var Done = false
    var position = 0
    
    while (!Done) {
    	  try {
    	      var cha = lin.get(position)
	      when (cha){
	      ' ' -> {
	      	  val lex = tokenOfChar(cha, position, lin)
		  lexemeList.add(lex)
		  position = position + 1
		  }
	      '$' -> {
	      	  val lex = tokenOfChar(cha, position, lin)
		  lexemeList.add(lex)
		  position = position + 1
		  }
	      else -> {
	      	  val str = lin.substring(position)
		  val word = nextWordOfEndCharOfString('$', str)
		  if (isSpotOfString(word)) {
		     position = position + word.length 
    		      val lex = Lexeme.Spot (word)
		      lexemeList.add(lex)
		  }
		  else {
		       val message = "$here: Error word '$word' is not a valid Qm Hash"
		       throw Exception(message)
		  }
	          }
		}
   		}
	  catch (e: java.lang.StringIndexOutOfBoundsException) {
	  	val cha = lin.get(position-1)
	  	if (cha.equals('$')) {
	     	   val lex = Lexeme.TokenEndOfLine
		   lexemeList.add (lex)
	     	   if (isLoop(here)) println("$here: setting End Of Line")
	     	   Done = true			
	     	}
		else {
		     val message = "$here: Error previous character '$cha' should be '$'"
	    	     throw Exception(message)
		}
	  }
   }

   if (isTrace(here)) println("$here: output lexemeList "+lexemeList)

   val result = lexemeList.toList()

   exiting(here)
   return result 
}

fun lexemeListOfAuthorLine (lin: String) : Pair<List<Lexeme>, Int> {
// # $Author: michel$'
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input lin '$lin'")
    
    val lexemeList = mutableListOf<Lexeme>()

    var Done = false
    var position = 0
    
    while (!Done) {
    	  try {
    	      var cha = lin.get(position)
	      if (isLoop(here)) println ("$here: while position $position cha '$cha'")
	      when (cha){
	      '$', ':', ' ' -> {
	      	  val lex = tokenOfChar(cha, position, lin)
		  lexemeList.add (lex)
		  position = position + 1
		  }
	      'A' -> {
	      	  val str = lin.substring(position)
    	      	  val word = nextWordOfEndCharOfString(':', str)
    	  	  val lex = lexemeOfWord (word)
		  lexemeList.add (lex)
	  	  position = position + word.length
		  }
	      in 'a'..'z', in 'A' .. 'Z' -> {
	      	  val str = lin.substring(position)
		  val word = nextWordOfEndCharOfString('$', str)
		  if (isAuthorNameOfString(word)) {
    		     val lex = Lexeme.AuthorName (word)
		     lexemeList.add (lex)
		     position = position + word.length 
		  }
		  else {
		       val message = "$here: Error word '$word' is not a valid Author Name"
		       throw Exception(message)
		  }
	          }
	       else -> {
		     val message = "$here: Error unknown current Character '$cha' at position $position"
		     throw Exception(message)
	          }
		}
   		}
	  catch (e: java.lang.StringIndexOutOfBoundsException) {
	  	val cha = lin.get(position-1)
	  	if (cha.equals('$')) {
	     	   val lex = Lexeme.TokenEndOfLine
		   lexemeList.add (lex)	
	     	   if (isLoop(here)) println("$here: setting End Of Line")
	     	   Done = true			
	     	}
		else {
		     val message = "$here: Error previous character '$cha' should be '$'"
	    	     throw Exception(message)
		}
	  }
   }

   if (isTrace(here)) println("$here: output position $position")
   if (isTrace(here)) println("$here: output lexemeList: "+ fullnameListOfLexemeList(lexemeList))
   val result = Pair(lexemeList.toList(), position)

   exiting(here)
   return result
}

fun lexemeListOfDateLine (lin: String) : Pair<List<Lexeme>, Int> {
// # $Date: 27-05-2015$
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input lin '$lin'")
    
    val lexemeList = mutableListOf<Lexeme>()

    var Done = false
    var position = 0
    
    while (!Done) {
    	  try {
    	      var cha = lin.get(position)
	      if (isLoop(here)) println ("$here: while position $position cha '$cha'")
	      when (cha) {
	      ' ', ':', '$' -> {
	          val lex = tokenOfChar(cha, position, lin)
		  lexemeList.add (lex)
		  position = position + 1
		  }
	      'D' -> {
	      	  val str = lin
    	      	  val word = nextWordOfEndCharOfString(':', str)
    	  	  val lex = lexemeOfWord (word)
		  lexemeList.add (lex)
		  position = position + word.length 
		  }
	      in '0' .. '9' -> {
	      	  val str = lin.substring(position)
		  val cha_l = listOf(' ','$')
		  val word = nextWordOfEndCharListOfString(cha_l, str)
		  if (isDateOfString(word)) {
    		     val lex = Lexeme.DateValue (word)
		     lexemeList.add (lex)
		     position = position + word.length
		  }
		  else {
		    val message = "$here: Error word '$word' is not a valid date"
		    throw Exception(message)
		  }
		  }
	      'n' -> {
	      	  val str = lin.substring(position)
		  val word = nextWordOfEndCharOfString('$', str)
		  if (isDateOfString(word)) {
    		     val lex = Lexeme.DateValue (word)
		     lexemeList.add (lex)
		     position = position + word.length
		  }
		  else {
		    val message = "$here: Error word '$word' is not a valid date"
		    throw Exception(message)
		  }
	          }
	       else -> {
		    val message = "$here: Error unknown Character '$cha' at position $position of line '$lin'"
		    throw Exception(message)
	          }
		}
   		}
	  catch (e: java.lang.StringIndexOutOfBoundsException) {
	  	val cha = lin.get(position-1)
	  	if (cha.equals('$')) {
	     	  val lex = Lexeme.TokenEndOfLine
		  lexemeList.add (lex)
	     	  if (isLoop(here)) println("$here: setting End Of Line")
	     	  Done = true			
	     	}
		else {
		  val message = "$here: Error previous character '$cha' should be '$'"
	    	  throw Exception(message)
		}
	  }
   }

   if (isTrace(here)) println("$here: output position $position")
   if (isTrace(here)) println("$here: output lexemeList: "+ fullnameListOfLexemeList(lexemeList))
   val result = Pair(lexemeList.toList(), position)

   exiting(here)
   return result
}

fun lexemeListOfDollarStringDollar (dol_dol: String): Pair<List<Lexeme>, Int> {
/*
 $any test$
*/
  
  val (here, caller) = moduleHereAndCaller()
  entering(here, caller)

  if (isTrace(here)) println("$here: input dol_dol '$dol_dol'")

// output :
  var lexemeList = mutableListOf<Lexeme>()

  if (dol_dol.isNullOrBlank()){
    val message = "$here: Error string dol_dol is Null ot Blank"
       throw Exception(message)
  }

  if (dol_dol.contains(':')){
    val message = "$here: Error string dol_dol contains ':'"
       throw Exception(message)
  }

  val lex = Lexeme.TextWordConstant(dol_dol)
  lexemeList.add(lex)
  val position = dol_dol.length
  
  if (isTrace(here)) println("$here: output position $position")
  if (isTrace(here)) println("$here: output lexemeList: "+ fullnameListOfLexemeList(lexemeList))

  val result = Pair(lexemeList.toList(), position)
  
  exiting(here)
  return result
}

fun lexemeListOfFileName(fil_nam: String) : List<Lexeme> {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    val ext = fileExtensionOfFilePath(fil_nam)
    val lex_l =
      when (ext) {
      "yml" -> lexemeListOfYmlFile (fil_nam)
     else -> {
       fatalErrorPrint ("file extension is 'yml'", "file name '$fil_nam'", "Check", here)
       }
     }
    if (isTrace(here)) if (isTrace(here)) println("$here: output lexeme List '$lex_l'")
    exiting(here)
    return lex_l
}

fun lexemeListOfMembersKeyword (lin: String) : Pair<List<Lexeme>, Int> {
// $members: michelc@lausanne , emilea$ 
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input lin '$lin'")
    
    val lexemeList = mutableListOf<Lexeme>()

    var Done = false
    var position = 0
    
    while (!Done) {
    	  try {
    	      var cha = lin.get(position)
	      if (isLoop(here)) println ("$here: while position $position cha '$cha'")
	      when (cha){
	      ' ', '$' -> {
	      	 val lex = tokenOfChar(cha, position, lin)
		 lexemeList.add (lex)
		 position = position + 1
		  }
	      'm' -> {
	      	  var str = lin.substring(position)
    	      	  val word = nextWordOfEndCharOfString(':', str)
		  if (word != "members") {
		     fatalErrorPrint ("word were 'members'", word, "Check", here)
		  }

	  	  position = position + word.length + 1
    	  	  val lex = lexemeOfWord (word)
		  lexemeList.add (lex)

	      	  str = (lin.substring(position)).dropLastWhile({c -> c.equals(' ')})
		  val (lex_l, pos) = lexemeListOfMembersRemainderString (str)
		  lexemeList.addAll (lex_l)
		  position = position + pos
		  Done = true
		  }		  
	       else -> {
		  val message = "$here: Error unknown current Character '$cha' at position $position of line '$lin'"
		  throw Exception(message)
	          }
		}
   		}
	  catch (e: java.lang.StringIndexOutOfBoundsException) {
	    val message = "$here: Error unexpected End Of Line at position $position of line '$lin'"
	    throw Exception(message)
	  
	  }
   }

   if (isTrace(here)) println("$here: output position $position")
   if (isTrace(here)) println("$here: output lexemeList: "+ fullnameListOfLexemeList(lexemeList))
   val result = Pair(lexemeList.toList(), position)

   exiting(here)
   return result

}

fun lexemeListOfMembersRemainderString (str: String) : Pair<List<Lexeme>, Int> {
// " emilea@paris, michelc@lausanne$ "
// " jules, alain@champagne$ "
// " michelc@lausanne$ "
// " louis$ "
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input str '$str'")

// output
    val lexemeList = mutableListOf<Lexeme>()

// clean space at end of line
    
    val is_array = str.contains(',')
    println("$here: is_array '$is_array'")
    
    var Done = false
    var position = 0
    
    while (!Done) {
    	  try {
    	      var cha = str.get(position)
	      println("$here: at position $position cha '$cha'")
	      when (cha) {
	      ' ' -> {
	      	 val lex = tokenOfChar(cha, position, str)
		 position = position + 1
		 lexemeList.add (lex)
		  }
	      '$' -> {
	      	 val lex = tokenOfChar(cha, position, str)
		 position = position + 1
		 lexemeList.add (lex)
		  }
	      ',' -> {
	      	 val lex = tokenOfChar(cha, position, str)
		 position = position + 1
		 lexemeList.add (lex)
		  }
	      '@' -> {
	      	 val lex = tokenOfChar(cha, position, str)
		 position = position + 1
		 lexemeList.add (lex)
	          }
	       else -> {
	       	 val sub = str.substring(position)
		 println("$here: at position $position sub '$sub'")
		 val cha_l = listOf(' ',',','$')
	         val word = nextWordOfEndCharListOfString(cha_l, sub)
		 if (isAuthorNameOfString(word)) {
		     position = position + word.length 
    		     val lex = Lexeme.AuthorName (word)
		     lexemeList.add (lex)
		  }
		  else {
		       val message = "$here: Error word '$word' is not a valid Author Name"
		       throw Exception(message)
		  }
		}
              }
          }
        catch (e: java.lang.StringIndexOutOfBoundsException) {
	  val cha = str.get(position-1)
	  val cha_l = listOf('$', ' ', '\'', '"')
	  if (cha_l.contains(cha)) { 
	    val lex = Lexeme.TokenEndOfLine
            lexemeList.add (lex)
	    if (isLoop(here)) println("$here: setting End Of Line")
	      Done = true			
	   }
	  else {
	    fatalErrorPrint("last character '$cha' (position $position) should in list '$cha_l'", "string '$str'", "Correct record", here)
	  }
         } // catch
   }

   if (isTrace(here)) println("$here: output position $position")
   if (isTrace(here)) println("$here: output lexemeList: "+ fullnameListOfLexemeList(lexemeList))
   val result = Pair(lexemeList.toList(), position)

   exiting(here)
   return result
}

fun lexemeListOfMutableLine (lin: String) : Pair<List<Lexeme>, Int> {
// mutable: /.brings/system/bin/kwextract.pl$
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input lin '$lin'")
    
    val lexemeList = mutableListOf<Lexeme>()

    var Done = false
    var position = 0
    
    while (!Done) {
    	  try {
    	      var cha = lin.get(position)
	      if (isLoop(here)) println ("$here: while position $position cha '$cha'")
	      when (cha){
	      ' ', '$', ':' -> {
	      	  val lex = tokenOfChar(cha, position, lin)
		  lexemeList.add (lex)
		  position = position + 1
		  }
	      'm' -> {
	      	  val str = lin.substring(position)
    	      	  val word = nextWordOfEndCharOfString(':', str)
    	  	  val lex = lexemeOfWord (word)
		  lexemeList.add (lex)
	  	  position = position + word.length
		  }
	      '/' -> {
	      	  val str = lin.substring(position)
		  val word = nextWordOfEndCharOfString('$', str)
		  if (isFilePathOfString(word)) {
		     position = position + word.length 
    		     val lex = Lexeme.FilePath (word)
		     lexemeList.add (lex)
		  }
		  else {
		       val message = "$here: Error word '$word' is not a valid File Path"
		       throw Exception(message)
		  }
	          }
	       else -> {
		     val message = "$here: Error unknown current Character '$cha' at position $position of lin'$lin'"
		     throw Exception(message)
	          }
		} // when
   		} // try
	  catch (e: java.lang.StringIndexOutOfBoundsException) {
	  	val cha = lin.get(position-1)
	  	if (cha.equals('$')) {
	     	   val lex = Lexeme.TokenEndOfLine
		   lexemeList.add (lex)
	     	   if (isLoop(here)) println("$here: setting End Of Line")
	     	   Done = true			
	     	}
		else {
		     val message = "$here: Error previous character '$cha' should be '$'"
	    	     throw Exception(message)
		}
	  }
   }

   if (isTrace(here)) println("$here: output position $position")
   if (isTrace(here)) println("$here: output lexemeList: "+ fullnameListOfLexemeList(lexemeList))
   val result = Pair(lexemeList.toList(), position)

   exiting(here)
   return result
}

fun lexemeListOfNextLine (lin: String) : Pair<List<Lexeme>, Int> {
// # $next: unknown$'
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input lin '$lin'")
    
    val lexemeList = mutableListOf<Lexeme>()

    var Done = false
    var position = 0
    
    while (!Done) {
    	  try {
    	      var cha = lin.get(position)
	      if (isLoop(here)) println ("$here: while position $position cha '$cha'")
	      when (cha){
	      '$', ':' -> {
	      	  val lex = tokenOfChar(cha, position, lin)
		  lexemeList.add (lex)
		  position = position + 1
		  }
	      'n' -> {
	      	  val str = lin.substring(position)
    	      	  val word = nextWordOfEndCharOfString(':', str)
    	  	  val lex = lexemeOfWord (word)
		  lexemeList.add (lex)
	  	  position = position + word.length
		  }
	      ' ' -> {
	      	  val lex = tokenOfChar(cha, position, lin)
		  lexemeList.add (lex)
		  position = position + 1
	      }
              in 'a' .. 'z', in 'A' .. 'Z' -> {
	      	  val str = lin.substring(position)
		  val word = nextWordOfEndCharOfString('$', str)
		  if (isNextNameOfString(word)) {
    		     val lex = Lexeme.NextName (word)
		     lexemeList.add (lex)
		     position = position + word.length 
		     }		     
		  else {
		       val message = "$here: Error word '$word' is not a valid Next Name"
		       throw Exception(message)
		  }
	          }
	       else -> {
		     val message = "$here: Error unknown current Character '$cha' at position $position of lin '$lin'"
		     throw Exception(message)
	          }
		}
   		}
	  catch (e: java.lang.StringIndexOutOfBoundsException) {
	  	val cha = lin.get(position-1)
	  	if (cha.equals('$')) {
	     	   val lex = Lexeme.TokenEndOfLine
		   lexemeList.add (lex)		
	     	   if (isLoop(here)) println("$here: setting End Of Line")
	     	   Done = true			
	     	}
		else {
		     val message = "$here: Error previous character '$cha' should be '$'"
	    	     throw Exception(message)
		}
	  }
   }
   
   if (isTrace(here)) println("$here: output position $position")
   if (isTrace(here)) println("$here: output lexemeList: "+ fullnameListOfLexemeList(lexemeList))
   val result = Pair(lexemeList.toList(), position)

   exiting(here)
   return result
}

fun lexemeListOfNonSharpedLine (lin: String) : List<Lexeme> {
// any text 
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input lin '$lin'")
    
    val lexemeList = mutableListOf<Lexeme>()

    var Done = false
    var position = 0
    
    while (!Done) {
      try {
    	var cha = lin.get(position)
	if (isLoop(here)) println ("$here: while position $position cha '$cha'")
 	when (cha) {
	' ', ':' -> {
	    val lexeme = tokenOfChar(cha, position, lin)
	    lexemeList.add(lexeme)
	    position = position + 1	 
	}
	in 'a' .. 'z', in 'A' .. 'Z', in '0' .. '9' -> {
	    val str = lin.substring(position)
	    if (isLoop(here)) println("$here: when str '$str'")
	    val cha_l = listOf(' ','$')
	    val word = nextWordOfEndCharListOfString(cha_l, str)
	    val lex = Lexeme.TextWordConstant(word)
	    if (isLoop(here)) println("$here: when lex '$lex'")
	    lexemeList.add(lex)
	    position = position + word.length

	}
	'{' -> {
	    val str = lin.substring(position)
	    val word = nextWordInBracketsOfString (str)
            val lex = Lexeme.TextVariableSubstituable(word)
            val lex_l = listOf(Lexeme.TokenLeftCurvedBracket, Lexeme.TokenLeftCurvedBracket, lex, Lexeme.TokenRightCurvedBracket, Lexeme.TokenRightCurvedBracket)
	    lexemeList.addAll(lex_l)
	    position = position + word.length + 4
	}
	'$' -> {
	    val lexeme = tokenOfChar(cha, position, lin)
	    lexemeList.add(lexeme)
	    position = position + 1
	    
	    val str = lin.substring(position)
	    if (isLoop(here)) println("$here: when dollar str '$str'")

/*
somekeyword: somevalue$  '
*/
            val cha_l = listOf(' ', ':')
	    val word = nextWordOfEndCharListOfString (cha_l, str)
	    if (isMetaKeywordOfString(word)) {
	       val (lex_l, pos) = lexemeListMetaOfDollarString (str)
	       lexemeList.addAll(lex_l)
	       position = position + pos
	    }
	    else {
	       val (lex_l, pos) = lexemeListOfTextString(word)
	       lexemeList.addAll(lex_l)
	       position = position + pos
	    }
	}
	else -> {
	  val chr = lin.get(position)
	  val message = "$here: Error unexpected character '$chr' at position $position of lin '$lin'"
	  throw Exception(message)
	    }

        } // when
      } // try
      catch (e: java.lang.StringIndexOutOfBoundsException) {

      	val (cha, pos) =
      	    if (lin.isNullOrBlank()) {
	       Pair (nullChar, 0)
    	    }
	    else {
      	       val pos = position-1 
               val cha = lin.get(pos)
	       Pair (cha, pos)
	    }
      	      if (isLoop(here)) println ("$here: while Done at previous position $pos with cha '$cha' in lin '$lin'")

	    val lex = Lexeme.TokenEndOfLine
	    lexemeList.add(lex)
	    if (isLoop(here)) println ("$here: EndOfLine added")
      	    Done = true			
       } // catch
   } // while

   if (isTrace(here)) println("$here: output position $position")
   if (isTrace(here)) println("$here: output lexemeList: "+ fullnameListOfLexemeList(lexemeList))
   val result = lexemeList.toList()

   exiting(here)
   return result
}

fun lexemeListOfParentsLine (lin: String) : Pair<List<Lexeme>, Int> {
// parents: QmU1RDLsAGNPVuwDjKD3RQx7R6aEuQfcmSiubviDZ2XRVC$
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input lin '$lin'")
    
    val lexemeList = mutableListOf<Lexeme>()

    var Done = false
    var position = 0
    
    while (!Done) {
    	  try {
    	      var cha = lin.get(position)
	       if (isLoop(here)) println ("$here: while position $position cha '$cha'")
	      when (cha){
	      ' ', '$', ':' -> {
	      	  val lex = tokenOfChar(cha, position, lin)
		  lexemeList.add (lex)
		  position = position + 1
		  }
	      'p' -> {
	      	  val str = lin.substring(position)
    	      	  val word = nextWordOfEndCharOfString(':', str)
    	  	  val lex = lexemeOfWord (word)
		  lexemeList.add (lex)
	  	  position = position + word.length
		  }
	      'Q' -> {
	      	  val str = lin.substring(position)
		  val word = nextWordOfEndCharOfString('$', str)
		  if (isQmHashOfString(word)) {
    		     val lex = Lexeme.QmHash (word)
		     lexemeList.add (lex)	
		     position = position + word.length 
	  	  }
		  else {
		       val message = "$here: Error word '$word' is not a valid Qm Hash"
		       throw Exception(message)
		  }
	          }
	       else -> {
		     val message = "$here: Error unknown current Character '$cha' at position $position of lin '$lin'"
		     throw Exception(message)
	          }
		}
   		}
	  catch (e: java.lang.StringIndexOutOfBoundsException) {
	  	val cha = lin.get(position-1)
	  	if (cha.equals('$')) {
	     	   val lex = Lexeme.TokenEndOfLine
		   lexemeList.add (lex)
	     	   if (isLoop(here)) println("$here: setting End Of Line")
	     	   Done = true			
	     	}
		else {
		     val message = "$here: Error previous character '$cha' should be '$'"
	    	     throw Exception(message)
		}
	  }
   }
   
   if (isTrace(here)) println("$here: output position $position")
   if (isTrace(here)) println("$here: output lexemeList: "+ fullnameListOfLexemeList(lexemeList))
   val result = Pair(lexemeList.toList(), position)

   exiting(here)
   return result
}

fun lexemeListOfPreviousLine (lin: String) : Pair<List<Lexeme>, Int> {
// previous: QmU1RDLsAGNPVuwDjKD3RQx7R6aEuQfcmSiubviDZ2XRVC$
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input lin '$lin'")
    
    val lexemeList = mutableListOf<Lexeme>()

    var Done = false
    var position = 0
    
    while (!Done) {
    	  try {
    	      var cha = lin.get(position)
	       if (isLoop(here)) println ("$here: while position $position cha '$cha'")
	      when (cha){
	        ' ', ':', '$' -> {
	      	  val lex = tokenOfChar(cha, position, lin)
		  lexemeList.add (lex)
		  position = position + 1
		  }
	      'p' -> {
	      	  val str = lin.substring(position)
    	      	  val word = nextWordOfEndCharOfString(':', str)
    	  	  val lex = lexemeOfWord (word)
		  lexemeList.add (lex)
	  	  position = position + word.length
		  }
	      'Q' -> {
	      	  val str = lin.substring(position)
		  val word = nextWordOfEndCharOfString('$', str)
		  if (isQmHashOfString(word)) {
		     position = position + word.length 
    		     val lex = Lexeme.QmHash (word)
		     lexemeList.add (lex)
		  }
		  else {
		       val message = "$here: Error word '$word' is not a valid Qm Hash"
		       throw Exception(message)
		  }
	          }
	       else -> {
		     val message = "$here: Error unknown current Character '$cha' at position $position of lin '$lin'"
		     throw Exception(message)
	          }
		}
   		}
	  catch (e: java.lang.StringIndexOutOfBoundsException) {
	  	val cha = lin.get(position-1)
	  	if (cha.equals('$')) {
	     	   val lex = Lexeme.TokenEndOfLine
		   lexemeList.add (lex)
	     	   if (isLoop(here)) println("$here: setting End Of Line")
	     	   Done = true			
	     	}
		else {
		     val message = "$here: Error previous character '$cha' should be '$'"
	    	     throw Exception(message)
		}
	  }
   }

   if (isTrace(here)) println("$here: output position $position")
   if (isTrace(here)) println("$here: output lexemeList: "+ fullnameListOfLexemeList(lexemeList))
   val result = Pair(lexemeList.toList(), position)

   exiting(here)
   return result
}

fun lexemeListOfQmHashLine (lin: String) : Pair<List<Lexeme>, Int> {
// qm: z2U1RDLsAGNPVuwDjKD3RQx7R6aEuQfcmSiubviDZ2XRVC$
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input lin '$lin'")
    
    val lexemeList = mutableListOf<Lexeme>()

    var Done = false
    var position = 0
    
    while (!Done) {
    	  try {
    	      var cha = lin.get(position)
	      if (isLoop(here)) println ("$here: while position $position cha '$cha'")
	      when (cha){
	      ' ', '$', ':' -> {
	      	  val lex = tokenOfChar(cha, position, lin)
		  lexemeList.add (lex)
		  position = position + 1
		  }
	      'q' -> {
	      	  val str = lin.substring(position)
    	      	  val word = nextWordOfEndCharOfString(':', str)
    	  	  val lex = lexemeOfWord (word)
		  lexemeList.add (lex)
	  	  position = position + word.length
		  }
	      'z' -> {
	      	  val str = lin.substring(position)
		  val word = nextWordOfEndCharOfString('$', str)
		  if (isZ2HashOfString(word)) {
    		     val lex = Lexeme.Z2Hash (word)
		     lexemeList.add (lex)
		     position = position + word.length 
		  }
		  else {
		       val message = "$here: Error word '$word' is not a valid z2 Hash"
		       throw Exception(message)
		  }
	          }
	       else -> {
		     val message = "$here: Error unknown current Character '$cha' at position $position of lin '$lin'"
		     throw Exception(message)
	          }
		}
   		}
	  catch (e: java.lang.StringIndexOutOfBoundsException) {
	  	val cha = lin.get(position-1)
	  	if (cha.equals('$')) {
	     	   val lex = Lexeme.TokenEndOfLine
             	   lexemeList.add (lex)
	     	   if (isLoop(here)) println("$here: setting End Of Line")
	     	   Done = true			
	     	}
		else {
		     val message = "$here: Error previous character '$cha' should be '$'"
	    	     throw Exception(message)
		}
	  }
   }

   if (isTrace(here)) println("$here: output position $position")
   if (isTrace(here)) println("$here: output lexemeList: "+ fullnameListOfLexemeList(lexemeList))
   val result = Pair(lexemeList.toList(), position)

   exiting(here)
   return result
}

fun lexemeListOfSharpedLine (lin: String) : List<Lexeme> {
// Source: /my/perl/script/kwextract.pl,v$
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input lin '$lin'")
    
    val lexemeList = mutableListOf<Lexeme>()

    var Done = false
    var position = 0
    
    while (!Done) {
      try {
    	var cha = lin.get(position)
	if (isLoop(here)) println ("$here: while position $position cha '$cha'")
 	when (cha) {
// Header 
	'#' -> {
	    if (lin.length > 1) {
	       val lexeme = tokenOfChar(cha, position, lin)
	       lexemeList.add(lexeme)
	       position = position + 1
	    }
	    else {
	       Done = true
	       Lexeme.TokenEmptySharpedLine
	    }
	}
	' ' -> {
	    val lexeme = tokenOfChar(cha, position, lin)
	    lexemeList.add(lexeme)
	    position = position + 1	 
	}
	'$' -> {
	    val lexeme = tokenOfChar(cha, position, lin)
	    lexemeList.add(lexeme)
	    position = position + 1
	    
	    val str = lin.substring(position)
	    if (isLoop(here)) println("$here: while str '$str'")
	    
	    val (lex_l, pos) = lexemeListMetaOfDollarString (str)
	    position = position + pos
	    lexemeList.addAll(lex_l)
	}
	else -> {
	  val message = "$here: Error unexpected current character '$cha' at position $position of lin '$lin'"
	  throw Exception(message)
	    }

        } // when
      } // try
      catch (e: java.lang.StringIndexOutOfBoundsException) {
	val cha = lin.get(position-1)
	if (! cha.equals('$')) {
	val cha_l = listOf(' ', '\'', '"')
	if (cha_l.contains(cha)) { 
	  val lex = Lexeme.TokenEndOfLine
          lexemeList.add (lex)
	  if (isLoop(here)) println("$here: setting End Of Line")
	  }
	  else {
	    fatalErrorPrint("last character '$cha' (position $position) should in list '$cha_l'", "string '$lin'", "Correct record", here)
	  }
	  }
	    Done = true			
       } // catch
   } // while

   if (isTrace(here)) println("$here: output position $position")
   if (isTrace(here)) println("$here: output lexemeList: "+ fullnameListOfLexemeList(lexemeList))
   val result = lexemeList.toList()

   exiting(here)
   return result
}

fun lexemeListOfSignatureLine (lin: String) : Pair<List<Lexeme>, Int> {
// # $signature: n/a$'
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input lin '$lin'")
    
    val lexemeList = mutableListOf<Lexeme>()

    var Done = false
    var position = 0
    
    while (!Done) {
    	  try {
    	      var cha = lin.get(position)
	      if (isLoop(here)) println ("$here: while position $position cha '$cha'")
	      when (cha){
	      ' ', '$' -> {
	      	  val lex = tokenOfChar(cha, position, lin)
		  lexemeList.add (lex)
		  position = position + 1
		  }
	      'S' -> {
	      	  val str = lin.substring(position)
    	      	  val word = nextWordOfEndCharOfString(':', str)
	  	  position = position + word.length
    	  	  lexemeOfWord (word)
		  }
	      else -> {
	      	  val str = lin.substring(position)
		  val word = nextWordOfEndCharOfString('$', str)
		  if (isSignatureOfString(word)) {
		     position = position + word.length 
    		     Lexeme.Signature (word)
		  }
		  else {
		       val message = "$here: Error word '$word' is not a valid Spot field"
		       throw Exception(message)
		  }
	          }
		} // when
   		} // try
	  catch (e: java.lang.StringIndexOutOfBoundsException) {
	  	val cha = lin.get(position-1)
	  	if (cha.equals('$')) {
	     	   val lex = Lexeme.TokenEndOfLine
             	   lexemeList.add (lex)
	     	   if (isLoop(here)) println("$here: setting End Of Line")
	     	   Done = true			
	     	}
		else {
		     val message = "$here: Error previous character '$cha' should be '$'"
	    	     throw Exception(message)
		}
	  }
   }

   if (isTrace(here)) println("$here: output position $position")
   if (isTrace(here)) println("$here: output lexemeList: "+ fullnameListOfLexemeList(lexemeList))
   val result = Pair(lexemeList.toList(), position)

   exiting(here)
   return result
}

fun lexemeListOfSourceLine (lin: String) : Pair<List<Lexeme>, Int> {
// Source: /my/perl/script/kwextract.pl,v$
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input lin '$lin'")
    
    val lexemeList = mutableListOf<Lexeme>()

    var Done = false
    var position = 0
    
    while (!Done) {
    	  try {
    	      var cha = lin.get(position)
	      if (isLoop(here)) println ("$here: while position $position cha '$cha'")
	      when (cha){
	      ' ', 'v', ',', ':', '$' -> {
	      	  val lex = tokenOfChar(cha, position, lin)
		  lexemeList.add (lex)
		  position = position + 1
		  }
	      'S' -> {
	      	  val str = lin.substring(position)
    	      	  val word = nextWordOfEndCharOfString(':', str)
    	  	  val lex = lexemeOfWord (word)
		  lexemeList.add (lex)
	  	  position = position + word.length
		  }
	      '/' -> {
	      	  val str = lin.substring(position)
		  val word = nextWordOfEndCharOfString(',', str)
		  if (isFilePathOfString(word)) {
    		     val lex = Lexeme.FilePath (word)
		     lexemeList.add (lex)
		     position = position + word.length 
		  }
		  else {
		       val message = "$here: Error word '$word' is not a valid File Path"
		       throw Exception(message)
		  }
	          }
	       else -> {
		     val message = "$here: Error unknown current Character '$cha' at position $position of lin '$lin'"
		     throw Exception(message)
	          }
		}
   		}
	  catch (e: java.lang.StringIndexOutOfBoundsException) {
	  	val cha = lin.get(position-1)
	  	if (cha.equals('$')) {
	     	   val lex = Lexeme.TokenEndOfLine
             	   lexemeList.add (lex)
	     	   if (isLoop(here)) println("$here: setting End Of Line")
	     	   Done = true			
	     	}
		else {
		     val message = "$here: Error previous character '$cha' should be '$'"
	    	     throw Exception(message)
		}
	  }
   }

   if (isTrace(here)) println("$here: output position $position")
   if (isTrace(here)) println("$here: output lexemeList: "+ fullnameListOfLexemeList(lexemeList))
   val result = Pair(lexemeList.toList(), position)

   exiting(here)
   return result
}

fun lexemeListOfSpotLine (lin: String) : Pair<List<Lexeme>, Int> {
// # $spot: 1579373044$'
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input lin '$lin'")
    
    val lexemeList = mutableListOf<Lexeme>()

    var Done = false
    var position = 0
    
    while (!Done) {
    	  try {
    	      var cha = lin.get(position)
	      if (isLoop(here)) println ("$here: while position $position cha '$cha'")
	      when (cha){
	      '$', ':' -> {
	      	  val lex = tokenOfChar(cha, position, lin)
		  lexemeList.add (lex)
		  position = position + 1
		  }
	      's' -> {
	      	  val str = lin.substring(position)
    	      	  val word = nextWordOfEndCharOfString(':', str)
    	  	  val lex = lexemeOfWord (word)
		  lexemeList.add (lex)
	  	  position = position + word.length
		  }
	      ' ' -> {
	      	  tokenOfChar(cha, position, lin)
		  position = position + 1
	      }
              in '0' .. '9' -> {
		  
	      	  val str = lin.substring(position)
		  val word = nextWordOfEndCharOfString('$', str)
		  if (isSpotOfString(word)) {
    		     val lex = Lexeme.Spot (word)
		     lexemeList.add (lex)
		     position = position + word.length 
		     }
		  else {
		       val message = "$here: Error word '$word' is not a valid Spot field"
		       throw Exception(message)
		       }
	           }    
	       else -> {
		     val message = "$here: Error unknown current Character '$cha' at position $position of lin '$lin'"
		     throw Exception(message)
	          }
		}
   		}
	  catch (e: java.lang.StringIndexOutOfBoundsException) {
	  	val cha = lin.get(position-1)
	  	if (cha.equals('$')) {
	     	   val lex = Lexeme.TokenEndOfLine
             	   lexemeList.add (lex)
	     	   if (isLoop(here)) println("$here: setting End Of Line")
	     	   Done = true			
	     	}
		else {
		     val message = "$here: Error at end of line previous character '$cha' should be '$'"
	    	     throw Exception(message)
		}
	  }
   }
   if (isTrace(here)) println("$here: output position $position")
   if (isTrace(here)) println("$here: output lexemeList: "+ fullnameListOfLexemeList(lexemeList))
   val result = Pair(lexemeList.toList(), position)

   exiting(here)
   return result
}

fun lexemeListOfTextString (str: String) : Pair<List<Lexeme>, Int> {
/*
 variable 
*/
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input str '$str'")
    
    val lexemeList = mutableListOf<Lexeme>()

    var Done = false
    var position = 0
    
    while (!Done) {
    	  try {
    	      var cha = str.get(position)
	      if (isLoop(here)) println ("$here: while position $position cha '$cha'")
	      when (cha) {
	      '$' -> {
	      	  if (position == 0) {
		     fatalErrorPrint("'$' not starting string", "'$str'", "Check", here)
		  }
	      	  val lex = tokenOfChar(cha, position, str)
		  lexemeList.add (lex)
		  position = position + 1
		  Done = true
		  if (isLoop(here)) println ("$here: while position $position Done true")
		  }
	      ' ' -> {
	      	  val lex = tokenOfChar(cha, position, str)
		  lexemeList.add (lex)
		  position = position + 1
		  }
	      in 'a' .. 'z', in 'A' .. 'Z' -> {
	        val cha_l = listOf(' ', ':', '$')
	    	val word = nextWordOfEndCharListOfString (cha_l, str)
    	  	val lex = Lexeme.TextWordConstant (word)
		lexemeList.add (lex)
	  	position = position + word.length

                Done = word == str
		if (isLoop(here)) println ("$here: Done '$Done'")
		  }
	      else -> {
		val message = "$here: Error unknown Character '$cha' at position $position of str '$str'"
		throw Exception(message)
	      }
	      } // when
	      } // try
	  catch (e: java.lang.StringIndexOutOfBoundsException) {
	    val cha = str.get(position)
	    val message = "$here: Error at end of line character '$cha' should be '$'"
	    throw Exception(message)
		}
	  } // while
   
   if (isTrace(here)) println("$here: output position $position")
   if (isTrace(here)) println("$here: output lexemeList: "+ fullnameListOfLexemeList(lexemeList))
   val result = Pair(lexemeList.toList(), position)

   exiting(here)
   return result
}

fun lexemeListOfTicLine (lin: String) : Pair<List<Lexeme>, Int> {
// # $tic: 1579373044$'
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input lin '$lin'")
    
    val lexemeList = mutableListOf<Lexeme>()

    var Done = false
    var position = 0
    
    while (!Done) {
    	  try {
    	      var cha = lin.get(position)
	      if (isLoop(here)) println ("$here: while position $position cha '$cha'")
	      when (cha){
	      '$' -> {
	      	  val lex = tokenOfChar(cha, position, lin)
		  lexemeList.add (lex)
		  position = position + 1
		  }
	      't' -> {
	      	  val str = lin.substring(position)
    	      	  val word = nextWordOfEndCharOfString(':', str)
	  	  position = position + word.length
		  val lex = lexemeOfWord (word)
		  lexemeList.add (lex)
		  }
	      ' ' -> {
	      	  val lex = tokenOfChar(cha, position, lin)
		  lexemeList.add (lex)
		  position = position + 1
		  }		  		  
	      ':' -> {
	      	  val lex = tokenOfChar(cha, position, lin)
		  lexemeList.add (lex)
		  position = position + 1
		  }		  		  
	      '0', '1', '2','3','4','5','6','7','8','9' -> {
	      	  val str = lin.substring(position)
		  val word = nextWordOfEndCharOfString('$', str)
		  if (isTicOfString(word)) {
		     position = position + word.length 
    		     val lex = Lexeme.Tic (word)
		     lexemeList.add (lex)
		  }
		  else {
		       val message = "$here: Error word '$word' is not a valid Tic field"
		       throw Exception(message)
		  }
	          }
	       else -> {
		     val message = "$here: Error unknown current Character '$cha' at position $position of lin '$lin'"
		     throw Exception(message)
	          }
		}
   		}
	  catch (e: java.lang.StringIndexOutOfBoundsException) {
	  	val cha = lin.get(position-1)
	  	if (cha.equals('$')) {
	     	   val lex = Lexeme.TokenEndOfLine
             	   lexemeList.add (lex)
	     	   if (isLoop(here)) println("$here: setting End Of Line")
	     	   Done = true			
	     	}
		else {
		     val message = "$here: Error previous character '$cha' should be '$'"
	    	     throw Exception(message)
		}
	  }
   }

   if (isTrace(here)) println("$here: output position $position")
   if (isTrace(here)) println("$here: output lexemeList "+lexemeList)

   val result = Pair(lexemeList.toList(), position)
   exiting(here)
   return result
}

fun lexemeListOfYmlFile (ymlFileName: String): List<Lexeme> {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    var lexemeList = mutableListOf<Lexeme>()
    println("$here: input ymlFileName '$ymlFileName'")
    val lineList = lineListOfFileName (ymlFileName)

    var count = 0
    for (line in lineList) {
      count = count + 1
      if (isLoop(here)) println("$here: for line # $count '$line'")
      if (line.startsWith('#'))  {
      	 val lex_l = lexemeListOfSharpedLine (line)
	 lexemeList.addAll (lex_l)
	 }		
      else {
	 val lex_l = lexemeListOfNonSharpedLine (line)
	 lexemeList.addAll (lex_l)
	 }
    }
    
    if (isTrace(here)) if (isTrace(here)) println("$here: output lexemeList $lexemeList")
    exiting(here)

    return lexemeList
}

fun nameKeywordWithOfLexeme(lex: Lexeme): String {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    println("$here: input lex '$lex'")
    val result = when (lex) {
    	is Lexeme.KeywordWithZ2Hash -> lex.name 
	is Lexeme.KeywordWithDate -> lex.name     
    	is Lexeme.KeywordWithFile -> lex.name 
    	is Lexeme.KeywordWithInteger -> lex.name 
    	is Lexeme.KeywordWithQmHash -> lex.name  
    	is Lexeme.KeywordWithString -> lex.name   
        is Lexeme.KeywordWithPersonName -> lex.name
	else -> "none"
    }
    
    exiting(here + " with result '$result'")
    return result
}

fun stringValueListOfLexemeList (lex_l: List<Lexeme>) : List<String> {
  val str_l = lex_l.map({l -> stringValueOfLexeme (l) })
  return str_l 
}

fun unknownCharacterOfMessage (mes: String?): Char? {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    val messageType : String?
    
    println("$here: input mes '$mes'")
    
    var unknownCharacter = try {
    	messageType = mes?.substring (0, 36)
	println("$here: messageType '$messageType'")
	mes?.get (38)
	}
    catch (e: Exception) {nullChar}

    if (isTrace(here)) println("$here: output character '$unknownCharacter'")
    exiting(here)

    return unknownCharacter
}

