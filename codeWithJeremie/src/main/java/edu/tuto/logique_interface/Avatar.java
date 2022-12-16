package edu.tuto.logique_interface;

public interface Avatar {
    void marcher();
    void courir();
    void seBattre();
    void parler(String phrase);
    default void faireDePetitPas(){
        System.out.println("L'avatar fait de petits pas.");
    }
    default void parler(String phrase, boolean hauteVoix){
        if (hauteVoix){
            System.out.println(phrase);
        }
    }
}
