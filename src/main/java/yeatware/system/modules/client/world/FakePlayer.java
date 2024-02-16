package yeatware.system.modules.client.world;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.network.OtherClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;
import yeatware.system.Category;
import yeatware.system.Module;

import java.util.Objects;
import java.util.UUID;

public class FakePlayer extends Module {
    public FakePlayer() {
        super("FakePlayer", Category.PLAYER);
    }

    FakePlayerEntity fakePlayerEntity;

    @Override
    public void onActivate() {
        fakePlayerEntity = new FakePlayerEntity();

        mc.world.addEntity(fakePlayerEntity);
        super.onActivate();
    }

    @Override
    public void onDeactivate() {
        mc.world.removeEntity(fakePlayerEntity.getId(), Entity.RemovalReason.KILLED);
        super.onDeactivate();
    }

    private class FakePlayerEntity extends OtherClientPlayerEntity {

        public FakePlayerEntity() {
            super(Objects.requireNonNull(mc.world), new GameProfile(UUID.fromString("f5d7971d-54c5-4b37-aca9-f7b14511b8a6"), "SplashAni_"));
        }

        @Override
        public Text getDisplayName() {
            return Text.of("SplashAni_");
        }
    }
}
