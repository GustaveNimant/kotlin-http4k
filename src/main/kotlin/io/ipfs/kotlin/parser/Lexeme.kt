package io.ipfs.kotlin.parser

import io.ipfs.kotlin.*
import java.io.File
import java.util.Stack
import java.lang.Character.MIN_VALUE as nullChar

/**
 * Definition : The Lexer : Stream of characters => List of Lexemes
 * Done : 25 février 2020 Lexeme {}
 * Author : Emile Achadde 25 février 2020 at 16:38:46+01:00
 */

sealed class Lexeme {
  data class KeywordWithPersonName (val name: String) : Lexeme ()
  data class KeywordWithDate (val name: String) : Lexeme ()
  data class KeywordWithQmHash (val name: String) : Lexeme ()
  data class KeywordWithZ2Hash (val name: String) : Lexeme () 
  data class KeywordWithString (val name: String) : Lexeme ()
  data class KeywordWithFile (val name: String) : Lexeme ()
  data class KeywordWithInteger (val name: String) : Lexeme ()
  data class KeywordFromUser (val name: String) : Lexeme ()
  
  data class AuthorName (val name: String) : Lexeme ()
  data class Comment (val name: String) : Lexeme ()
  data class DateValue (val value: String) : Lexeme ()
  data class FilePath (val name: String) : Lexeme ()
  data class NextName (val name: String) : Lexeme ()
  data class QmHash (val hash: String) : Lexeme ()
  data class Signature (val value: String) : Lexeme ()
  data class Spot (val value: String) : Lexeme ()
  data class Tic (val value: String) : Lexeme ()
  data class Z2Hash (val hash: String) : Lexeme ()

  data class FromUserKeywordValue (val name: String) : Lexeme ()

  data class TextRecordConstant (val record: String) : Lexeme ()
  data class TextStringConstant (val string: String) : Lexeme ()
  data class TextWordConstant (val word: String) : Lexeme ()
  data class TextVariableSubstituable (val variable: String) : Lexeme ()
	  
  object TokenUnknown : Lexeme ()
  object TokenSkipped : Lexeme ()

  object TokenEmptySharpedLine : Lexeme ()
  object TokenEmptyLine : Lexeme ()

  object TokenAt : Lexeme ()
  object TokenDollar : Lexeme ()
  object TokenSpace : Lexeme ()
  object TokenEndOfLine : Lexeme ()
  object TokenVee : Lexeme ()
  object TokenComma : Lexeme ()
  object TokenColon : Lexeme ()
  object TokenSemicolon : Lexeme ()
  object TokenSharp : Lexeme ()
  object TokenDot : Lexeme ()
  object TokenHyphen : Lexeme ()
  object TokenLeftCurvedBracket : Lexeme ()
  object TokenLeftSquareBracket : Lexeme ()
  object TokenRightCurvedBracket : Lexeme ()
  object TokenRightSquareBracket : Lexeme ()
}

fun fullnameOfLexeme (lexeme: Lexeme): String {

    val string = when (lexeme) {

	is Lexeme.AuthorName -> "AuthorName("+lexeme.name+")"
	is Lexeme.Comment -> "Comment("+lexeme.name+")"
	is Lexeme.DateValue -> "DateValue("+lexeme.value+")"
	is Lexeme.FilePath -> "FilePath("+lexeme.name+")"
	is Lexeme.FromUserKeywordValue -> "FromUserKeywordValue("+lexeme.name+")"
	is Lexeme.NextName -> "NextName("+lexeme.name+")"
	is Lexeme.QmHash -> "QmHash("+lexeme.hash+")"
	is Lexeme.Signature -> "Signature("+lexeme.value+")"	
	is Lexeme.Spot -> "Spot("+lexeme.value+")"
	is Lexeme.TextRecordConstant -> "TextRecordConstant("+lexeme.record+")"
	is Lexeme.TextStringConstant -> "TextStringConstant("+lexeme.string+")"
	is Lexeme.TextVariableSubstituable -> "TextVariableSubstituable("+lexeme.variable+")"	
	is Lexeme.TextWordConstant -> "TextWordConstant("+lexeme.word+")"
	is Lexeme.Tic -> "Tic("+lexeme.value+")"	
	is Lexeme.Z2Hash -> "Z2Hash("+lexeme.hash+")"

	is Lexeme.KeywordWithDate    -> "KeywordWithDate("+lexeme.name+")"
    	is Lexeme.KeywordWithFile    -> "KeywordWithFile("+lexeme.name+")"
    	is Lexeme.KeywordWithInteger -> "KeywordWithInteger("+lexeme.name+")"
    	is Lexeme.KeywordWithQmHash  -> "KeywordWithQmHash("+lexeme.name+")"
    	is Lexeme.KeywordWithString  -> "KeywordWithString("+lexeme.name+")"
        is Lexeme.KeywordWithPersonName -> "KeywordWithPersonName("+lexeme.name+")"
	is Lexeme.KeywordWithZ2Hash -> "KeywordWithZ2Hash("+lexeme.name+")"

	is Lexeme.KeywordFromUser -> "KeywordFromUser("+lexeme.name+")"

	Lexeme.TokenAt	-> "TokenAt"
	Lexeme.TokenLeftCurvedBracket -> "TokenLeftCurvedBracket"
	Lexeme.TokenLeftSquareBracket -> "TokenLeftSquareBracket"
	Lexeme.TokenRightCurvedBracket -> "TokenRightCurvedBracket"
	Lexeme.TokenRightSquareBracket -> "TokenRightSquareBracket"
	Lexeme.TokenColon	-> "TokenColon"
	Lexeme.TokenComma	-> "TokenComma"
	Lexeme.TokenDollar	-> "TokenDollar"
	Lexeme.TokenDot	-> "TokenDot"
	Lexeme.TokenEmptyLine  -> "TokenEmptyLine"
	Lexeme.TokenEmptySharpedLine -> "TokenEmptySharpedLine"
	Lexeme.TokenEndOfLine	-> "TokenEndOfLine"
	Lexeme.TokenHyphen	-> "TokenHyphen"
	Lexeme.TokenSemicolon	-> "TokenSemicolon"
	Lexeme.TokenSharp	-> "TokenSharp"
	Lexeme.TokenSkipped    -> "skipped "
	Lexeme.TokenSpace	-> "Lexeme.TokenSpace"
	Lexeme.TokenUnknown    -> "unknown "
	Lexeme.TokenVee	-> "TokenVee"
	}
    return string
}

