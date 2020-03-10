package io.ipfs.kotlin.parser

import io.ipfs.kotlin.*
import java.io.File
import java.util.Stack
import java.lang.Character.MIN_VALUE as nullChar

/**
 * Definition : The Parser : List of Lexemes => Tree of Domain Entities
 * Done : 25 février 2020 MutableTree => Tree
 * Author : Emile Achadde 25 février 2020 at 16:31:52+01:00
 */

fun blockKindOfMetaLexemeList (met_l: List<Lexeme>): String {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    var result = "genesis"
    for (lex in met_l) {
      	if (lex is Lexeme.KeywordWithQmHash && lex.name == "previous") {	
       	   result = "current"
	   break
      }
    }	

    exiting(here)
    return result
}

fun leafedNodeAndStackOfLexemeMetaStack (lex_met_s: Stack<Lexeme>): Pair<TreeNode<String>, Stack<Lexeme>> {
// Set up a Leafed Node (ex.: qm / z2....)
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    var nodMut = MutableTreeNode<String>("")
    var Done = false
    
    while (! Done) {
      try {
        var lex = lex_met_s.pop()
        if (isDebug(here)) println ("$here: while lex '$lex'")

      	when (lex) {
      	  is Lexeme.TokenEndOfLine -> {
	     Done=true
	     if (isDebug(here)) println ("$here: while EndOfLine reached")
	  }
     	  is Lexeme.KeywordWithDate -> {
	    var nod_nam = lex.name
	    nodMut = MutableTreeNode<String>(nod_nam)
	  }
     	  is Lexeme.KeywordWithFile -> {
	    var nod_nam = lex.name
	    nodMut = MutableTreeNode<String>(nod_nam)
	  }
	  is Lexeme.KeywordWithQmHash -> {
	    var nod_nam = lex.name
	    nodMut = MutableTreeNode<String>(nod_nam)
	  }
	  is Lexeme.KeywordWithString -> {
	    var nod_nam = lex.name
	    nodMut = MutableTreeNode<String>(nod_nam)
	  }
	  is Lexeme.KeywordWithZ2Hash -> {
	    var nod_nam = lex.name
	    nodMut = MutableTreeNode<String>(nod_nam)
	  }
     	  is Lexeme.KeywordWithInteger -> {
	    var nod_nam = lex.name
	    nodMut = MutableTreeNode<String>(nod_nam)
	  }
	  is Lexeme.KeywordWithPersonName -> {
	    var nod_nam = lex.name
	    nodMut = MutableTreeNode<String>(nod_nam)
	  }
	  is Lexeme.AuthorName -> {
	    var lea_val = lex.name
	    var leaf = MutableTreeNode<String>(lea_val)
	    nodMut.addChild(leaf)
	  }
	  is Lexeme.NextName -> {
	    var lea_val = lex.name
	    var leaf = MutableTreeNode<String>(lea_val)
	    nodMut.addChild(leaf)
	  }
	  is Lexeme.FilePath -> {
	    var lea_val = lex.name
	    var leaf = MutableTreeNode<String>(lea_val)
	    nodMut.addChild(leaf)
          }
	  is Lexeme.DateValue -> {
	    var lea_val = lex.value
	    var leaf = MutableTreeNode<String>(lea_val)
	    nodMut.addChild(leaf)
	  }
	  is Lexeme.QmHash -> {
	    var lea_val = lex.hash
	    var leaf = MutableTreeNode<String>(lea_val)
	    nodMut.addChild(leaf)
	  }
	  is Lexeme.Z2Hash -> {
	    var lea_val = lex.hash
	    var leaf = MutableTreeNode<String>(lea_val)
	    nodMut.addChild(leaf)
	  }
	  is Lexeme.Signature -> {
	    var lea_val = lex.value
	    var leaf = MutableTreeNode<String>(lea_val)
	    nodMut.addChild(leaf)
	  }
	  is Lexeme.Spot -> {
	    var lea_val = lex.value
	    var leaf = MutableTreeNode<String>(lea_val)
	    nodMut.addChild(leaf)
	  }
	  is Lexeme.Tic  -> {
	    var lea_val = lex.value
	    var leaf = MutableTreeNode<String>(lea_val)
	    nodMut.addChild(leaf)
	  }
	  is Lexeme.TokenDollar, is Lexeme.TokenVee, is Lexeme.TokenSharp, is Lexeme.TokenSpace -> {
	    if(isVerbose(here)) println ("$here: lexeme skipped '$lex'")
	    MutableTreeNode<String>("skipped")
	  }
	  else -> {
	    if(isVerbose(here)) println ("$here: lexeme skipped '$lex'")
	    MutableTreeNode<String>("skipped")
	  }
       }
       }
      catch (e:java.util.EmptyStackException) {Done = true }
    }

    val nodImm = nodMut.toTreeNode()
    if (isTrace(here)) println ("$here: output nodImm $nodImm")
    if (isTrace(here)) println ("$here: output lex_met_s '$lex_met_s'")
	
    exiting(here)
    return Pair (nodImm, lex_met_s)
}

fun provideBlockCurrentTreeNode () : TreeNode<String> {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

// <BlockCurrent> ::= <TreeMeta> <TreeText>

    val treeMeta = provideBlockMetaTreeNode ()
    val treeText = provideBlockTextTreeNode ()
    val tree = TreeNode("block-current", listOf(treeMeta, treeText))

    if (isTrace(here)) println ("$here: output tree '$tree'")	
    exiting(here)
    return tree
}

