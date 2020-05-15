/*
 * Copyright (c) 2020 Taylor Hughes (taylorhughes719).
 */

package net.mythiccraft.skywars.util;

import java.text.NumberFormat;

public class Timer {

    private long startTime, stopTime, last;

    public Timer() {
        this(false);
    }

    public Timer(boolean start) {
        this.reset();
        if (start) this.start();
    }

    public void start() {
        this.startTime = System.nanoTime();
    }

    public void reset() {
        this.startTime = 0L;
        this.stopTime = 0L;
        this.last = 0L;
    }



    public long getTime() {
        this.stopTime = System.nanoTime();
        this.last = this.stopTime - this.startTime;
        return this.last;
    }

    public String getTimeFormatted() {
        return NumberFormat.getNumberInstance().format(this.getTime()) + " nanoseconds";
    }

    public String getTimeMillis() {
        return NumberFormat.getNumberInstance().format((this.getTime() / 1000000) + " ms");
    }
}
