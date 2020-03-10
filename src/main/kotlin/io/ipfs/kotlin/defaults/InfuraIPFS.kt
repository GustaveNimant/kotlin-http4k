package io.ipfs.kotlin.defaults

import io.ipfs.kotlin.Ipfs
import io.ipfs.kotlin.IpfsConfiguration

val infuraIpfsConfig by lazy {
    IpfsConfiguration("https://ipfs.infura.io:5001/api/v0/", createOKHTTP(), createMoshi())
}

open class InfuraIpfs : Ipfs(infuraIpfsConfig)