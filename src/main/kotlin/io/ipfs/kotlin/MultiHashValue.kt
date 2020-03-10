package io.ipfs.kotlin

/**
 * What is it : A MultiHash is a Hash string calculated from an inputString
 * Needs      : a hashFunctionType 
 * Needs      : an inputString
 * fn code  dig size hash digest
 * -------- -------- ------------------------------------
 * 00010001 00000100 101101100 11111000 01011100 10110101
 * sha1     4 bytes  4 byte sha1 digest
 * Author : Emile Achadde 23 février 2020 at 11:36:24+01:00
 */

data class MultiHashValue (val hashFunctionType: String, val inputString: String)