fun provideBlockGenesisTreeNode () : TreeNode<String> {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

// <BlockGenesis> ::= <TreeMeta> <TreeText>

    val treeMeta = provideBlockMetaTreeNode ()
    val treeText = provideBlockTextTreeNode ()
    val tree = TreeNode("block-genesis", listOf(treeMeta, treeText))
    
    exiting(here)
    return tree
}

fun provideBlockMetaTreeNode () : TreeNode<String> {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

// <TreeMeta> ::= TreeMetaRecordList ::= { TreeMetaRecord }

    val nod_l = provideTreeMetaRecordList ()
    val tree = TreeNode("block-meta", nod_l)

    if (isTrace(here)) println ("$here: output tree '$tree'")	
    exiting(here)
    return tree
}

fun provideBlockTextTreeNode () : TreeNode<String> {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

// <TreeText> ::= TreeTextRecordList

    val nod_l = provideTreeTextRecordList ()
    val tree = TreeNode<String> ("block-text", nod_l)

    if (isTrace(here)) println ("$here: output tree '$tree'")	
    exiting(here)
    return tree
}

fun provideMetaLexemeList () : List<Lexeme> {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    val lex_l = provideLexemeList ()

    var metMul = mutableListOf<Lexeme>()
    var is_meta = false

    for (lex in lex_l) {
    	if (isDebug(here)) if (isDebug(here)) println ("$here: for lex '$lex'")

	if (lex is Lexeme.TokenSharp) {
	   is_meta = true
	}
	
	if (is_meta) {
	   metMul.add (lex)
	   if (isDebug(here)) println ("$here: added lex '$lex'")
	}
	
	if (is_meta && (lex is Lexeme.TokenEndOfLine)){
	   is_meta = false
	   if (isDebug(here)) println ("$here: meta set to false")
	}
    }

    val result = metMul.toList()
    if (isTrace(here)) println ("$here: output result "+ fullnameListOfLexemeList(result))
    
    exiting(here)
    return result
}

fun provideRecordTextList () : List<String> {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

// A record is enclosed between two TokenEndOfLine
// Record are rebuilt from Lexemes and Not Parsed Yet
// Need to interpolate variables

    val lex_l = provideTextLexemeList ()

    var rec = ""
    var rec_l = mutableListOf<String>()
	
    for (lex in lex_l) {
        if (isLoop(here)) println ("$here: for lex '$lex'")	
        when (lex) {
       	   is Lexeme.TokenEndOfLine -> {
	      rec_l.add (rec)
	      rec = ""
	   }
	   else -> {
	        var str = stringValueOfLexeme (lex) 
	   	rec = rec + str
		if (isDebug(here)) println ("$here: for str '$str'")	
		if (isDebug(here)) println ("$here: for rec '$rec'")	
		}
      }
    }

    val result = rec_l.toList()
    if (isTrace(here)) println ("$here: output result '$result'")	
    exiting(here)
    return result
}

fun provideTextLexemeList () : List<Lexeme> {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

    val lex_l = provideLexemeList ()

    var texMul = mutableListOf<Lexeme>()
    var is_meta = false

    for (lex in lex_l) {
    	if (isLoop(here)) println ("$here: for lex '$lex'")

	if (lex is Lexeme.TokenSharp) {
	   is_meta = true
	}
	
	if (is_meta && (lex is Lexeme.TokenEndOfLine)){
	   is_meta = false
	   if (isLoop(here)) println ("$here: meta set to false")
	}

	if (! is_meta) {
	   texMul.add (lex)
	   if (isLoop(here)) println ("$here: added lex '$lex'")
	}
	
    }

    val result = texMul.toList()
    if (isTrace(here)) println ("$here: output result "+ fullnameListOfLexemeList(result))
    
    exiting(here)
    return result
}

fun provideTreeMetaRecordList () : List<TreeNode<String>> {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

// <TreeMetaRecordList> ::= { <TreeMetaRecord> }
// <TreeMetaRecord>     ::= Node(Record)-Leaf(value)
//         Source        Date
//           |            |
//        file_path   dd/mm/yyyy

    val lex_met_l = provideMetaLexemeList ()
    var lex_met_s = teeStackOfTeeList (lex_met_l)
    
    var treMut = mutableListOf<TreeNode<String>>()
    var Done = false
    
    while (! Done) {
      try {	  
      	var lex = lex_met_s.pop()
      	if (isLoop(here)) println ("$here: while lex '$lex'")
      	if (lex is Lexeme.TokenSharp) {
	  var (tree, lex_s) = leafedNodeAndStackOfLexemeMetaStack (lex_met_s)
	  treMut.add(tree)
	  lex_met_s = lex_s
	  if (isLoop(here)) println ("$here: while added tree '$tree")	
	  if (isLoop(here)) println ("$here: while lex_met_s '$lex_met_s")	
        }
      }
      catch (e:java.util.EmptyStackException) {
        println ("$here: end of Stack reached")	
        Done = true
      }
    }
    val result = treMut.toList()
    if (isTrace(here)) println ("$here: output result '$result'")	

    exiting(here)
    return result
}

fun provideTreeTextRecordList(): List<TreeNode<String>> {
    val (here, caller) = moduleHereAndCaller()
    entering(here, caller)

// <TreeTextRecordList> ::= { <TreeTextRecord> }

    val nam_l = provideRecordTextList ()

    val empty_l: List<TreeNode<String>> = listOf()
    var result = nam_l.map { nam -> TreeNode(nam, empty_l)}   
    if (isTrace(here)) println ("$here: output result result")

    exiting(here)
    return result
}

