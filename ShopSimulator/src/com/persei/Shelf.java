package com.persei;

/**
 * Główna klasa regałów w sklepie
 */
public class Shelf {
    float shelf_pos_x;
    float shelf_pos_y;
    Product product;

    public Shelf(int shelf_pos_x, int shelf_pos_y, Product product) {
        this.shelf_pos_x = shelf_pos_x;
        this.shelf_pos_y = shelf_pos_y;
        this.product = product;
    }
}
