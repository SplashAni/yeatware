package yeatware.utils.constants.modes;

import net.minecraft.network.packet.c2s.play.HandSwingC2SPacket;
import net.minecraft.util.Hand;

import static yeatware.Main.mc;

public enum SwingMode {
    Packet,
    Client,
    None;
}
