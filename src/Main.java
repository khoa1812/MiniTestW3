import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        MaterialManager manager = new MaterialManager();

        for (int i = 1; i <= 5; i++) {
            LocalDate manufacturingDate = LocalDate.of(2023, 1, i);
            CrispyFlour crispyFlour = new CrispyFlour("CF" + i, "Crispy Flour " + i, manufacturingDate, 10, 5);
            manager.addMaterial(crispyFlour);
            Meat meat = new Meat("Meat" + i, "Meat " + i, manufacturingDate, 20, 2.5);
            manager.addMaterial(meat);
        }

        System.out.println("Total cost before discounts: $" + manager.calculateTotalCost());
        manager.applyDiscounts();
        System.out.println("Total cost after discounts: $" + manager.calculateTotalCost());

        System.out.println("\nMaterials sorted by price:");
        manager.sortByPrice();
        for (Material material : manager.getMaterials()) {
            System.out.println(material.getName() + ": $" + material.getAmount());
        }
    }
}