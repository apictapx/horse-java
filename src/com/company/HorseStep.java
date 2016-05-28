package com.company;

import java.util.ArrayList;

public class HorseStep
{
    private final byte[][] STEPS = new byte[][]{
        {2, 1},
        {2, -1},
        {1, 2},
        {1, -2},
        {-2, 1},
        {-2, -1},
        {-1, 2},
        {-1, -2}
    };

    private byte[][] field;

    private int[] comb;

    private byte x, y, step;

    public HorseStep(byte x, byte y, byte[][] field, byte step, int[] comb)
    {
        this.field = field;
        this.x = x;
        this.y = y;
        this.step = step;
        this.comb = comb;
        this.field[x][y] = step;
    }

    public ArrayList<int[]> getNext()
    {
        ArrayList<int[]> next = new ArrayList<>();
        for (int i : comb) {
            int new_x = x + STEPS[i][0];
            int new_y = y + STEPS[i][1];

            if (check_bounds_and_empty(new_x, new_y)) {
                next.add(new int[]{
                    new_x,
                    new_y
                });
            }
        }

        return next;
    }

    private boolean check_bounds_and_empty(int x, int y)
    {
        return (x >= 0 && y >= 0 && x < field.length && y < field[x].length && field[x][y] == 0);
    }

    public byte[][] getField()
    {
        return field;
    }

    public byte getStep()
    {
        return step;
    }
}
