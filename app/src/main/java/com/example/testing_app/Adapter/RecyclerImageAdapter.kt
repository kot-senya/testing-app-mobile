package com.example.testing_app.Adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testing_app.Helper.ActivityComponentLoad.OpenDialogImage
import com.example.testing_app.databinding.PartItemImageBinding

class RecyclerImageAdapter(var elements: MutableList<ByteArray>):RecyclerView.Adapter<ImageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = PartItemImageBinding.inflate(inflate)
        return ImageViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return elements.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val source = byteArrayToBitmap(elements[position])
        holder.bind.image.setImageBitmap(source)

        holder.bind.image.setOnClickListener {
            OpenDialogImage(source)
        }
    }

    private fun byteArrayToBitmap(data: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(data, 0, data.size)
    }
}
class ImageViewHolder(var bind: PartItemImageBinding): RecyclerView.ViewHolder(bind.root)