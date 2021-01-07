package com.pieta.jumpscounter.logic;

import android.widget.TextView;

import com.pieta.jumpscounter.fragments.CounterFragment;

public class JumpCollector {
    private int jumps = 0;
    private final CounterFragment counterFragment;

    public JumpCollector(CounterFragment counterFragment) {
        this.counterFragment = counterFragment;
    }

    public void collectJump() {
        jumps += 1;
        counterFragment.updateCounter(getJumps());
    }

    public int getJumps() {
        return Math.max(0, jumps / 2 - 1);
    }

    public void reset() {
        jumps = 0;
    }
}
