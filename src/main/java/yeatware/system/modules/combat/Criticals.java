package yeatware.system.modules.combat;

import meteordevelopment.orbit.EventHandler;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import yeatware.event.events.PacketSendEvent;
import yeatware.system.Category;
import yeatware.system.Module;
import yeatware.ui.settings.ModeSetting;

import static net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket.InteractType.ATTACK;


public class Criticals extends Module {
    ModeSetting<Mode> mode = new ModeSetting<>("Mode", Mode.Jump);

    public Criticals() {
        super("Criticals", Category.COMBAT);
        addSettings(mode);
    }

    @EventHandler
    public void onPacket(PacketSendEvent event) {
        if (!(event.getPacket() instanceof PlayerInteractEntityC2SPacket packet)) return;

        if (packet.type.getType() == ATTACK) {

            switch (mode.get()) {
                case Jump -> {
                    if (!mc.player.isOnGround()) return; // air jumping?
                    mc.player.jump();
                }

                case Packet -> {

                    mc.getNetworkHandler().sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(
                            mc.player.getX(), mc.player.getY() + 1, mc.player.getZ(), true
                    ));

                    mc.getNetworkHandler().sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(
                            mc.player.getX(), mc.player.getY(), mc.player.getZ(), true
                    ));

                }
            }
        }
    }

    public enum Mode {
        Jump,
        Packet
    }
}
