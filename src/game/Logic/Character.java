package game.Logic;

public class Character {

    private String name;
    private int speech;
    private int cunning;
    private int mentality;

    public Character(String name, int speech, int cunning, int mentality) {
        this.name = name;
        this.speech = speech;
        this.cunning = cunning;
        this.mentality = mentality;
    }

    public String getName() {
        return name;
    }

    public int getSpeech() {
        return speech;
    }

    public int getCunning() {
        return cunning;
    }

    public int getMentality() {
        return mentality;
    }

}
