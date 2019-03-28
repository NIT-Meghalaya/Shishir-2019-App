package shishir.nitmeghalaya.`in`.shishir2019.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_contact.*
import shishir.nitmeghalaya.`in`.shishir2019.R
import android.content.Intent
import android.net.Uri
import android.R.id.message




class ContactFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_contact, container, false)
        //TODO: Add actions for buttons


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnEmail.setOnClickListener({
            val intent = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "shishir@nitm.ac.in", null))
            startActivity(Intent.createChooser(intent, "Choose an Email client :"))
        })

        btnWebsite.setOnClickListener({
            val uri = Uri.parse("http://nitmeghalaya.in/shishir/")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        })

        btnFacebook.setOnClickListener({
            val uri = Uri.parse("https://www.facebook.com/shishir.nitm/")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        })

//        btnInstagram.setOnClickListener({
//            val uri = Uri.parse("http://www.nitmeghalaya.in/shishir/#")
//            val intent = Intent(Intent.ACTION_VIEW, uri)
//            startActivity(intent)
//        })
//
//        btnYoutube.setOnClickListener({
//            val uri = Uri.parse("http://www.nitmeghalaya.in/shishir/#")
//            val intent = Intent(Intent.ACTION_VIEW, uri)
//            startActivity(intent)
//        })


    }

    companion object {
        @JvmStatic
        fun newInstance() = ContactFragment()
    }
}
