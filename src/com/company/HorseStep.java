package com.company;

import java.util.ArrayList;

public class HorseStep
{
    private final int[][] STEPS = new int[][]{
        {2, 1},
        {2, -1},
        {1, 2},
        {1, -2},
        {-2, 1},
        {-2, -1},
        {-1, 2},
        {-1, -2}
    };

    private int[][] field;

    private int x, y, step;

    public HorseStep(int x, int y, int[][] field, int step)
    {
        this.field = field;
        this.x = x;
        this.y = y;
        this.step = step;
        this.field[x][y] = step;
    }

    public ArrayList<int[]> getNext()
    {
        ArrayList<int[]> next = new ArrayList<>();
        for (int[] step_item : STEPS) {
            int new_x = x + step_item[0];
            int new_y = y + step_item[1];

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

    public int[][] getField()
    {
        return field;
    }

    public int getStep()
    {
        return step;
    }
}
