package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class HorseKeeper {

    private ArrayList<HorseStep> horsesQueue;

    private int max_horses;

    private int field_size;

    private int[] comb;

    public HorseKeeper(byte field_size, int max_horses, int[] comb)
    {
        this.max_horses = max_horses;
        this.field_size = field_size;
        this.comb = comb;
        horsesQueue = new ArrayList<>();
//        horsesQueue.setIteratorMode(SplQueue::IT_MODE_DELETE);
    }

    public boolean add(byte x, byte y, byte[][] field, byte step)
    {
//        if (count(horses) >= max_horses ) {
//            return false;
//        }

        horsesQueue.add(new HorseStep(x, y, field, step, comb));

        return true;
    }

    private static byte[][] get_clean_field(int field_size)
    {
        byte[][] field = new byte[field_size][field_size];

        return field;
    }

    public int findTheWay()
    {
        int c = 0;
        int last_step = field_size * field_size;
        int next_step = 0;
        while (horsesQueue.size() > 0) {
            c++;
            int horseNumber = getHorseNumber();
            HorseStep horse = horsesQueue.get(horseNumber);
            byte[][] new_field = horse.getField();
            int cur_step = horse.getStep();
            next_step = cur_step;
            ArrayList<int[]> newHorses = horse.getNext();
            if (newHorses.size() > 0) {
                next_step = cur_step + 1;
                for (int[] newHorse : newHorses) {
                    add((byte)newHorse[0], (byte)newHorse[1], copyOf(new_field), (byte)next_step);
                    if (last_step == next_step) {
                        showField();
                        break;
                    }
                }
                if (last_step == next_step) {
                    break;
                }
            }
            if (c % 1e+7 == 0) {
                showField();
            }
            horsesQueue.remove(horseNumber);
        }

        showResults(c);

        if (last_step != next_step) {
            return 0;
        }

        return c;
    }

    private int getHorseNumber()
    {
        return horsesQueue.size() - max_horses;
    }

    private void showResults(int c)
    {
        if (horsesQueue.size() > 0) {
            System.out.print("Active horses "+ horsesQueue.size());
        }
        else {
            System.out.print("The Way not exists.");
        }
        System.out.println( " Iterations "+ c);
    }

    public void showField()
    {
        int index = horsesQueue.size() - 1;
//        for (int index = 0; index < horsesQueue.size(); index++) {
            byte[][] horse_field = horsesQueue.get(index).getField();
            System.out.println(" Step " + horsesQueue.get(index).getStep()
                    + ". Horses " + horsesQueue.size() + ".");
            for (byte[] field_line : horse_field) {
                for (byte field_item : field_line) {
                    System.out.print(String.format("%1$4s", field_item));
                }
                System.out.println("");
            }
            System.out.println("");
//        }
//        System.out.println("--------------");
    }

    private static byte[][] copyOf(byte[][] original)
    {
        byte[][] copy = new byte[original.length][];
        for (byte i = 0; i < original.length; i++) {
            copy[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return copy;
    }
}
