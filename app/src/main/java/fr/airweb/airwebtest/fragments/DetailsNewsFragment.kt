package fr.airweb.airwebtest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import fr.airweb.airwebtest.MainActivity
import fr.airweb.airwebtest.utils.Constants
import fr.airweb.airwebtest.R
import fr.airweb.airwebtest.domain.models.PsgModel

class DetailsNewsFragment : Fragment() {
    private var psgModelDetail: PsgModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            psgModelDetail = it.getSerializable(Constants.PSG_NEW_DETAIL) as PsgModel?
        }
    }

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_details_news, container, false)
        val image = view.findViewById<ImageView>(R.id.item_image_detail)
        val title = view.findViewById<TextView>(R.id.item_title_detail)
        val content = view.findViewById<TextView>(R.id.item_content_detail)

        title.text = psgModelDetail?.title
        content.text = psgModelDetail?.content
        Glide.with(requireActivity())
            .load(psgModelDetail?.picture)
            .into(image)

        navigateToContactFragment()
        return view
    }

    private fun navigateToContactFragment() {
        (requireActivity()as MainActivity).contactBtn?.setOnClickListener {
            view?.findNavController()
                ?.navigate(R.id.action_DetailsNewsFragment_to_ContactFragment)
        }
    }
}