package com.pieta.jumpscounter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pieta.jumpscounter.fragments.CounterFragment;
import com.pieta.jumpscounter.fragments.StatsFragment;
import com.pieta.jumpscounter.fragments.SummaryFragment;
import com.pieta.jumpscounter.logic.JumpCollector;
import com.pieta.jumpscounter.logic.JumpDetector;
import com.pieta.jumpscounter.logic.LinearAccelerationDetector;

public class MainActivity extends AppCompatActivity {

    public enum State {
        INIT, STOP, START, RESUME, PAUSE, SAVE_DATA, SUMMARY, STATS, COUNTER
    }

    private CounterFragment counterFragment;
    private SummaryFragment summaryFragment;
    private StatsFragment statsFragment;

    private JumpCollector collector;
    private JumpDetector detector;
    private BottomNavigationView bottomNavigationView;

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
                counterFragment.updateCounter(69);
                counterFragment.updateButtons(true, false, true, false);
                break;
            case PAUSE:
                counterFragment.updateButtons(true, true, false, false);
                break;
            case RESUME:
                counterFragment.updateButtons(true, false, true, false);
                break;
            case STOP:
                updateFrame(summaryFragment);
                bottomNavigationView.setSelectedItemId(R.id.menu_summary);
                // SAVE DATA
                break;
            case SAVE_DATA:
                break;
            case SUMMARY:
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
        collector = new JumpCollector(findViewById(R.id.textView));
        detector = new LinearAccelerationDetector(this);
        detector.setCollector(collector);

        counterFragment = new CounterFragment(this);
        summaryFragment = new SummaryFragment();
        statsFragment = new StatsFragment();

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