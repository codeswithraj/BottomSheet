package com.example.bottomsheet

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity() {
    lateinit var clickImageId: ImageView
    private val pickImage = 100
    private var imageUri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clickImageId = findViewById(R.id.click_image)

        val click=findViewById<Button>(R.id.idBtnShowBottomSheet)
        click.setOnClickListener {
            val dialog=BottomSheetDialog(this)
            val view=layoutInflater.inflate(R.layout.bottom_sheet_dialog,null)
            val camera=view.findViewById<ImageView>(R.id.idIVCourse)
            val closeBtn=view.findViewById<Button>(R.id.idBtnDismiss)
            val folder= view.findViewById<Button>(R.id.idBtnGallery)

            folder.setOnClickListener {
                val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                startActivityForResult(gallery, pickImage)
            }
            camera.setOnClickListener{
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent,pic_id)
            }
            closeBtn.setOnClickListener {
                dialog.dismiss()
            }
            dialog.setCancelable(true)
            dialog.setContentView(view)
            dialog.show()
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == pic_id) {
            val photo = data!!.extras!!["data"] as Bitmap?
            clickImageId.setImageBitmap(photo)
        }
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            clickImageId.setImageURI(imageUri)
        }
    }
    companion object {
        private const val pic_id = 123
    }
}