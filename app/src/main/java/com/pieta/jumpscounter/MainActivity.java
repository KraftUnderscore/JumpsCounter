package com.pieta.jumpscounter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.pieta.jumpscounter.data.AppDatabase;
import com.pieta.jumpscounter.data.Session;
import com.pieta.jumpscounter.logic.Timer;
import com.pieta.jumpscounter.fragments.CounterFragment;
import com.pieta.jumpscounter.fragments.StatsFragment;
import com.pieta.jumpscounter.fragments.SummaryFragment;
import com.pieta.jumpscounter.logic.JumpCollector;
import com.pieta.jumpscounter.logic.JumpDetector;
import com.pieta.jumpscounter.logic.LinearAccelerationDetector;

public class MainActivity extends AppCompatActivity {

    public enum State {
        INIT, STOP, START, RESUME, PAUSE, SUMMARY, STATS, COUNTER
    }

    private CounterFragment counterFragment;
    private SummaryFragment summaryFragment;
    private StatsFragment statsFragment;

    private BottomNavigationView bottomNavigationView;

    private JumpCollector collector;
    private JumpDetector detector;
    private Timer timer;

    private Session currentSession;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchState(State.INIT);
    }

    public void switchState(State state) {
        switch (state) {
            case INIT:
                initialize();
                switchState(State.COUNTER);
                break;
            case COUNTER:
                updateFrame(counterFragment);
                break;
            case START:
                currentSession = new Session();
                detector.startDetecting();
                timer.start();
                bottomNavigationView.setVisibility(View.INVISIBLE);
                counterFragment.updateButtons(true, false, true, false);
                break;
            case PAUSE:
                timer.stop();
                detector.stopDetecting();
                counterFragment.updateButtons(true, true, false, false);
                break;
            case RESUME:
                timer.start();
                detector.startDetecting();
                counterFragment.updateButtons(true, false, true, false);
                break;
            case STOP:
                timer.stop();
                detector.stopDetecting();
                float duration = timer.getDuration();
                currentSession.setDuration(duration);
                currentSession.setJumps(collector.getJumps());
                appDatabase.sessionDao().insert(currentSession);
                updateFrame(summaryFragment);
                bottomNavigationView.setVisibility(View.VISIBLE);
                bottomNavigationView.setSelectedItemId(R.id.menu_summary);
                timer.reset();
                collector.reset();
                break;
            case SUMMARY:
                Session lastSession = appDatabase.sessionDao().getLastSession();
                summaryFragment.updateSessionData(lastSession);
                updateFrame(summaryFragment);
                break;
            case STATS:
                updateFrame(statsFragment);
                break;
            default:
                break;
        }
    }

    private void initialize() {
        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database").allowMainThreadQueries().build();

        counterFragment = new CounterFragment(this);
        summaryFragment = new SummaryFragment();
        statsFragment = new StatsFragment();

        timer = new Timer();
        collector = new JumpCollector(counterFragment);
        detector = new LinearAccelerationDetector(this);
        detector.setCollector(collector);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch(item.getItemId()){
                case R.id.menu_counter:
                    switchState(State.COUNTER);
//                    detector.startDetecting();
                    break;
                case R.id.menu_summary:
                    switchState(State.SUMMARY);
//                    detector.stopDetecting();
                    break;
                case R.id.menu_stats:
                    switchState(State.STATS);
//                    detector.stopDetecting();
                    break;
            }
            return true;
        });
    }

    private void updateFrame(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_fragment, fragment)
                .commit();
    }
}