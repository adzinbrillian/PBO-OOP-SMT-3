public class Employee {
    private String name;
    private String skill; // Cooking, Serving, Cleaning
    private boolean isAvailable;

    public Employee(String name, String skill) {
        this.name = name;
        this.skill = skill;
        this.isAvailable = true;
    }

    public void setAvailable(boolean status) {
        this.isAvailable = status;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getSkill() {
        return skill;
    }

    @Override
    public String toString() {
        return name + " (" + skill + ")";
    }
}