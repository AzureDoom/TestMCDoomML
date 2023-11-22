package mod.azure.doom.client.render;

import mod.azure.azurelib.renderer.GeoEntityRenderer;
import mod.azure.doom.client.models.MechaZombieModel;
import mod.azure.doom.entities.tierfodder.MechaZombieEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class MechaZombieRender extends GeoEntityRenderer<MechaZombieEntity> {

    public MechaZombieRender(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new MechaZombieModel());
    }

    @Override
    protected float getDeathMaxRotation(MechaZombieEntity entityLivingBaseIn) {
        return 0.0F;
    }

}