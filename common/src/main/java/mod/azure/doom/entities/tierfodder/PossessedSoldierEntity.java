package mod.azure.doom.entities.tierfodder;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import mod.azure.azurelib.core.animatable.instance.AnimatableInstanceCache;
import mod.azure.azurelib.core.animation.AnimatableManager.ControllerRegistrar;
import mod.azure.azurelib.core.animation.AnimationController;
import mod.azure.azurelib.core.object.PlayState;
import mod.azure.azurelib.util.AzureLibUtil;
import mod.azure.doom.MCDoom;
import mod.azure.doom.entities.DemonEntity;
import mod.azure.doom.entities.DoomAnimationsDefault;
import mod.azure.doom.entities.ai.DemonFlyControl;
import mod.azure.doom.entities.task.DemonMeleeAttack;
import mod.azure.doom.entities.task.DemonProjectileAttack;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.LookAtTargetSink;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.tslat.smartbrainlib.api.SmartBrainOwner;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.SmartBrainProvider;
import net.tslat.smartbrainlib.api.core.behaviour.FirstApplicableBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.OneRandomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.look.LookAtTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.Idle;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.FloatToSurfaceOfFluid;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.MoveToWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.StayWithinDistanceOfAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetRandomWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetWalkTargetToAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.SetPlayerLookTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.SetRandomLookTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.TargetOrRetaliate;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.custom.UnreachableTargetSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.HurtBySensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyLivingEntitySensor;

import java.util.List;

public class PossessedSoldierEntity extends DemonEntity implements SmartBrainOwner<PossessedSoldierEntity> {

	private final AnimatableInstanceCache cache = AzureLibUtil.createInstanceCache(this);
	public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(PossessedSoldierEntity.class, EntityDataSerializers.INT);
	public int flameTimer;

	public PossessedSoldierEntity(EntityType<PossessedSoldierEntity> entityType, Level worldIn) {
		super(entityType, worldIn);
		moveControl = new DemonFlyControl(this);
	}

	@Override
	public void registerControllers(ControllerRegistrar controllers) {
		var isDead = this.dead || this.getHealth() < 0.01 || this.isDeadOrDying();
		controllers.add(new AnimationController<>(this, "livingController", 0, event -> {
			if (event.isMoving() && !isDead && !this.swinging)
				return event.setAndContinue(DoomAnimationsDefault.WALKING);
			if (!onGround() && !onGround() && getVariant() == 2 && !isDead)
				return event.setAndContinue(DoomAnimationsDefault.FLYING);
			return event.setAndContinue(isDead ? DoomAnimationsDefault.DEATH : DoomAnimationsDefault.IDLE);
		}).setSoundKeyframeHandler(event -> {
			if (event.getKeyframeData().getSound().matches("walk"))
				if (level().isClientSide())
					level().playLocalSound(this.getX(), this.getY(), this.getZ(), mod.azure.doom.platform.Services.SOUNDS_HELPER.getPINKY_STEP(), SoundSource.HOSTILE, 0.25F, 1.0F, false);
			if (event.getKeyframeData().getSound().matches("attack"))
				if (level().isClientSide())
					level().playLocalSound(this.getX(), this.getY(), this.getZ(), mod.azure.doom.platform.Services.SOUNDS_HELPER.getPISTOL_HIT(), SoundSource.HOSTILE, 0.25F, 1.0F, false);
		})).add(new AnimationController<>(this, "attackController", 0, event -> {
			return PlayState.STOP;
		}).triggerableAnim("ranged", DoomAnimationsDefault.ATTACKING).triggerableAnim("melee", DoomAnimationsDefault.MELEE));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return cache;
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		entityData.define(VARIANT, 0);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		setVariant(tag.getInt("Variant"));
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putInt("Variant", getVariant());
	}

	public int getVariant() {
		return Mth.clamp(entityData.get(VARIANT), 1, 3);
	}

	public void setVariant(int variant) {
		entityData.set(VARIANT, variant);
	}

	public int getVariants() {
		return 3;
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, SpawnGroupData spawnDataIn, CompoundTag dataTag) {
		spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
		final var variant = getRandom().nextInt(0, 4);
		setVariant(variant);
		return spawnDataIn;
	}

	@Override
	protected void customServerAiStep() {
		tickBrain(this);
		super.customServerAiStep();
	}

	@Override
	protected Brain.Provider<?> brainProvider() {
		return new SmartBrainProvider<>(this);
	}

