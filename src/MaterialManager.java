import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class MaterialManager {
    private List<Material> materials = new ArrayList<>();

    public void addMaterial(Material material) {
        materials.add(material);
    }

    public void editMaterial(String id, Material newMaterial) {
        for (int i = 0; i < materials.size(); i++) {
            if (materials.get(i).getId().equals(id)) {
                materials.set(i, newMaterial);
                break;
            }
        }
    }

    public void deleteMaterial(String id) {
        materials.removeIf(material -> material.getId().equals(id));
    }

    public double calculateTotalCost() {
        double totalCost = 0;
        for (Material material : materials) {
            totalCost += material.getAmount();
        }
        return totalCost;
    }

    public void applyDiscounts() {
        LocalDate currentDate = LocalDate.now();
        for (Material material : materials) {
            if (material instanceof Meat) {
                LocalDate expiryDate = material.getExpiryDate();
                long daysUntilExpiry = currentDate.until(expiryDate).getDays();
                if (daysUntilExpiry <= 5) {
                    material = applyDiscount(material, 0.3);
                } else if (daysUntilExpiry <= 30) {
                    material = applyDiscount(material, 0.1);
                }
            } else if (material instanceof CrispyFlour) {
                LocalDate expiryDate = material.getExpiryDate();
                long monthsUntilExpiry = currentDate.until(expiryDate).toTotalMonths();
                if (monthsUntilExpiry <= 2) {
                    material = applyDiscount(material, 0.4);
                } else if (monthsUntilExpiry <= 4) {
                    material = applyDiscount(material, 0.2);
                } else {
                    material = applyDiscount(material, 0.05);
                }
            }
        }
    }

    private Material applyDiscount(Material material, double discountRate) {
        int newCost = (int) (material.getCost() * (1 - discountRate));
        return new Material(material.getId(), material.getName(), material.getManufacturingDate(), newCost) {
            @Override
            public double getAmount() {
                return material.getAmount();
            }

            @Override
            public LocalDate getExpiryDate() {
                return material.getExpiryDate();
            }
        };
    }

    public void sortByPrice() {
        Collections.sort(materials, Comparator.comparingDouble(Material::getAmount));
    }

    public List<Material> getMaterials() {
        return materials;
    }
}
