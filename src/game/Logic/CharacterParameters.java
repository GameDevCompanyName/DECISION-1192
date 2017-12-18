package game.Logic;

public enum CharacterParameters {

    SPEECH("КРСНРЧ", 10), CUNNING("ХТРСТЬ", 10), MENTALITY("МЕНТЛН", 10);

    CharacterParameters(String name, int defaultValue) {
        this.name = name;
        this.defaultValue = defaultValue;
    }

    String name;
    int defaultValue;

    public String getName() {
        return name;
    }
}
