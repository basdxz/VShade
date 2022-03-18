package com.basdxz.vshade.example;

import lombok.*;


public class Profiler {
    protected static final int MAX_SAMPLES = 100;

    protected final long firstNano;

    protected int deltaIndex = 0;
    protected long lastDelta = 0;
    protected long deltaSum = 0;
    protected long deltaAverage = 0;
    protected long[] deltaSamples = new long[MAX_SAMPLES];
    protected long lastUpdateNano;

    public Profiler() {
        firstNano = System.nanoTime();
        lastUpdateNano = firstNano;
    }

    public void updateTime() {
        val currentTimeNano = System.nanoTime();
        lastDelta = currentTimeNano - lastUpdateNano;
        lastUpdateNano = currentTimeNano;

        deltaSum -= deltaSamples[deltaIndex];
        deltaSum += lastDelta;
        deltaSamples[deltaIndex] = lastDelta;
        if (++deltaIndex == MAX_SAMPLES)
            deltaIndex = 0;

        deltaAverage = deltaSum / MAX_SAMPLES;
    }

    public float runningSeconds() {
        return (lastUpdateNano - firstNano) / 1E9F;
    }

    public float updateMS() {
        return deltaAverage / 1E6F;
    }

    public int updatesPerSecond() {
        val updateSecond = deltaAverage / 1E9F;
        return Math.round(1 / updateSecond);
    }
}
