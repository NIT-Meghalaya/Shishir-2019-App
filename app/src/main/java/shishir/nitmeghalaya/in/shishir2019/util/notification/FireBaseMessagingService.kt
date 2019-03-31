package shishir.nitmeghalaya.`in`.shishir2019.util.notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import shishir.nitmeghalaya.`in`.shishir2019.R

/**
 * Created by Devansh on 31/3/19.
 */

class FireBaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        //Displaying data in log
        //It is optional
        Log.d(TAG, "Notification Message TITLE: " + remoteMessage!!.notification!!.title!!)
        Log.d(TAG, "Notification Message BODY: " + remoteMessage.notification!!.body!!)
        Log.d(TAG, "Notification Message DATA: " + remoteMessage.data.toString())
        //Calling method to generate notification
        sendNotification(
            remoteMessage.notification!!.title,
            remoteMessage.notification!!.body, remoteMessage.data
        )
    }

    //This method is only generating push notification
    private fun sendNotification(messageTitle: String?, messageBody: String?, row: Map<String, String>) {
        val contentIntent: PendingIntent? = null
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_notification))
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(messageTitle)
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(contentIntent)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(count, notificationBuilder.build())
        count++
    }

    companion object {
        private const val TAG = "MyFirebaseMsgService"
        private var count = 0
    }
}