	@Override
	public List<ExtendedSensor<PossessedSoldierEntity>> getSensors() {
		return ObjectArrayList.of(new NearbyLivingEntitySensor<PossessedSoldierEntity>().setPredicate((target, entity) -> target.isAlive() && entity.hasLineOfSight(target) && !(target instanceof DemonEntity)), new HurtBySensor<>(), new UnreachableTargetSensor<PossessedSoldierEntity>());
	}

	@Override
	public BrainActivityGroup<PossessedSoldierEntity> getCoreTasks() {
		return BrainActivityGroup.coreTasks(new LookAtTarget<>(), new LookAtTargetSink(40, 300), new FloatToSurfaceOfFluid<>(), new StayWithinDistanceOfAttackTarget<>().speedMod(0.25F), new MoveToWalkTarget<>());
	}

	@Override
	public BrainActivityGroup<PossessedSoldierEntity> getIdleTasks() {
		return BrainActivityGroup.idleTasks(new FirstApplicableBehaviour<PossessedSoldierEntity>(new TargetOrRetaliate<>().alertAlliesWhen((mob, entity) -> this.isAggressive()), new SetPlayerLookTarget<>().stopIf(target -> !target.isAlive() || target instanceof Player && ((Player) target).isCreative()), new SetRandomLookTarget<>()), new OneRandomBehaviour<>(new SetRandomWalkTarget<>().setRadius(20).speedModifier(1.0f), new Idle<>().runFor(entity -> entity.getRandom().nextInt(300, 600))));
	}

	@Override
	public BrainActivityGroup<PossessedSoldierEntity> getFightTasks() {
		return BrainActivityGroup.fightTasks(new InvalidateAttackTarget<>().invalidateIf((target, entity) -> !target.isAlive() || !entity.hasLineOfSight(target)), new SetWalkTargetToAttackTarget<>().speedMod((owner, target) -> 1.05F), new DemonProjectileAttack<>(7).attackInterval(mob -> 80).attackDamage(MCDoom.config.possessed_soldier_ranged_damage), new DemonMeleeAttack<>(5).startCondition(entity -> this.getVariant() != 3));
	}

	@Override
	public void travel(Vec3 movementInput) {
		if (isAggressive() && getVariant() == 2) {
			if (isInWater()) {
				moveRelative(0.02F, movementInput);
				move(MoverType.SELF, getDeltaMovement());
				this.setDeltaMovement(getDeltaMovement().scale(0.8F));
			} else if (isInLava()) {
				moveRelative(0.02F, movementInput);
				move(MoverType.SELF, getDeltaMovement());
				this.setDeltaMovement(getDeltaMovement().scale(0.5D));
			} else {
				final var ground = BlockPos.containing(this.getX(), this.getY() - 1.0D, this.getZ());
				var f = 0.91F;
				if (onGround())
					f = level().getBlockState(ground).getBlock().getFriction() * 0.91F;
				final var f1 = 0.16277137F / (f * f * f);
				f = 0.91F;
				if (onGround())
					f = level().getBlockState(ground).getBlock().getFriction() * 0.91F;
				moveRelative(onGround() ? 0.1F * f1 : 0.02F, movementInput);
				move(MoverType.SELF, getDeltaMovement());
				this.setDeltaMovement(getDeltaMovement().scale(f));
			}
		} else {
			super.travel(movementInput);
		}
	}

	@Override
	protected PathNavigation createNavigation(Level worldIn) {
		final var flyingpathnavigator = new FlyingPathNavigation(this, worldIn);
		flyingpathnavigator.setCanOpenDoors(false);
		flyingpathnavigator.setCanFloat(true);
		flyingpathnavigator.setCanPassDoors(true);
		return flyingpathnavigator;
	}

	public static AttributeSupplier.Builder createMobAttributes() {
		return LivingEntity.createLivingAttributes().add(Attributes.FOLLOW_RANGE, 40.0D).add(Attributes.MAX_HEALTH, MCDoom.config.possessed_soldier_health).add(Attributes.ATTACK_DAMAGE, 2.0D).add(Attributes.MOVEMENT_SPEED, 0.25D).add(Attributes.ATTACK_KNOCKBACK, 0.0D);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return 1.74F;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return mod.azure.doom.platform.Services.SOUNDS_HELPER.getPSOLDIER_HURT();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return mod.azure.doom.platform.Services.SOUNDS_HELPER.getPSOLDIER_DEATH();
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return 7;
	}

	@Override
	public void aiStep() {
		super.aiStep();
		flameTimer = (flameTimer + 1) % 2;
	}

	public int getFlameTimer() {
		return flameTimer;
	}

}