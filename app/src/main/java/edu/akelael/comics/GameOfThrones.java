package edu.akelael.comics;

import java.util.ArrayList;

public class GameOfThrones {
    String lema = "You know nothing Jon Snow";

    public static class Characters {
        ArrayList<Character> characters = new ArrayList<>();

        public static class Character {
            String name;
            String role;
            int remainingEpisodesToDie;
        }
    }
}
