package io.ipfs.kotlin

import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class TestGet : BaseIpfsWebserverTest() {

    @Test
    fun testGetString() {
        // setup
        server.enqueue(MockResponse().setBody("result"))
	val (here, caller) = hereAndCaller()
	entering (here, caller)
	
        // invoke
        val result = ipfs.get.cat("hash")

        // assert
        assertThat(result).isEqualTo("result")

        val executedRequest = server.takeRequest()
        assertThat(executedRequest.path).startsWith("/cat/hash")

	if(isTrace(here)) println ("$here: output result $result")
	exiting (here)
    }

}