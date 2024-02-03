package yeatware.event.events;

import net.minecraft.network.packet.Packet;
import yeatware.event.Event;

public class PacketReceiveEvent extends Event {
    Packet<?> packet;

    public PacketReceiveEvent(Packet<?> packet) {
        this.packet = packet;
    }

    public Packet<?> getPacket() {
        return packet;
    }
}
