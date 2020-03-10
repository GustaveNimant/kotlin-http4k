package io.ipfs.kotlin

import com.squareup.moshi.Moshi
import io.ipfs.kotlin.commands.*
import io.ipfs.kotlin.defaults.createMoshi
import io.ipfs.kotlin.defaults.createOKHTTP
import io.ipfs.kotlin.model.MessageWithCode
import okhttp3.OkHttpClient

/**
 * Remark : peerid added as stats
 * Author : Emile Achadde 27 février 2020 at 08:33:04+01:00
 */

data class IpfsConfiguration(val base_url: String = "http://127.0.0.1:5001/api/v0/",
                             val okHttpClient: OkHttpClient = createOKHTTP(),
                             val moshi: Moshi = createMoshi())

open class Ipfs(configuration: IpfsConfiguration) {

    private val connection = IpfsConnection(configuration)

    val add by lazy { Add(connection) }
    val get by lazy { Get(connection) }
    val info by lazy { Info(connection) }
    val name by lazy { Name(connection) }
    val peerid by lazy { PeerId(connection) }
    val pins by lazy { Pins(connection) }
    val repo by lazy { Repo(connection) }
    val stats by lazy { Stats(connection) }

    val lastError: MessageWithCode? get() = connection.lastError
}
