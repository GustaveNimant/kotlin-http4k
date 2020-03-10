package io.ipfs.kotlin.commands

import io.ipfs.kotlin.*
import com.squareup.moshi.JsonAdapter
import io.ipfs.kotlin.IpfsConnection
import io.ipfs.kotlin.model.NamedHash

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.Request

// The extension functions are in companion objects.
// https://square.github.io/okhttp/upgrading_to_okhttp_4/#extension-functions
import okhttp3.Headers.Companion.toHeaders

//import okhttp3.ByteArray.Companion.toRequestBody
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.RequestBody.Companion.asRequestBody

import java.io.File
import java.net.URLEncoder

/**
 * Revision : Emile Achadde 19 Feb 2020 : Headers.of(Map) => Map.toHeaders()
 * Revision : Emile Achadde 19 Feb 2020 : MediaType.parse(String) => String.toMediaType()
 */

class Add(val ipfs: IpfsConnection) {

    private val adapter: JsonAdapter<NamedHash> by lazy {
        ipfs.config.moshi.adapter(NamedHash::class.java)
    }

    // Accepts a single file or directory and returns the named hash.
    // For directories we return the hash of the enclosing
    // directory because that makes the most sense, also for
    // consistency with the java-ipfs-api implementation.
    fun file(file: File, name: String = "file", filename: String = name) = addGeneric {
        addFile(it, file, name, filename)
    }.last()


    // Accepts a single file or directory and returns the named hash.
    // Returns a collection of named hashes for the containing directory
    // and all sub-directories.

    fun directory(file: File, name: String = "file", filename: String = name)
	= addGeneric {
            addFile(it, file, name, filename)
	}
    
    // this has to be outside the lambda because it is reentrant to handle subdirectory structures
    private fun addFile(builder: MultipartBody.Builder, file: File, name: String, filename: String) {
	
	val (here, caller) = moduleHereAndCaller()
	entering(here, caller)
	
	if(isDebug(here)) println ("name $name")
	
	val encodedFileName = URLEncoder.encode(filename, "UTF-8")
	/*
	 val headers = Headers.of("Content-Disposition", "file; filename=\"$encodedFileName\"", "Content-Transfer-Encoding", "binary")
*/
	
	val mapH = mapOf (Pair("Content-Disposition",
			       "file; filename=\"$encodedFileName\""),
			  Pair("Content-Transfer-Encoding",
			       "binary"))
        
	val headers = mapH.toHeaders()
	
	if (file.isDirectory) {
	    // add directory
	    val mediaType = ("application/x-directory").toMediaType()
  	    val request = "".toRequestBody(mediaType)
            builder.addPart(headers, request)

            // add files and subdirectories
            for (f: File in file.listFiles()) {
		addFile(builder, f, f.name, filename + "/" + f.name)
            }
	} else {
	    val mediaType = ("application/octet-stream").toMediaType()
	    val request = file.asRequestBody(mediaType)
            builder.addPart(headers, request)
        }
	
	exiting(here)
	
    } // addFile
    
    fun string(text: String, name: String = "string", filename: String = name): NamedHash {
	
        return addGeneric {
	    val mediaType = ("application/octet-stream").toMediaType()
	    val request = text.toRequestBody(mediaType)
            it.addFormDataPart(name, filename, request)
        }.last()
        // there can be only one
	
    }
    
    private fun addGeneric(withBuilder: (MultipartBody.Builder) -> Any): List<NamedHash> {
        val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
        withBuilder(builder)
        val requestBody = builder.build()
	
        val request = Request.Builder()
            .url("${ipfs.config.base_url}add?stream-channels=true&progress=false")
            .post(requestBody)
            .build()
	
        val response = ipfs.config.okHttpClient.newCall(request).execute().body
	
        response.use { responseBody ->
		return responseBody!!.charStream().readLines().asSequence().map {
                adapter.fromJson(it)
            }.filterNotNull().toList()
        }
    }
}
