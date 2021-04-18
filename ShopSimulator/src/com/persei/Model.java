package com.persei;

import static org.lwjgl.opengl.GL11.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Klasa do skalowanie i przechowywania wartości sklepu w postaci do rysowania
 */
public class Model {
    private Random rnd = new Random();
    public float scaled_shop_size_x;
    public float scaled_shop_size_y;
    public float scaled_entrance_pos_x;
    public float scaled_entrance_pos_y;
    public float scaled_counter_pos_x;
    public float scaled_counter_pos_y;
    public ArrayList<Shelf> scaled_shelves;
    private float scalenum;
    WallColor wallColor;

    public Model(Shop shop) {
        this.scaled_shop_size_x = (float)shop.getShop_size_x();
        this.scaled_shop_size_y = (float)shop.getShop_size_y();
        this.scaled_entrance_pos_x = (float)shop.getEntrance_pos_x();
        this.scaled_entrance_pos_y = (float)shop.getEntrance_pos_y();
        this.scaled_counter_pos_x = (float)shop.getCounter_pos_x();
        this.scaled_counter_pos_y = (float)shop.getCounter_pos_y();
        this.scaled_shelves = shop.getShelfList();
        this.wallColor = shop.getWall_color();
        getScaled();

    }

    /**
     * skalowanie wszystkich wartości
     */
    private void getScaled(){
        if (scaled_shop_size_x>scaled_shop_size_y){
            scalenum = scaled_shop_size_x;
        } else {
            scalenum = scaled_shop_size_y;
        }
        scaled_shop_size_x /=scalenum;
        scaled_shop_size_y /=scalenum;
        scaled_entrance_pos_x /=scalenum;
        scaled_entrance_pos_y /=scalenum;
        scaled_counter_pos_x /=scalenum;
        scaled_counter_pos_y /=scalenum;

        for (int i = 0 ; i<scaled_shelves.size(); ++i){
            scaled_shelves.get(i).shelf_pos_x /= scalenum;
            scaled_shelves.get(i).shelf_pos_y /= scalenum;
        }

    }

    /**
     * metoda do definiowania koloru z Enum WallColor
     * @return float[r,g,b]
     */
    private float[] DefineWallColor(){
        if (wallColor == WallColor.White){
            return new float[]{ 1f,1f,1f};
        }
        if (wallColor == WallColor.Black){
            return new float[]{ 0f,0f,0f};
        }
        if (wallColor == WallColor.Red){
            return new float[]{ 1f,0f,0f};
        }
        if (wallColor == WallColor.Green){
            return new float[]{ 0f,1f,0f};
        }
        else return new float[]{ 0f,0f,1f};

    }

    /**
     * Rysowanie ścian w oknie OpenGL
     */
    public void DrawWalls(){
        glBegin(GL_LINE_STRIP);
        glColor3fv(DefineWallColor());
        glVertex2f(0f,0f);
        glVertex2f(scaled_shop_size_x,0f);
        glVertex2f(scaled_shop_size_x,scaled_shop_size_y);
        glVertex2f(0f,scaled_shop_size_y);
        glVertex2f(0f,0f);
        glEnd();
    }

    /**
     * Rysowanie wejścia w oknie OpenGL
     */
    public void DrawEntrance(){
        glBegin(GL_QUADS);
        glColor3f(0.5f,0.5f,0.2f);
        glVertex2f(scaled_entrance_pos_x,scaled_entrance_pos_y);
        glVertex2f(scaled_entrance_pos_x+0.5f/scalenum,scaled_entrance_pos_y);
        glVertex2f(scaled_entrance_pos_x+0.5f/scalenum,scaled_entrance_pos_y-0.05f/scalenum);
        glVertex2f(scaled_entrance_pos_x,scaled_entrance_pos_y-0.05f/scalenum);
        glEnd();
    }

    /**
     * Rysowanie kasy w oknie OpenGL
     */
    public void DrawCounter(){
        glBegin(GL_QUADS);
        glColor3f(0f,0.5f,0.5f);
        glVertex2f(scaled_counter_pos_x,scaled_counter_pos_y);
        glVertex2f(scaled_counter_pos_x+0.7f/scalenum,scaled_counter_pos_y);
        glVertex2f(scaled_counter_pos_x+0.7f/scalenum,scaled_counter_pos_y+0.2f/scalenum);
        glVertex2f(scaled_counter_pos_x,scaled_counter_pos_y+0.2f/scalenum);
        glEnd();
    }

    /**
     * Rysowanie Regałów w oknie OpenGL
     */
    public void DrawShelves(){
        for (int i = 0 ; i<scaled_shelves.size(); ++i){
            glBegin(GL_QUADS);
            glColor3fv(DefineProduct(scaled_shelves.get(i)));
            glVertex2f(scaled_shelves.get(i).shelf_pos_x+0.4f/scalenum,scaled_shelves.get(i).shelf_pos_y+0.4f/scalenum);
            glVertex2f(scaled_shelves.get(i).shelf_pos_x+0.6f/scalenum,scaled_shelves.get(i).shelf_pos_y+0.4f/scalenum);
            glVertex2f(scaled_shelves.get(i).shelf_pos_x+0.6f/scalenum,scaled_shelves.get(i).shelf_pos_y+0.9f/scalenum);
            glVertex2f(scaled_shelves.get(i).shelf_pos_x+0.4f/scalenum,scaled_shelves.get(i).shelf_pos_y+0.9f/scalenum);
            glEnd();
        }

    }

    /**
     * metoda do definiowania koloru z Enum Product
     * @return float[r,g,b]
     */
    private float[] DefineProduct(Shelf shelf){
        if(shelf.product == Product.APPLE){
            return new float[]{1f,0f,0f};
        }
        if(shelf.product == Product.BREAD){
            return new float[]{1f,1f,0f};
        }
        if(shelf.product == Product.CORN){
            return new float[]{1f,0.5f,0.1f};
        }
        if(shelf.product == Product.MEAT){
            return new float[]{0.7f,0.2f,0.1f};
        }
        if(shelf.product == Product.WATER){
            return new float[]{0f,0.5f,0.5f};
        }
        else return new float[]{1f,1f,1f};

    }

    /**
     * Metoda do nowego losowania koloru ścian i produktów
     */
    public void SetRandomValues(){
        wallColor = WallColor.values()[rnd.nextInt(WallColor.values().length)];
        for (int i = 0 ; i<scaled_shelves.size(); ++i){
            scaled_shelves.get(i).product = Product.values()[rnd.nextInt(Product.values().length)];
        }
    }
}
