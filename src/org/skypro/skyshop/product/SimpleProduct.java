package org.skypro.skyshop.product;

public class SimpleProduct extends Product{
    private final int cost;


    public SimpleProduct(String name, int cost) {
        super(name);
        this.cost = cost;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return getName() + ": " + getCost();
    }
}
