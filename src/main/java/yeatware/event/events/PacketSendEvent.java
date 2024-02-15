package yeatware.event.events;

import net.minecraft.network.packet.Packet;
import yeatware.event.Event;

public class PacketSendEvent extends Event {
    Packet<?> packet;

    public PacketSendEvent(Packet<?> packet) {
        this.packet = packet;
    }

    public Packet<?> getPacket() {
        return packet;
    }
}
