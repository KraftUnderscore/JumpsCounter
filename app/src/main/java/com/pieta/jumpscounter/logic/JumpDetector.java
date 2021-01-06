package com.pieta.jumpscounter.logic;

public interface JumpDetector {
    void setCollector(JumpCollector collector);
    void updateCollector();
    void startDetecting();
    void stopDetecting();
}
