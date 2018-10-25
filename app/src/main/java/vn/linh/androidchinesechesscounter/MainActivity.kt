package vn.linh.androidchinesechesscounter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import com.ankushgrover.hourglass.Hourglass
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    var TIME: Long = 5 * 60 * 1000;
    lateinit var player1Timer: Hourglass
    lateinit var player2Timer: Hourglass
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        player1Timer = object : Hourglass(TIME, 1000) {
            override fun onTimerTick(timeRemaining: Long) {
                player_1_time.text = millisecondToFullTime(timeRemaining)
            }

            override fun onTimerFinish() {

            }
        }

        player2Timer = object : Hourglass(TIME, 1000) {
            override fun onTimerTick(timeRemaining: Long) {
                player_2_time.text = millisecondToFullTime(timeRemaining)
            }

            override fun onTimerFinish() {

            }
        }

        button_start.setOnClickListener {
            player1Timer.startTimer()
        }

        player_1_time.setOnClickListener {
            player1Timer.pauseTimer()
            player2Timer.resumeTimer()

            player_1_time.setBackgroundColor(C)
        }

        player_2_time.setOnClickListener {
            player2Timer.pauseTimer()
            player1Timer.resumeTimer()
        }
    }

    fun millisecondToFullTime(second: Long): String {
        return timeUnitToFullTime(second, TimeUnit.MILLISECONDS)
    }

    fun timeUnitToFullTime(time: Long, timeUnit: TimeUnit): String {
        val day = timeUnit.toDays(time)
        val hour = timeUnit.toHours(time) % 24
        val minute = timeUnit.toMinutes(time) % 60
        val second = timeUnit.toSeconds(time) % 60
        return if (day > 0) {
            String.format("%dday %02d:%02d:%02d", day, hour, minute, second)
        } else if (hour > 0) {
            String.format("%d:%02d:%02d", hour, minute, second)
        } else if (minute > 0) {
            String.format("%d:%02d", minute, second)
        } else {
            String.format("%02d", second)
        }
    }

}
