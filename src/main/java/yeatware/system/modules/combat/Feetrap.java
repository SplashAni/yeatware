package yeatware.system.modules.combat;

import me.x150.renderer.render.Renderer3d;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import yeatware.event.events.KeyEvent;
import yeatware.system.Category;
import yeatware.system.Module;
import yeatware.ui.settings.BooleanSetting;
import yeatware.ui.settings.ModeSetting;
import yeatware.ui.settings.NumberSetting;
import yeatware.utils.constants.modes.RotationMode;
import yeatware.utils.constants.modes.SwingMode;
import yeatware.utils.player.ItemResult;
import yeatware.utils.world.BlockUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Feetrap extends Module {
    NumberSetting delay = new NumberSetting("Delay", 4, 0, 30);
    ModeSetting<SwingMode> swingMode = new ModeSetting<>("Swing Mode", SwingMode.Client);
    ModeSetting<RotationMode> rotationMode = new ModeSetting<>("RotationMode", RotationMode.None);
    BooleanSetting center = new BooleanSetting("Center", true);
    BooleanSetting jumpToggle = new BooleanSetting("Jump Toggle", false);

    public Feetrap() {
        super("Feetrap", Category.COMBAT);
        addSettings(delay, center, swingMode, rotationMode, jumpToggle);
    }

    List<BlockPos> poses = new ArrayList<>();
    ItemResult item = new ItemResult(Items.OBSIDIAN);
    int ticks;

    @Override
    public void onActivate() {
        if (center.get()) {
            Vec3d center = Vec3d.ofBottomCenter(mc.player.getBlockPos());
            mc.player.setPos(center.getX(), center.getY(), center.getZ());
            mc.getNetworkHandler().sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(center.getX(), center.getY(), center.getZ(), mc.player.isOnGround()));
        }
        super.onActivate();
    }


    @EventHandler
    public void onKey(KeyEvent event) {
        if (mc.options.jumpKey.matchesKey(event.getKey(), event.getScanCode()) && jumpToggle.get()) toggle();
    }

    @Override
    public void onTick() {

        if (!item.isHotbar()) return;


        if (ticks > 0) {
            ticks--;
            return;
        }

        calculate();

        if (poses.isEmpty()) {
            ticks = delay.get();
            return;
        }

        BlockPos pos = poses.get(0);

        if (BlockUtils.place(pos, item, swingMode.get(), rotationMode.get())) {
            poses.remove(pos);
        }
        ticks = delay.get();

        super.onTick();
    }


    public void calculate() {
        poses.clear();
        for (Direction direction : Direction.values()) {
            if (direction == Direction.UP) continue;
            BlockPos offset = mc.player.getBlockPos().offset(direction);

            if (!BlockUtils.canPlace(offset)) continue;
            poses.add(offset);
        }
    }

    @Override
    public void onRender(MatrixStack matrcies, float tickDelta) {
        if (poses.isEmpty()) return;
        Renderer3d.renderThroughWalls();
        poses.forEach(blockPos -> Renderer3d.renderEdged(matrcies, new Color(47, 85, 157, 161),
                new Color(93, 136, 218, 208), Vec3d.of(blockPos), new Vec3d(1, 1, 1)));
        super.onRender(matrcies, tickDelta);
    }

}
