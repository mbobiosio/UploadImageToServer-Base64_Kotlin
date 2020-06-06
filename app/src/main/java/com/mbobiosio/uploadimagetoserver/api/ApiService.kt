package com.mbobiosio.uploadimagetoserver.api

import com.mbobiosio.uploadimagetoserver.model.ImageData
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * UploadImageToServer-Base64
 * Created by Mbuodile Obiosio on Jun 06, 2020
 * https://twitter.com/cazewonder
 * Nigeria.
 */
interface ApiService {

    @FormUrlEncoded
    @POST("upload.php")
    fun uploadImage(
        @Field("title") title: String?,
        @Field("image") image: String?
    ): Call<ImageData?>?

}