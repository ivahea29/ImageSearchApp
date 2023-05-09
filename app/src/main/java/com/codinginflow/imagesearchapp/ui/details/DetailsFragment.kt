package com.codinginflow.imagesearchapp.ui.details

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.codinginflow.imagesearchapp.R
import com.codinginflow.imagesearchapp.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment(R.layout.fragment_details) {

    // get the arguments passed from GalleryFragment
    private val args by navArgs<DetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // get reference to the layout views
        val binding = FragmentDetailsBinding.bind(view)

        // get the photo object from the arguments passed
        val photo = args.photo

        // load the photo image using Glide library and set an error placeholder
        Glide.with(this)
            .load(photo.urls.full)
            .error(R.drawable.ic_error)
            .listener(object : RequestListener<Drawable> {

                // handle when the image fails to load
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    // hide the progress bar
                    binding.progressBar.isVisible = false
                    return false
                }

                // handle when the image is loaded successfully
                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    // hide the progress bar and show the photo creator text
                    binding.progressBar.isVisible = false
                    binding.textViewCreator.isVisible = true
                    // show the photo description text if it is not null
                    binding.textViewDescription.isVisible = photo.description != null
                    return false
                }
            })
            .into(binding.imageView)

        // set the photo description text
        binding.textViewDescription.text = photo.description

        // create an intent to open the photo creator's attribution url
        val uri = Uri.parse(photo.user.attributionUrl)
        val intent = Intent(Intent.ACTION_VIEW, uri)

        // set the photo creator text with a clickable hyperlink
        binding.textViewCreator.apply {
            text = "Photo by ${photo.user.name} on Unsplash"
            setOnClickListener {
                // start an activity to open the photo creator's attribution url
                context.startActivity(intent)
            }
            // underline the text to show that it is clickable
            paint.isUnderlineText = true
        }
    }
}
