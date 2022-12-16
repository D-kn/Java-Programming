package edu.tuto.logique_interface;

public class Human {
    private String name;
    private String firstname;
    private String classCombatant;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getClassCombatant() {
        return classCombatant;
    }

    public void setClassCombatant(String classCombatant) {
        this.classCombatant = classCombatant;
    }

    public void marcher(){
        System.out.println("l'avatar type humain marche");
    }
    public void courir(){
        System.out.println("l'avatar type humain court");
    }
    public void seBattre(){
        System.out.println("l'avatar type humain se bat");
    }

}

