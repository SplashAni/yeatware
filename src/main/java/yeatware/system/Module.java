package yeatware.system;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import yeatware.Main;
import yeatware.ui.settings.KeybindSetting;
import yeatware.ui.settings.Setting;

import java.util.ArrayList;
import java.util.List;

public abstract class Module {
    public static MinecraftClient mc = Main.mc;
    List<Setting> settings = new ArrayList<>();
    String name;
    String desc;
    Category category;
    boolean isActive;
    int key;

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

    public void addSettings(Setting... settings) {
        this.settings.addAll(List.of(settings));
    }

    public List<Setting> getSettings() {
        List<Setting> settings = new ArrayList<>(this.settings);
        settings.add(new KeybindSetting("Bind", this, key));
        return settings;
    }


    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void toggle() {
        isActive = !isActive;
        if (isActive()) onActivate();
        else onDeactivate();
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

    public void onRender(MatrixStack matrcies, float tickDelta) {

    }

    public void onActivate() {
        Main.BUS.subscribe(this);
    }

    public void onDeactivate() {
        Main.BUS.unsubscribe(this);
    }
}
