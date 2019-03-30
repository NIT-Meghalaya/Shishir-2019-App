package shishir.nitmeghalaya.`in`.shishir2019.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.amqtech.opensource.appintroexample.util.SampleSlide
import com.github.paolorotolo.appintro.AppIntro2
import shishir.nitmeghalaya.`in`.shishir2019.R

class IntroSliderActivity : AppIntro2() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addSlide(SampleSlide.newInstance(R.layout.slide_0))
        addSlide(SampleSlide.newInstance(R.layout.slide_1))
        addSlide(SampleSlide.newInstance(R.layout.slide_2))
        addSlide(SampleSlide.newInstance(R.layout.slide_3))
        addSlide(SampleSlide.newInstance(R.layout.slide_4))

        showStatusBar(true)
        showSkipButton(false)
    }

//    override fun onSkipPressed() {
//        // Do something when users tap on Skip button.
//    }
//
//    override fun onNextPressed() {
//        // Do something when users tap on Next button.
//    }
//
    override fun onDonePressed(currentFragment: Fragment) {
        // Do something when users tap on Done button.
        super.onDonePressed(currentFragment)
        finish()
    }
//
//    override fun onSlideChanged() {
//        // Do something when slide is changed
//    }
}