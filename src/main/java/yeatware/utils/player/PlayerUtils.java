package yeatware.utils.player;

import net.minecraft.network.packet.c2s.play.HandSwingC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import yeatware.system.ModuleManager;
import yeatware.system.modules.client.InteractionModule;
import yeatware.utils.constants.SwingMode;
import yeatware.utils.constants.TimeMode;

import static yeatware.Main.mc;

public class PlayerUtils {

    public static void swingHand(SwingMode mode, Hand hand) {
        switch (mode) {
            case Packet -> mc.getNetworkHandler().sendPacket(new HandSwingC2SPacket(hand));
            case Client -> mc.player.swingHand(hand);
        }
    }

    public static boolean preSwing() {
        return ModuleManager.get().getModule(InteractionModule.class).swing.get() == TimeMode.Pre;
    }

}