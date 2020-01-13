package k_visualization.Get_Pi_with_Monte_Carlo;

import java.awt.*;
import javax.swing.*;

public class Circle {

    private int x, y, r;

    public Circle(int x, int y, int r){
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public int getX(){ return x; }
    public int getY(){ return y; }
    public int getR(){ return r; }

    public boolean contain(Point p){
        return Math.pow(p.x - x, 2) + Math.pow(p.y - y, 2) <= r*r;
    }
}
