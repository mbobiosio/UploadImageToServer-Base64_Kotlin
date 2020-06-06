package com.mbobiosio.uploadimagetoserver.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.mbobiosio.uploadimagetoserver.R
import com.mbobiosio.uploadimagetoserver.api.ApiService
import com.mbobiosio.uploadimagetoserver.api.RetrofitServer.getRetrofit
import com.mbobiosio.uploadimagetoserver.model.ImageData
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.util.*

class MainActivity : AppCompatActivity() {
    private var mBitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView.setOnClickListener { selectImage() }
        upload_image.setOnClickListener { uploadImage() }
    }

    private fun selectImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, IMAGE)
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            val resultUri = data.data
            imageView!!.setImageURI(resultUri)

            try {
                mBitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, resultUri)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private val image: String
        private get() {
            val baos = ByteArrayOutputStream()
            mBitmap!!.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val dataByte = baos.toByteArray()
            return Base64.encodeToString(dataByte, Base64.DEFAULT)
        }

    private fun uploadImage() {
        val title = "IMG_"
        val api =
            getRetrofit()!!.create(ApiService::class.java)
        val call = api.uploadImage(title, image)
        call!!.enqueue(object : Callback<ImageData?> {
            override fun onResponse(
                call: Call<ImageData?>,
                response: Response<ImageData?>
            ) {
                val data = response.body()!!
                Log.d("Response", data.getResponse())
            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            override fun onFailure(
                call: Call<ImageData?>,
                t: Throwable
            ) {
                Log.d(
                    "Error Response",
                    Objects.requireNonNull(t.message)
                )
            }
        })
    }

    companion object {
        private const val IMAGE = 100
    }
}