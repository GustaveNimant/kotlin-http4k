package io.ipfs.kotlin.url

import io.ipfs.kotlin.*
import java.io.File
import java.util.Stack

/**
 * What is it : the Types of an Host.
 * Example : (LocalIpfsApi, "127.0.0.1:5001") 
 * What to do : provide HostType by asking if stored in ParameterMap 
 * Author : Emile Achadde 26 février 2020 at 17:24:40+01:00
 */

sealed class HostType {
  object HostUserDefined: HostType()
  object HostLocal: HostType()
  object HostRemote: HostType()

  override fun toString(): String {
      val result =
	  when (this) {
	      HostType.HostLocal -> "HostLocal"
	      HostType.HostRemote -> "HostRemote"
	      HostType.HostUserDefined -> "HostUserDefined"
	  }
      return result
  }

  companion object {
      fun make (wor: String): HostType {
	  val (here, caller) = moduleHereAndCaller()
	  entering(here, caller)
	  
	  if(isTrace(here)) println ("$here: input wor '$wor'")
	  
	  val result =
	      when (wor) {
		  "local" -> HostType.HostLocal
		  "remote" -> HostType.HostRemote
		  else -> HostType.HostUserDefined
	      }
	  if(isTrace(here)) println ("$here: output result $result")
	  
	  exiting(here)
	  return result
      }
  }
}

