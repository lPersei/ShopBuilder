package com.persei;

import java.util.ArrayList;

/**
 * Builder obiektu Shop
 */
public class ShopBuilder {
    private final Shop shop;

    public ShopBuilder() {
        shop = new Shop();
    }

    public Shop build() {
        return shop;
    }

    public ShopBuilder setShopSize(int x, int y) {
        shop.setShop_size_x(x);
        shop.setShop_size_y(y);
        return this;
    }

    public ShopBuilder setWallColor(WallColor wallColor) {
        shop.setWall_color(wallColor);
        return this;
    }

    public ShopBuilder setCounterPosition(int x, int y) {
        shop.setCounter_pos_x(x);
        shop.setCounter_pos_y(y);
        return this;
    }

    public ShopBuilder setEntrancePosition(int x, int y) {
        shop.setEntrance_pos_x(x);
        shop.setEntrance_pos_y(y);
        return this;
    }

    public ShopBuilder setShelves(ArrayList<Shelf> shelfList) {
        shop.setShelfList(shelfList);
        return this;
    }
}
