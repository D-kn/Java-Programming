package edu.tuto.logique_interface;

public class Zombi implements Avatar{
    private String name;
    private String firstname;
    private String classCombatant;

    @Override
    public void marcher() {
        System.out.println("le zombi marche.");
    }

    @Override
    public void courir() {
        System.out.println("le zombi court.");
    }

    @Override
    public void seBattre() {
        System.out.println("Le zombi se bat.");
    }

    @Override
    public void parler(String phrase) {

    }

    public void parler(String phrase, boolean hauteVoix){
        System.out.println(phrase);
    }
}
