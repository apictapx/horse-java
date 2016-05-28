package com.company;

import java.util.Arrays;

public class Main
{
    private static void combinations(int depth)
    {
        int max_it = 100000000;
        int[] best_comb = new int[8];

        int[] i = new int[depth];
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
                                        int[] comb = new int[8];
                                        for (int k = 0; k < depth; k++) {
                                            comb[k] = i[k];
//                                            comb[k + depth] = i[k] + depth;
                                        }

                                        int cur_it = find(comb);
                                        if (cur_it > 0 && cur_it < max_it) {
                                            best_comb = Arrays.copyOf(comb, comb.length);
                                            max_it = cur_it;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println("Best result "+ max_it +" with combination "+ Arrays.toString(best_comb));
    }
    
    public static void main(String[] args)
    {
        long running_time = -System.currentTimeMillis();

//        combinations(8);
        find(new int[]{1, 5, 7, 3, 0, 2, 6, 4});
        // fs=5 it=24 - Best {1, 5, 7, 3, 0, 2, 6, 4}
        // fs=6 it=2189389
        // fs=7 it=38505
        // fs=8 it=1234907
        // TODO совершенно ясно что в полном переборе больших досок нет никакого смысла.
        // TODO важно перебирать ключи обхода доски, останавливая перебор при достижении большого
        // TODO количества итераций (1 000 000)

        // TODO сделать условие выхода по кол-ву итераций
        // TODO запустить перебор ключей для досок 6-10

        running_time += System.currentTimeMillis();
        System.out.println("Totally run "+ running_time +" msec.");
    }

    private static int find(int[] comb)
    {
        final byte FIELD_SIZE = 10;

        long running_time = -System.currentTimeMillis();

        HorseKeeper horseKeeper = new HorseKeeper(FIELD_SIZE, 1, comb);
        horseKeeper.add((byte)0, (byte)0, new byte[FIELD_SIZE][FIELD_SIZE], (byte)1);
        int iterations = horseKeeper.findTheWay();

        running_time += System.currentTimeMillis();
        //int peak_memory = memory_get_peak_usage() / 1024;
        System.out.println("Script was run "+ running_time +" msec. Comb "+ Arrays.toString(comb));
        System.out.println();

        return iterations;
    }
}
