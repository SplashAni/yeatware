package yeatware.system;

import net.minecraft.client.MinecraftClient;
import yeatware.Main;

public abstract class Module {
    public MinecraftClient mc = Main.mc;
    String name;
    String desc;
    Category category;
    boolean isActive;

    public Module(String name, String desc, Category category) {
        this.name = name;
        this.desc = desc;
        this.category = category;
    }

    public Module(String name, Category category) {
        this.name = name;
        this.desc = "";
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void onTick() {

    }

    public void onActivate() {
        Main.BUS.subscribe(this);
    }

    public void onDeactivate() {
        Main.BUS.unsubscribe(this);
    }
}
