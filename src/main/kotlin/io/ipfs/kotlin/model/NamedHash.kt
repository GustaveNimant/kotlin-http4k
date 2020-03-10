package io.ipfs.kotlin.model

/**
 * toString() : file/temporary/parser.bnf:QmPSb3KKdkoxVeiWeBgi9Sk3ZXKeddB3s4EFVCuhZLJpoE
 * Author : Emile Achadde 02 mars 2020 at 17:03:10+01:00
 */

data class NamedHash(val Name: String,
                     val Hash: String) {

    override fun toString(): String {
	return Name + ":" + Hash
	}

    fun print() {
	println(toString())
    }

    fun mapOf(): Map<String, String> {
	return mapOf(Name to Hash)
	}
}
