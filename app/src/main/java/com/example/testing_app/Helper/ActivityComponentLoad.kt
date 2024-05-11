package com.example.testing_app.Helper

import android.app.Dialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewbinding.ViewBinding
import com.example.testing_app.R
import com.example.testing_app.Storage.Data
import com.example.testing_app.databinding.DialogOptionImageViewBinding

object ActivityComponentLoad {
    fun openDialogCustom(bind: ViewBinding, context: Context): Dialog {
        val dialog: Dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(bind.root)
        dialog.show();
        dialog.getWindow()
            ?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.getWindow()?.setGravity(Gravity.FILL);
        return dialog
    }

    fun OpenDialogImage(source: Bitmap) {
        val dialog: Dialog = Dialog(Data.context!!)
        val bind = DialogOptionImageViewBinding.inflate(Data.layoutInflater)
        bind.image.setImageBitmap(source)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(bind.root)
        dialog.show();
        dialog.getWindow()
            ?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.getWindow()?.setGravity(Gravity.CENTER);

        bind.image.setOnClickListener {
            dialog.dismiss()
        }
    }

    fun loadFragment(fragment: Fragment, supportFragmentManager: FragmentManager) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.test_view, fragment)
        transaction.commit()
    }
}