fun isInMetaOfLexeme(lex: Lexeme): Boolean {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input lex '$lex'")
    val result = when (lex) {
	is Lexeme.TextRecordConstant  -> false
	is Lexeme.TextStringConstant  -> false
	is Lexeme.TextVariableSubstituable  -> false
	is Lexeme.TextWordConstant  -> false

	is Lexeme.FromUserKeywordValue  -> false

	Lexeme.TokenAt	         -> true
	Lexeme.TokenColon	 -> true
	Lexeme.TokenComma	 -> true
	Lexeme.TokenDollar	 -> true
	Lexeme.TokenDot	 -> true
	Lexeme.TokenEndOfLine	 -> true
	Lexeme.TokenHyphen	 -> true
	Lexeme.TokenSemicolon	 -> true
	Lexeme.TokenSharp	 -> true
	Lexeme.TokenLeftCurvedBracket -> true
        Lexeme.TokenLeftSquareBracket -> true
	Lexeme.TokenRightCurvedBracket -> true
        Lexeme.TokenRightSquareBracket -> true
	Lexeme.TokenSpace	 -> true

	is Lexeme.AuthorName  -> true
	is Lexeme.Comment  -> true
	is Lexeme.DateValue  -> true
	is Lexeme.FilePath  -> true
	is Lexeme.NextName  -> true
	is Lexeme.QmHash  -> true
	is Lexeme.Signature  -> true
	is Lexeme.Spot  -> true
	is Lexeme.Tic  -> true
	is Lexeme.Z2Hash  -> true

	is Lexeme.KeywordWithDate     -> true
    	is Lexeme.KeywordWithFile     -> true
    	is Lexeme.KeywordWithInteger  -> true
    	is Lexeme.KeywordWithQmHash   -> true
    	is Lexeme.KeywordWithString   -> true
        is Lexeme.KeywordWithPersonName -> true
	is Lexeme.KeywordWithZ2Hash  -> true

	is Lexeme.KeywordFromUser -> false

	Lexeme.TokenSkipped     -> true
	Lexeme.TokenUnknown     -> true
	Lexeme.TokenVee	 -> true
	Lexeme.TokenEmptyLine   -> true
	Lexeme.TokenEmptySharpedLine  -> true
    }
    exiting(here + " with result '$result'")
    return result
}

fun isMetaKeywordOfString (str: String): Boolean {
  val (here, caller) = moduleHereAndCaller()
  entering(here, caller)

  if (isTrace(here)) println("$here: str '$str'")

    val result = when (str) {
       "Author" -> true
       "Date" -> true
       "Source" -> true
       "Signature" -> true
       "members" -> true 
       "mutable" -> true 
       "parents" -> true 
       "previous" -> true
       "next" -> true 
       "tic" -> true 
       "qm" -> true 
       "spot" -> true
       else -> {
       	    false
	}
  }

  if (isTrace(here)) println("$here: output result $result")

  exiting(here)
  return result
}

