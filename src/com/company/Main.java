package com.company;

public class Main
{
    public static void main(String[] args)
    {
        final int FIELD_SIZE = 6;

        long running_time = -System.currentTimeMillis();

        HorseKeeper horseKeeper = new HorseKeeper(FIELD_SIZE, 1);
        horseKeeper.add(0, 0, new int[FIELD_SIZE][FIELD_SIZE], 1);
        horseKeeper.findTheWay();

        running_time += System.currentTimeMillis();
        //int peak_memory = memory_get_peak_usage() / 1024;
        System.out.println("Script was run "+ running_time +" sec.");
    }
}
