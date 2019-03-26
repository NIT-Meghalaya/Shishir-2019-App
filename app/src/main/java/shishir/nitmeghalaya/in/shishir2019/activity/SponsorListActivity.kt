package shishir.nitmeghalaya.`in`.shishir2019.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.FirebaseFirestore
import org.jetbrains.anko.toast
import shishir.nitmeghalaya.`in`.shishir2019.R
import shishir.nitmeghalaya.`in`.shishir2019.models.SponsorItem
import shishir.nitmeghalaya.`in`.shishir2019.util.SPONSOR_LIST

class SponsorListActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    private var sponsorList = ArrayList<SponsorItem>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sponsor_list)

        db.collection(SPONSOR_LIST)
            .get()
            .addOnSuccessListener {
                toast("Data received")
                for (document in it) {
//                    eventsList.add(document.toObject(ShishirEvent::class.java))
                }

//                addEventsListFragment()
//                Log.v("List", eventsList.toString())
            }
            .addOnFailureListener {
                toast("Error!")
            }
    }
}
