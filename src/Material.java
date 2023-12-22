import java.time.LocalDate;

public abstract class Material {
    public String id;
    public String name;
    public LocalDate manufacturingDate;
    public int cost;

    public Material(String id, String name, LocalDate manufacturingDate, int cost) {
        this.id = id;
        this.name = name;
        this.manufacturingDate = manufacturingDate;
        this.cost = cost;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getManufacturingDate() {
        return manufacturingDate;
    }

    public int getCost() {
        return cost;
    }

    public abstract double getAmount();

    public abstract LocalDate getExpiryDate();
}





