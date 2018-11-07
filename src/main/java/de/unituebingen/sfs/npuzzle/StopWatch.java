package de.unituebingen.sfs.npuzzle;

public class StopWatch {
    private final long startTime;

    public StopWatch(){
        startTime = System.currentTimeMillis();
    }

    public double elapsedTime(){
        long currentTime = System.currentTimeMillis();
        return (currentTime-startTime)/ 1000.0;
    }
}
