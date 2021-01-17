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
import com.pieta.jumpscounter.data.SessionAccess;
import com.pieta.jumpscounter.fragments.Stats;
import com.pieta.jumpscounter.logic.DemoAccelerationDetector;
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
                start();
                break;
            case PAUSE:
                pause();
                break;
            case RESUME:
                resume();
                break;
            case STOP:
                stop();
                break;
            case SUMMARY:
                summary();
                break;
            case STATS:
                stats();
                break;
            default:
                break;
        }
    }

    private void stats() {
        SessionAccess sessionAccess = appDatabase.sessionDao();
        Stats stats = new Stats(this);

        stats.setTotalJumps(sessionAccess.getTotalJumps());
        stats.setTotalDuration(sessionAccess.getTotalDuration());
        stats.setTotalSessions(sessionAccess.getTotalSessions());
        stats.setTotalSpeed(sessionAccess.getHighestSpeed());
        stats.setBestSession(sessionAccess.getBestSession());

        statsFragment.updateStatsData(stats);
        updateFrame(statsFragment);
    }

    private void start() {
        currentSession = new Session();
        detector.startDetecting();
        timer.start();
        bottomNavigationView.setVisibility(View.INVISIBLE);
        counterFragment.updateButtons(true, false, true, false);
    }

    private void pause() {
        timer.stop();
        detector.stopDetecting();
        counterFragment.updateButtons(true, true, false, false);
    }

    private void summary() {
        Session lastSession = appDatabase.sessionDao().getLastSession();
        summaryFragment.updateSessionData(lastSession);
        updateFrame(summaryFragment);
    }

    private void resume() {
        timer.start();
        detector.startDetecting();
        counterFragment.updateButtons(true, false, true, false);
    }

    private void stop() {
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
    }

    private void initialize() {
        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database").allowMainThreadQueries().build();

        counterFragment = new CounterFragment(this);
        summaryFragment = new SummaryFragment();
        statsFragment = new StatsFragment();

        timer = new Timer();
        collector = new JumpCollector(counterFragment);
        detector = new DemoAccelerationDetector(this);
        detector.setCollector(collector);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if(item.getItemId() == R.id.menu_counter) {
                switchState(State.COUNTER);
            } else if (item.getItemId() == R.id.menu_summary) {
                switchState(State.SUMMARY);
            } else if (item.getItemId() == R.id.menu_stats) {
                switchState(State.STATS);
            } else {
                switchState(State.COUNTER);
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