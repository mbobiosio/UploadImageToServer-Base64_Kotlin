package com.mbobiosio.uploadimagetoserver.model

import com.google.gson.annotations.SerializedName

/**
 * UploadImageToServer-Base64
 * Created by Mbuodile Obiosio on Jun 06, 2020
 * https://twitter.com/cazewonder
 * Nigeria.
 */
class ImageData {
    @SerializedName("title")
    private val title: String? = null

    @SerializedName("image")
    private val image: String? = null

    @SerializedName("response")
    private val response: String? = null
    fun getResponse(): String? {
        return response
    }
}
