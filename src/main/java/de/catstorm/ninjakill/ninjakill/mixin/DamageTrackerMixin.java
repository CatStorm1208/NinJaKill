package de.catstorm.ninjakill.ninjakill.mixin;

import de.catstorm.ninjakill.ninjakill.NinjaKill;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageRecord;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTracker;
import net.minecraft.entity.mob.PillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import java.util.List;
import java.util.Objects;

@Mixin(DamageTracker.class)
public class DamageTrackerMixin {
    @Shadow @Final private List<DamageRecord> recentDamage;
    @Shadow @Final private LivingEntity entity;

    @SuppressWarnings("SequencedCollectionMethodCanBeUsed") //IDEK why this is a warning
    @Inject(at = @At("HEAD"), method = "getDeathMessage", cancellable = true)
    public void getDeathMessage(CallbackInfoReturnable<Text> cir) {
        DamageRecord damageRecord = (DamageRecord) recentDamage.get(recentDamage.size() - 1);
        DamageSource damageSource = damageRecord.damageSource();
        //TODO: change Pillager to Player
        if ((Objects.requireNonNull(damageSource.getAttacker()).isInvisible() && damageSource.getAttacker() instanceof PlayerEntity) ||
            (entity.isInvisible() && entity instanceof PlayerEntity)) {
            cir.setReturnValue(Text.of("Someone was killed!"));
            NinjaKill.LOGGER.info(entity.getNameForScoreboard() + "'s death caused by " + damageSource.getAttacker().getNameForScoreboard() +
                " was hidden by NinjaKill!");
        }
    }
}