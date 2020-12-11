package fr.airweb.airwebtest.fragments

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import fr.airweb.airwebtest.MainActivity
import fr.airweb.airwebtest.R
import fr.airweb.airwebtest.utils.Constants

class ContactFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        displayContactButton(false)
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_contact, container, false)
        val tvTitle = view.findViewById<TextView>(R.id.idTitle)
        val tvAddress = view.findViewById<TextView>(R.id.idAdress)
        val tvMail = view.findViewById<TextView>(R.id.idMail)
        val tvTel = view.findViewById<TextView>(R.id.idTel)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tvTitle.text = Html.fromHtml(Constants.TITRE, HtmlCompat.FROM_HTML_MODE_LEGACY)
        } else {
            tvTitle.text = Html.fromHtml(Constants.TITRE)
        }
        tvAddress.text = Constants.ADRESSE
        tvMail.text = Constants.MAIL
        tvTel.text = Constants.TEL

        tvTel.setOnClickListener {
            val callIntent =
                Intent(Intent.ACTION_DIAL, Uri.parse(Constants.TEL))
            requireActivity().startActivity(callIntent)
        }
        tvMail.setOnClickListener {
            val mailIntent = Intent(Intent.ACTION_SEND).apply {
                putExtra(Intent.EXTRA_EMAIL, arrayOf(Constants.MAIL))
            }
            requireActivity().startActivity(mailIntent)
        }

        tvAddress.setOnClickListener {
            val iteranceIntentUri = Uri.parse("google.navigation:q=1+Rue+Royale,+Saint+Cloud+France")
            val intent = Intent(Intent.ACTION_VIEW, iteranceIntentUri)
            intent.setPackage("com.google.android.apps.maps")
            requireActivity().startActivity(intent)
        }
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        displayContactButton(true)
    }

    override fun onPause() {
        super.onPause()
        displayContactButton(true)
    }

    private fun displayContactButton(display: Boolean) {
        (requireActivity() as MainActivity).contactBtn?.visibility =
            if (display) View.VISIBLE else View.GONE
    }
}