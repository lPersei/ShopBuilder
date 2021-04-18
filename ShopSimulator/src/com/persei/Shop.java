package com.persei;

import java.util.ArrayList;

/**
 * Główna klasa sklepu
 */
public class Shop {
    private int shop_size_x;
    private int shop_size_y;
    private WallColor wall_color;
    private int counter_pos_x;
    private int counter_pos_y;
    private int entrance_pos_x;
    private int entrance_pos_y;
    ArrayList<Shelf> shelfList;

    public Shop() {
    }

    public int getShop_size_x() {
        return shop_size_x;
    }

    public void setShop_size_x(int shop_size_x) {
        this.shop_size_x = shop_size_x;
    }

    public int getShop_size_y() {
        return shop_size_y;
    }

    public void setShop_size_y(int shop_size_y) {
        this.shop_size_y = shop_size_y;
    }

    public WallColor getWall_color() {
        return wall_color;
    }

    public void setWall_color(WallColor wall_color) {
        this.wall_color = wall_color;
    }

    public int getCounter_pos_x() {
        return counter_pos_x;
    }

    public void setCounter_pos_x(int counter_pos_x) {
        this.counter_pos_x = counter_pos_x;
    }

    public int getCounter_pos_y() {
        return counter_pos_y;
    }

    public void setCounter_pos_y(int counter_pos_y) {
        this.counter_pos_y = counter_pos_y;
    }

    public int getEntrance_pos_x() {
        return entrance_pos_x;
    }

    public void setEntrance_pos_x(int entrance_pos_x) {
        this.entrance_pos_x = entrance_pos_x;
    }

    public int getEntrance_pos_y() {
        return entrance_pos_y;
    }

    public void setEntrance_pos_y(int entrance_pos_y) {
        this.entrance_pos_y = entrance_pos_y;
    }

    public ArrayList<Shelf> getShelfList() {
        return shelfList;
    }

    public void setShelfList(ArrayList<Shelf> shelfList) {
        this.shelfList = shelfList;
    }

}
