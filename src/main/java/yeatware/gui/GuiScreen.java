package yeatware.gui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import yeatware.Main;
import yeatware.system.Category;
import yeatware.system.Module;
import yeatware.system.ModuleManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static yeatware.Main.mc;

public class GuiScreen extends Screen {
    public Category currentCategory;
    private final Main main;
    List<Frame> frames;
    List<Button> buttons;
    int[] box;


    public GuiScreen(Category currentCategory, Main main) {
        super(Text.of("GuiScreen"));
        this.main = main;

        frames = new ArrayList<>();
        buttons = new ArrayList<>();


        int frameWidth = 80;
        int totalFramesWidth = Category.values().length * frameWidth;

        int startX = (mc.getWindow().getScaledWidth() - totalFramesWidth) / 2;

        for (Category category : Category.values()) {
            frames.add(new Frame(this, category, frameWidth, 15, startX, 20));
            startX += frameWidth;
        }

        /* 0 x
         * 1 y
         * 2 width
         * 3 height */

        box = new int[]{(mc.getWindow().getScaledWidth() - totalFramesWidth) / 2, 20 + 15, totalFramesWidth, 150};

        setCurrentCategory(currentCategory);
    }


    @Override
    public void close() {
        main.setCategory(currentCategory);
        super.close();
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        int outlineColor = new Color(61, 60, 60, 255).getRGB();

        context.fill(box[0], box[1], box[0] + box[2], box[1] + box[3], new Color(24, 24, 24, 255).getRGB());

        context.fill(box[0] - 1, box[1] + box[3], box[0] + box[2] + 1, box[1] + box[3] + 1, outlineColor);
        context.fill(box[0] - 1, box[1], box[0], box[1] + box[3], outlineColor);
        context.fill(box[0] + box[2], box[1], box[0] + box[2] + 1, box[1] + box[3], outlineColor);

        frames.forEach(frame -> {
            int lineY = frame.y + frame.height;
            context.fill(frame.x, lineY, frame.x + frame.width, lineY + 2, new Color(45, 79, 147, 255).getRGB());
        });


        // ik im gonna forget this ok so , this renders the line that goes down, and the background next to the line
        int lineOffset = 65;

        int horizontalX = box[0] + lineOffset + 3;
        context.fill(horizontalX + 1, box[1] + 2, box[0] + box[2], box[1] + box[3], new Color(44, 42, 42, 255).getRGB());

        int verticalX = box[0] + lineOffset + 3;
        context.fill(verticalX, box[1], verticalX + 2, box[1] + box[3], new Color(45, 79, 147, 255).getRGB());


        frames.forEach(frame -> frame.render(context, mouseX, mouseY, delta));

        if (buttons.isEmpty()) return;
        buttons.forEach(button -> button.render(context, mouseX, mouseY, delta));

        super.render(context, mouseX, mouseY, delta);
    }


    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        frames.forEach(frame -> frame.mouseReleased(mouseX, mouseY, button));

        buttons.forEach(button1 -> button1.mouseReleased(mouseX, mouseY, button));


        return super.mouseReleased(mouseX, mouseY, button);
    }


    public void setCurrentCategory(Category category) {
        currentCategory = category;

        buttons.clear();

        int offset = 0;

        for (Module module : ModuleManager.get().getCategoryModules(category)) {
            buttons.add(new Button(module, box[0] - 2, box[1] + offset + 2));
            offset += mc.textRenderer.fontHeight + 2;
        }
    }


    public boolean isSelected(Category category) {
        return currentCategory == category;
    }
}