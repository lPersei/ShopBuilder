package com.persei;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Klasa kierowania stworzeniem sklepu
 */
public class ShopBuilderDirector {
    public ShopBuilderDirector() {
    }

    ArrayList<Shelf> shelfList = new ArrayList<Shelf>();
    Random rnd = new Random();
    int shop_size_x, shop_size_y;
    int counter_pos;//Pozycja kasy obsługi
    Scanner scanner = new Scanner(System.in);

    /**
     * Metoda tworzenia sklepu.
     * Rozmiary sklepu są wprowadzane przez użytkownika w konsoli
     * @param shopBuilder
     * @return Shop
     */
    public Shop constructShop(ShopBuilder shopBuilder) {

        System.out.println("Input shop X size");
        shop_size_x = scanner.nextInt();

        System.out.println("Input shop Y size");
        shop_size_y = scanner.nextInt();
        while(shop_size_x<=2 || shop_size_y <=2){

            System.out.println("Inputing sizes must be bigger than 2");
            System.out.println("Input shop X size");
            shop_size_x = scanner.nextInt();

            System.out.println("Input shop Y size");
            shop_size_y = scanner.nextInt();
        }

        shopBuilder.setShopSize(shop_size_x, shop_size_y);

        counter_pos = rnd.nextInt(shop_size_x-1);

        shopBuilder.setCounterPosition(counter_pos, 0);
        shopBuilder.setEntrancePosition(counter_pos+1, 0);

        shopBuilder.setWallColor(getRandomWallColor());

        fillShelveList();
        shopBuilder.setShelves(shelfList);
        return shopBuilder.build();
    }

    /**
     * Zapisywanie losowych produktów do każdego regału w Liscie
     */
    private void fillShelveList() {
        for (int y = 0; y < shop_size_y; ++y) {
            for (int x = 0; x < shop_size_x; ++x) {
                shelfList.add(new Shelf(x, y, getRandomProduct()));
            }
        }
    }

    /**
     * Losowanie produktów z Enum Product
     * @return Product
     */
    private Product getRandomProduct() {
        return Product.values()[rnd.nextInt(Product.values().length)];
    }

    /**
     * Losowanie koloru ścian z Enum WallColor
     * @return WallColor
     */
    private WallColor getRandomWallColor() {
        return WallColor.values()[rnd.nextInt(WallColor.values().length)];
    }

}
