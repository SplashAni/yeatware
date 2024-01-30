package yeatware.gui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import yeatware.system.Category;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static yeatware.Main.mc;

public class GuiScreen extends Screen {
    List<Frame> frames;
    int[] box;

    public GuiScreen() {
        super(Text.of("GuiScreen"));
        frames = new ArrayList<>();

        int frameWidth = 50;
        int totalFramesWidth = Category.values().length * frameWidth;

        int startX = (mc.getWindow().getScaledWidth() - totalFramesWidth) / 2;

        for (Category category : Category.values()) {
            frames.add(new Frame(category, frameWidth, 15, startX, 20));
            startX += frameWidth;
        }

        /*
         * 0 x
         * 1 y
         * 2 width
         * 3 height
         * */
        box = new int[]{(mc.getWindow().getScaledWidth() - totalFramesWidth) / 2, 20 + 15, totalFramesWidth, 100};
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {

        frames.forEach(frame -> frame.render(context, mouseX, mouseY, delta));

        int outlineColor = new Color(61, 60, 60, 255).getRGB();


        // genius coding below <3

        context.fill(box[0], box[1], box[0] + box[2], box[1] + box[3], new Color(24, 24, 24,255).getRGB());

        context.fill(box[0] - 1, box[1] + box[3], box[0] + box[2] + 1, box[1] + box[3] + 1, outlineColor);
        context.fill(box[0] - 1, box[1], box[0], box[1] + box[3], outlineColor);
        context.fill(box[0] + box[2], box[1], box[0] + box[2] + 1, box[1] + box[3], outlineColor);

        frames.forEach(frame -> {
            int lineY = frame.y + frame.height;
            context.fill(frame.x, lineY, frame.x + frame.width, lineY + 1, new Color(45, 79, 147, 255).getRGB());
        });

        super.render(context, mouseX, mouseY, delta);
    }

}