fun isMetaKeywordValueOfDollarStringDollar (dol_dol: String): Boolean {
// $keywordFromUser: anything$
// $keyword: value$  
  val (here, caller) = moduleHereAndCaller()
  entering(here, caller)
  
   if (! dol_dol.contains(':')){
    fatalErrorPrint ("A string enclosed with '$' contains a ':'", "'"+dol_dol+"'", "check", here)
  }

  val (keyword, str) = dol_dol.split(':')
  if (isDebug(here)) println("$here: keyword '$keyword'")
  if (isDebug(here)) println("$here: str '$str'")

  val lex = lexemeOfWord (keyword)
  val result = isKeywordWithOfLexeme (lex)
  
  if (isTrace(here)) println("$here: output result $result")

  exiting(here)
  return result
}

fun isInTextOfLexeme(lex: Lexeme): Boolean {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input lex '$lex'")
    val result = when (lex) {
	is Lexeme.TextRecordConstant  -> true
	is Lexeme.TextStringConstant  -> true
	is Lexeme.TextVariableSubstituable  -> true
	is Lexeme.TextWordConstant  -> true

	Lexeme.TokenAt		 -> true
	Lexeme.TokenColon	 -> true
	Lexeme.TokenLeftSquareBracket -> true
	Lexeme.TokenLeftCurvedBracket -> true
	Lexeme.TokenRightSquareBracket -> true
	Lexeme.TokenRightCurvedBracket -> true
	Lexeme.TokenComma	 -> true
	Lexeme.TokenDollar	 -> true
	Lexeme.TokenDot	 -> true
	Lexeme.TokenEndOfLine	 -> true
	Lexeme.TokenHyphen	 -> true
	Lexeme.TokenSemicolon	 -> true
	Lexeme.TokenSharp	 -> true
	Lexeme.TokenSpace	 -> true

	is Lexeme.AuthorName  -> false
	is Lexeme.Comment  -> false
	is Lexeme.DateValue  -> false
	is Lexeme.FilePath  -> false
	is Lexeme.KeywordWithZ2Hash  -> false
	is Lexeme.NextName  -> false
	is Lexeme.QmHash  -> false
	is Lexeme.Signature  -> false
	is Lexeme.Spot  -> false
	is Lexeme.Tic  -> false
	is Lexeme.Z2Hash  -> false

	is Lexeme.FromUserKeywordValue  -> true
	
	is Lexeme.KeywordWithDate     -> false
    	is Lexeme.KeywordWithFile     -> false
    	is Lexeme.KeywordWithInteger  -> false
    	is Lexeme.KeywordWithQmHash   -> false
    	is Lexeme.KeywordWithString   -> false
        is Lexeme.KeywordWithPersonName  -> false

        is Lexeme.KeywordFromUser  -> true

	Lexeme.TokenSkipped     -> false
	Lexeme.TokenUnknown     -> false
	Lexeme.TokenVee	 -> false
	Lexeme.TokenEmptyLine   -> false
	Lexeme.TokenEmptySharpedLine  -> false
    }
    exiting(here + " with result '$result'")
    return result
}

fun lexemeOfWord (keyword: String) : Lexeme {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input keyword: '$keyword'")

   var lexeme = when (keyword) {
       "Author" -> Lexeme.KeywordWithPersonName (keyword)
       "Date" -> Lexeme.KeywordWithDate (keyword)
       "Source" -> Lexeme.KeywordWithFile (keyword)
       "Signature" -> Lexeme.KeywordWithString (keyword)      
       "members" -> Lexeme.KeywordWithString (keyword)
       "mutable" -> Lexeme.KeywordWithFile (keyword)
       "parents" -> Lexeme.KeywordWithQmHash (keyword)
       "previous" -> Lexeme.KeywordWithQmHash (keyword)
       "next" -> Lexeme.KeywordWithString (keyword)
       "tic" -> Lexeme.KeywordWithInteger (keyword)       
       "qm" -> Lexeme.KeywordWithZ2Hash (keyword)
       "spot" -> Lexeme.KeywordWithInteger (keyword)       
       else -> {
       	    Lexeme.KeywordFromUser (keyword)
	}
  }
  
  if (isTrace(here)) println ("$here: output lexeme '$lexeme'")	
  exiting(here)
  return lexeme
 }

fun isKeywordWithOfLexeme(lex: Lexeme): Boolean {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input lex '$lex'")
    val result = when (lex) {
    	is Lexeme.KeywordWithZ2Hash -> true 
	is Lexeme.KeywordWithDate -> true     
    	is Lexeme.KeywordWithFile -> true 
    	is Lexeme.KeywordWithInteger -> true 
    	is Lexeme.KeywordWithQmHash -> true  
    	is Lexeme.KeywordWithString -> true   
        is Lexeme.KeywordWithPersonName -> true
	else -> false
    }
    exiting(here + " with result '$result'")
    return result
}

