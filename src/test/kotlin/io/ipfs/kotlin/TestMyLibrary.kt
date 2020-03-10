package io.ipfs.kotlin

import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

class TestMyLibrary: BaseIpfsWebserverTest () {

    @Test
    fun testFunctionName() {
        // assert
	println ("My tests done")
        assertThat(functionName()).isEqualTo("testFunctionName")
    }

}
