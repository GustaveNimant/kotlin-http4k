package io.ipfs.kotlin.commands

import io.ipfs.kotlin.IpfsConnection
import io.ipfs.kotlin.model.BandWidthInfo

class Stats(val ipfs: IpfsConnection) {

    private val bandWidthAdapter = ipfs.config.moshi.adapter(BandWidthInfo::class.java)

    fun bandWidth(): BandWidthInfo? {
        val response = ipfs.callCmd("stats/bw")
        return response.use { bandWidthAdapter.fromJson(it.source()) }
    }

}