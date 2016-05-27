package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class HorseKeeper {

    private ArrayList<HorseStep> horsesQueue;

    private int max_horses;

    private int field_size;

    public HorseKeeper(int field_size, int max_horses)
    {
        this.max_horses = max_horses;
        this.field_size = field_size;
        horsesQueue = new ArrayList<>();
//        horsesQueue.setIteratorMode(SplQueue::IT_MODE_DELETE);
    }

    public boolean add(int x, int y, int[][] field, int step)
    {
//        if (count(horses) >= max_horses ) {
//            return false;
//        }

        horsesQueue.add(new HorseStep(x, y, field, step));

        return true;
    }

    private static int[][] get_clean_field(int field_size)
    {
        int[][] field = new int[field_size][field_size];

        return field;
    }

    public void findTheWay()
    {
        int c = 0;
        int last_step = field_size * field_size;
        while (horsesQueue.size() > 0) {
            c++;
            HorseStep horse = horsesQueue.get(0);
            int[][] new_field = horse.getField();
            int cur_step = horse.getStep();
            int next_step = cur_step;
            ArrayList<int[]> newHorses = horse.getNext();
            if (newHorses.size() > 0) {
                next_step = cur_step + 1;
                for (int[] newHorse : newHorses) {
                    add(newHorse[0], newHorse[1], copyOf(new_field), next_step);
                    if (last_step == next_step) {
                        showField();
                        break;
                    }
                }
                if (last_step == next_step) {
                    break;
                }
            }
            if (c % 100000 == 0) {
                showField();
            }
            horsesQueue.remove(0);
        }

        showResults(c);
    }

    private void showResults(int c)
    {
        if (horsesQueue.size() > 0) {
            System.out.println("Active horses "+ horsesQueue.size());
            System.out.println("Show the field here.");
        }
        else {
            System.out.println("The Way not exists.");
        }
        System.out.println( "Iterations "+ c);
    }

    public void showField()
    {
        int index = horsesQueue.size() - 1;
//        for (int index = 0; index < horsesQueue.size(); index++) {
            int[][] horse_field = horsesQueue.get(index).getField();
            System.out.println(" Step " + horsesQueue.get(index).getStep()
                    + ". Horses " + horsesQueue.size() + ".");
            for (int[] field_line : horse_field) {
                for (int field_item : field_line) {
                    System.out.print(String.format("%1$4s", field_item));
                }
                System.out.println("");
            }
            System.out.println("");
//        }
//        System.out.println("--------------");
    }

    private static int[][] copyOf(int[][] original)
    {
        int[][] copy = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            copy[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return copy;
    }
}
