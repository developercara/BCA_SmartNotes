package com.developercara.bcanotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

class OnboardingFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_onboarding, container, false)
        val imageView: ImageView = rootView.findViewById(R.id.image_view)

        val args = requireArguments()
        val imageResId = args.getInt(ARG_IMAGE_RES_ID)

        imageView.setImageResource(imageResId)

        return rootView
    }

    companion object {
        private const val ARG_IMAGE_RES_ID = "image_res_id"

        fun newInstance(imageResId: Int): OnboardingFragment {
            val args = Bundle()
            args.putInt(ARG_IMAGE_RES_ID, imageResId)

            val fragment = OnboardingFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
