package io.ipfs.kotlin.url

import io.ipfs.kotlin.*
import java.io.File
import java.util.Stack

/**
 * What is it : the Provider and Register storing an Port knowing its Type.
 * Example : (LocalIpfsApi, "127.0.0.1:5001") 
 * What to do : provide PortType by asking if stored in ParameterMap 
 * Author : Emile Achadde 26 février 2020 at 13:11:05+01:00
 * Revision : make Emile Achadde 28 février 2020 at 09:48:23+01:00
 */

sealed class PortType {
  object PortUserDefined: PortType()
  object PortWebui: PortType()
  object PortGateway: PortType()

  override fun toString (): String {
      val result =
	  when (this) {
	      is PortType.PortUserDefined -> "PortUserDefined"
	      is PortType.PortWebui -> "PortWebui"
	      is PortType.PortGateway -> "PortGateway"
	  }
      return result
  }

  companion object {
      fun make (wor: String): PortType {
	  val (here, caller) = moduleHereAndCaller()
	  entering(here, caller)
	  
	  if(isTrace(here)) println ("$here: input wor '$wor'")
	  
	  val result =
	      when (wor) {
		  "webui" -> PortType.PortWebui
		  "gateway" -> PortType.PortGateway
		  else -> PortType.PortUserDefined
	      }

	  if(isTrace(here)) println ("$here: output result $result")
	  
	  exiting(here)
	  return result
      }
  }
}
