package yeatware.system.modules.render;

import meteordevelopment.orbit.EventHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import yeatware.event.events.PacketReceiveEvent;
import yeatware.system.Category;
import yeatware.system.Module;

public class DeathEffect extends Module {
    public DeathEffect() {
        super("DeathEffect", Category.RENDER);
    }

    @EventHandler
    public void onPacket(PacketReceiveEvent event) {
        if (event.getPacket() instanceof EntityStatusS2CPacket packet && packet.getStatus() == 3) {

            Entity entity = packet.getEntity(mc.world);
            LightningEntity lightningEntity = new LightningEntity(EntityType.LIGHTNING_BOLT, mc.world);

            lightningEntity.updatePosition(entity != null ? entity.getX() : 0, entity.getY(), entity.getZ());
            lightningEntity.refreshPositionAfterTeleport(entity.getX(), entity.getY(), entity.getZ());

            if (mc.world != null) {
                mc.world.addEntity(lightningEntity);
            }
        }
    }

}
