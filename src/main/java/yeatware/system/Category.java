package yeatware.system;

public enum Category {
    COMBAT("Combat"),
    MOVEMENT("Movement"),
    RENDER("Render"),
    PLAYER("Player"),
    WORLD("World"),
    CLIENT("Client");

    public final String name;

    Category(String name) {
        this.name = name;
    }
}

