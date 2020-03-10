package io.ipfs.kotlin

import io.ipfs.kotlin.defaults.localIpfsConfig
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before

abstract class BaseIpfsWebserverTest {

    val server = MockWebServer()
    val ipfs: Ipfs by lazy {
        Ipfs(localIpfsConfig.copy(base_url = server.url("").toString()))
    }

    @Before
    fun runBeforeEveryTest() {
        server.start()
    }

    @After
    fun runAfterEveryTest() {
        server.shutdown()
    }
}