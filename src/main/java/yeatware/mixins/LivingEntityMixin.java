package yeatware.mixins;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LimbAnimator;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import yeatware.system.ModuleManager;
import yeatware.system.modules.render.NoLimbInterp;

import static yeatware.Main.mc;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    // First 1.20 nolimbinterp public? BY SPLASHANI ong

    @Redirect(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LimbAnimator;setSpeed(F)V"))
    public void damage(LimbAnimator instance, float speed) {
        if (stop()) instance.setSpeed(0f);
        else instance.setSpeed(speed);
    }

    @Redirect(method = "onDamaged", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LimbAnimator;setSpeed(F)V"))
    public void onDamaged(LimbAnimator instance, float speed) {
        if (stop()) instance.setSpeed(0f);
        else instance.setSpeed(speed);
    }

    @Redirect(method = "updateLimbs(F)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LimbAnimator;updateLimbs(FF)V"))
    public void updateLimbs(LimbAnimator instance, float speed, float multiplier) {
        if (stop()) instance.updateLimbs(0, 0);
        else instance.updateLimbs(speed, multiplier);
    }

    @Unique
    public boolean stop() {
        Entity entity = this;

        NoLimbInterp noLimbInterp = (NoLimbInterp) ModuleManager.get().getModule(NoLimbInterp.class);

        if (noLimbInterp.isActive()) return false;
        if (noLimbInterp.ignoreSelf.get() && entity == mc.player) return true;

        return entity instanceof PlayerEntity;
    }

}