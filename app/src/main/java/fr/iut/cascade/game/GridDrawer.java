package fr.iut.cascade.game;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.AsyncTask;

import java.util.ArrayList;

/**
 * Created by nero9 on 13/03/2018.
 *
 * I TRIED SOMETHING BUT IT DID NOT WORK AS INTENDED
 */

public class GridDrawer extends AsyncTask<Integer, Void, Integer>{

    private Grid grid;
    private Canvas canvas;
    private ArrayList<Cell> cells;
    private Paint paint;
    private float cell_width;
    private float cell_height;
    private float animation_speed;

    public GridDrawer(Grid grid, Canvas canvas, Paint paint, ArrayList<Cell> cells, float cell_width, float cell_height, float animation_speed){
        this.grid = grid;
        this.canvas = canvas;
        this.cells = cells;
        this.paint = paint;
        this.cell_width = cell_width;
        this.cell_height = cell_height;
        this.animation_speed = animation_speed;
    }

    @Override
    protected Integer doInBackground(Integer... integers) {
        int start_index = integers[0];
        int end_index = integers[1];

        for(int i = start_index ; i < end_index ; ++ i){
            Cell c = cells.get(i);

            paint.setColor(c.getColor());
            paint.setAlpha(210);
            float left,top,right,bottom;

            if(c.isMoving()){
                left =  c.getMovingColumn() * cell_width;
                top =  c.getMovingLine() * cell_height;
                right =  (c.getMovingColumn() + 1f) * cell_width;
                bottom =  (c.getMovingLine() + 1f) * cell_height;
                c.move(animation_speed);
                grid.invalidate();
            }else{
                left =  ((float) c.getColumn() * cell_width);
                top =  ((float) c.getLine() * cell_height);
                right =  ((float) (c.getColumn() + 1) * cell_width);
                bottom =  ((float) (c.getLine() + 1) * cell_height);
            }

            canvas.drawRect(left, top, right, bottom, paint);
        }

        return 0;
    }
}
