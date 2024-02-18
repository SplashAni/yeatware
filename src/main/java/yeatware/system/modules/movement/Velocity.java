package yeatware.system.modules.movement;

import meteordevelopment.orbit.EventHandler;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;
import yeatware.event.events.PacketReceiveEvent;
import yeatware.system.Category;
import yeatware.system.Module;

public class Velocity extends Module {

    public Velocity() {
        super("Velocity", Category.MOVEMENT);
    }


    @EventHandler
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacket() instanceof EntityVelocityUpdateS2CPacket || event.getPacket() instanceof ExplosionS2CPacket){
            event.cancel();
        }
    }
}