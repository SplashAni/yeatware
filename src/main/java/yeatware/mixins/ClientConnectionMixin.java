package yeatware.mixins;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.NetworkSide;
import net.minecraft.network.PacketCallbacks;
import net.minecraft.network.packet.Packet;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yeatware.Main;
import yeatware.event.events.PacketReceiveEvent;
import yeatware.event.events.PacketSendEvent;

@Mixin(ClientConnection.class)
public class ClientConnectionMixin {


    @Shadow
    private Channel channel;

    @Shadow
    @Final
    private NetworkSide side;

    @Inject(method = "channelRead0*", at = @At("HEAD"), cancellable = true)
    public void channelRead0(ChannelHandlerContext chc, Packet<?> packet, CallbackInfo ci) {
        if (!channel.isOpen() || packet == null) return;

        if (Main.BUS.post(new PacketReceiveEvent(packet)).isCancelled()) ci.cancel();
    }

    @Inject(method = "sendImmediately", at = @At("HEAD"), cancellable = true)
    private void sendImmediately(Packet<?> packet, PacketCallbacks callbacks, boolean flush, CallbackInfo ci) {
        if (side != NetworkSide.CLIENTBOUND) return;

        if (Main.BUS.post(new PacketSendEvent(packet)).isCancelled()) ci.cancel();

    }


}
