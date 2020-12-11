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

    private var tvTitle: TextView? = null
    private var tvAddress: TextView? = null
    private var tvMail: TextView? = null
    private var tvTel: TextView? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        displayContactButton(false)
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_contact, container, false)
        tvTitle = view.findViewById(R.id.idTitle)
        tvAddress = view.findViewById(R.id.idAdress)
        tvMail = view.findViewById(R.id.idMail)
        tvTel = view.findViewById(R.id.idTel)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tvTitle?.text = Html.fromHtml(Constants.TITRE, HtmlCompat.FROM_HTML_MODE_LEGACY)
        } else {
            tvTitle?.text = Html.fromHtml(Constants.TITRE)
        }
        tvAddress?.text = Constants.ADRESSE
        tvMail?.text = Constants.MAIL
        tvTel?.text = Constants.TEL

        navigateToMapWithAdress()
        navigateToSendMail()
        navigateToCall()

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

    private fun navigateToMapWithAdress() {
        tvAddress?.setOnClickListener {
            val iteranceIntentUri =
                Uri.parse("google.navigation:q=1+Rue+Royale,+Saint+Cloud+France")
            val intent = Intent(Intent.ACTION_VIEW, iteranceIntentUri)
            intent.setPackage("com.google.android.apps.maps")
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            requireActivity().startActivity(intent)
        }
    }

    private fun navigateToSendMail() {

        tvMail?.setOnClickListener {
            val mailIntent = Intent(Intent.ACTION_SENDTO) // it's not ACTION_SEND
            mailIntent.data = Uri.parse("mailto:$Constants.MAIL" )
            mailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(mailIntent)
            requireActivity().startActivity(mailIntent)
        }
    }

    private fun navigateToCall() {
        tvTel?.setOnClickListener {
            val callIntent =
                Intent(Intent.ACTION_DIAL, Uri.parse(Constants.TEL))
            callIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            requireActivity().startActivity(callIntent)
        }
    }
}