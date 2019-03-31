package shishir.nitmeghalaya.`in`.shishir2019.util.notification

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

/**
 * Created by Devansh on 31/3/19.
 */

class FireBaseInstanceIDService : FirebaseInstanceIdService() {
    override fun onTokenRefresh() {
        //Getting registration token
        val refreshedToken = FirebaseInstanceId.getInstance().token
        //Displaying token on logcat
        Log.d(TAG, "Refreshed token: " + refreshedToken!!)
    }

    companion object {
        private val TAG = "MyFirebaseIIDService"
    }
}