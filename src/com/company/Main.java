package com.company;

import java.util.Arrays;

public class Main
{
    private static final byte FIELD_SIZE = 5;
    private static final byte START_X = (byte)(Math.random() * FIELD_SIZE);
    private static final byte START_Y = (byte)(Math.random() * FIELD_SIZE);
    private static int min_iteration = 200000;
    private static final int COMB_MAX = 40320;

    private static void combinations(int depth)
    {
        int[] best_comb = new int[8];

        int[] i = new int[depth];
	int comb_count = 0;
        for (i[0] = 0; i[0] < depth; i[0]++) {
            for (i[1] = 0; i[1] < depth; i[1]++) {
                for (i[2] = 0; i[2] < depth; i[2]++) {
                    for (i[3] = 0; i[3] < depth; i[3]++) {
                        for (i[4] = 0; i[4] < depth; i[4]++) {
                            for (i[5] = 0; i[5] < depth; i[5]++) {
                                for (i[6] = 0; i[6] < depth; i[6]++) {
                                    for (i[7] = 0; i[7] < depth; i[7]++) {
                                        boolean cont = false;
                                        for (int j = 0; j < depth - 1; j++) {
                                            for (int k = j + 1; k < depth; k++) {
                                                if (i[j] == i[k]) {
                                                    cont = true;
                                                }
                                            }
                                        }
                                        if (cont) {
                                            continue;
                                        }
					comb_count++;
                                        int cur_it = find(i, min_iteration - 2);
                                        if (cur_it > 0 && cur_it < min_iteration) {
                                            best_comb = Arrays.copyOf(i, i.length);
                                            min_iteration = cur_it;
                                        }
                                        if (cur_it == (FIELD_SIZE * FIELD_SIZE - 1)) {
                                            for (int j = 0; j < i.length; j++) {
                                                i[j] = depth;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            System.out.print(i[0]+1 +"/"+ depth +" done.\r");
        }
        System.out.println("Best result "+ min_iteration +" with combination "+ Arrays.toString(best_comb));
    }
    
    public static void main(String[] args)
    {
        long running_time = -System.currentTimeMillis();

	System.out.println("Board size "+ FIELD_SIZE +". Starting point ["+ START_X +","+ START_Y +"]. Max iterations "+ min_iteration);
        combinations(8);
        // fs=5  it=24   Best [1, 5, 7, 3, 0, 2, 6, 4] Total  2.9 sec
        // fs=6  it=345  Best [1, 3, 5, 7, 4, 0, 2, 6] Total 28.6 sec
        // fs=7  it=74   Best [1, 3, 7, 4, 5, 0, 2, 6] Total  7.7 sec
        // fs=8  it=1332 Best [3, 5, 7, 6, 4, 2, 0, 1] Total 32.4 sec
        // fs=9  it=1654 Best [5, 6, 4, 7, 2, 3, 0, 1] Total 62.2 sec
        // fs=10 it=128678 Best [2, 6, 4, 7, 3, 0, 5, 1] Total  sec

        running_time += System.currentTimeMillis();
        System.out.println("Totally run "+ running_time +" msec.");
    }

    private static int find(int[] comb, int min_iteration)
    {
        long running_time = -System.currentTimeMillis();

        HorseKeeper horseKeeper = new HorseKeeper(FIELD_SIZE, 1, comb);
        horseKeeper.add(START_X, START_Y, new byte[FIELD_SIZE][FIELD_SIZE], (byte)1);
        int iterations = horseKeeper.findTheWay(min_iteration);

        if (iterations == 0) {
            return iterations;
        }

        running_time += System.currentTimeMillis();
        //int peak_memory = memory_get_peak_usage() / 1024;
        System.out.println("Script was run "+ running_time +" msec. Comb "+ Arrays.toString(comb));
        System.out.println();

        return iterations;
    }
}