fun isTokenOfChar(cha: Char) : Boolean {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input cha '$cha'")
    val result = when (cha) {
		'#' -> true
		'$' -> true
		' ' -> true 
		'\n' -> true
		'/' -> true
		',' -> true
		':' -> true
		']' -> true
		'[' -> true
		'}' -> true
		'{' -> true
		';' -> true
		'.' -> true
		'-' -> true
		'a','z' -> true
		else -> false
    }
    
  if (isTrace(here)) println ("$here: output result '$result'")	
  exiting(here)
  return result
 }

fun nextWordOfString(pos:Int, lin: String): String {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input pos '$pos'")
    if (isTrace(here)) println("$here: input lin '$lin'")
    
    val str = lin.substring(pos)
    var word = ""    
    for (c in str){
	  if (isDebug(here)) println("$here: c '$c'")
	  if (isTokenOfChar(c)) {break}
	  word = word.plus(c.toString())
    }

    assert (word.isNotEmpty())
    
    if (isTrace(here)) println("$here: output word '$word'")
    exiting(here)
    return word
}

fun stringValueOfLexeme (lexeme: Lexeme): String {
    val string = when (lexeme) {

	is Lexeme.AuthorName -> lexeme.name
	is Lexeme.Comment -> lexeme.name
	is Lexeme.DateValue -> lexeme.value
	is Lexeme.FilePath -> lexeme.name
	is Lexeme.FromUserKeywordValue -> lexeme.name
	is Lexeme.NextName -> lexeme.name
	is Lexeme.QmHash -> lexeme.hash
	is Lexeme.Signature -> lexeme.value	
	is Lexeme.Spot -> lexeme.value
	is Lexeme.TextRecordConstant -> lexeme.record
	is Lexeme.TextStringConstant -> lexeme.string
	is Lexeme.TextVariableSubstituable -> lexeme.variable	
	is Lexeme.TextWordConstant -> lexeme.word
	is Lexeme.Tic -> lexeme.value	
	is Lexeme.Z2Hash -> lexeme.hash

	is Lexeme.KeywordWithDate    -> lexeme.name
    	is Lexeme.KeywordWithFile    -> lexeme.name
    	is Lexeme.KeywordWithInteger -> lexeme.name
    	is Lexeme.KeywordWithQmHash  -> lexeme.name
    	is Lexeme.KeywordWithString  -> lexeme.name
        is Lexeme.KeywordWithPersonName -> lexeme.name
        is Lexeme.KeywordFromUser -> lexeme.name	
	is Lexeme.KeywordWithZ2Hash -> lexeme.name

	Lexeme.TokenAt         -> "@"
	Lexeme.TokenColon	-> ":"	
	Lexeme.TokenComma	-> ","
	Lexeme.TokenDollar	-> "\$"
	Lexeme.TokenDot	-> "."
	Lexeme.TokenEmptyLine  -> ""
	Lexeme.TokenEmptySharpedLine -> ""
	Lexeme.TokenEndOfLine	-> "\n"
	Lexeme.TokenHyphen	-> "-"
	Lexeme.TokenSemicolon	-> ";"
	Lexeme.TokenSharp	-> "#"
	Lexeme.TokenSpace	-> " "
	Lexeme.TokenVee	-> "v"
	Lexeme.TokenLeftSquareBracket	-> "["
	Lexeme.TokenLeftCurvedBracket	-> "{"
	Lexeme.TokenRightSquareBracket	-> "]"
	Lexeme.TokenRightCurvedBracket	-> "}"
	Lexeme.TokenUnknown    -> "unknown"
	Lexeme.TokenSkipped    -> "skipped"
	}
    return string
}

fun tokenOfChar(cha: Char, pos: Int, lin: String) : Lexeme {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    if (isTrace(here)) println("$here: input cha '$cha'")

    val token = when (cha) {
    		'@' -> Lexeme.TokenAt
		'#' -> Lexeme.TokenSharp
		'$' -> Lexeme.TokenDollar
		' ' -> Lexeme.TokenSpace
		'\n' -> Lexeme.TokenEndOfLine
		'v' -> Lexeme.TokenVee
		',' -> Lexeme.TokenComma
		':' -> Lexeme.TokenColon
		';' -> Lexeme.TokenSemicolon
		'.' -> Lexeme.TokenDot	
		'-' -> Lexeme.TokenHyphen
	else -> {
             val message = "$here: Error unknown Character '$cha' at position $pos of line '$lin'"
    	     throw Exception(message)
	     	}
	}

	if (isTrace(here)) println("$here: output token '$token'")
	exiting(here)
	return token
}
