package org.skypro.skyshop.product;

public class DiscountedProduct extends Product{
    private final int basePrice;
    private final int discountInWholePercentages;

    public DiscountedProduct(String name, int basePrice, int discountInWholePercentages){
        super(name);
        this.basePrice = basePrice;
        this.discountInWholePercentages = discountInWholePercentages;
    }

    @Override
    public int getCost() {
        if (discountInWholePercentages <= 0){
            return basePrice;
        }else{
            return basePrice * (1 - discountInWholePercentages/100);
        }
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return getName() + " со скидкой: " + getCost() + " (" + discountInWholePercentages + "%)";
    }
}
