package mod.azure.doom.client.models.armor;

import mod.azure.azurelib.model.GeoModel;
import mod.azure.doom.MCDoom;
import mod.azure.doom.items.armor.ClassicRedDoomArmor;
import net.minecraft.resources.ResourceLocation;

public class ClassicRedModel extends GeoModel<ClassicRedDoomArmor> {
	@Override
	public ResourceLocation getModelResource(ClassicRedDoomArmor object) {
		return MCDoom.modResource("geo/classicarmor.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(ClassicRedDoomArmor object) {
		return MCDoom.modResource("textures/models/armor/classic_red_armor_layer_1.png");
	}

	@Override
	public ResourceLocation getAnimationResource(ClassicRedDoomArmor animatable) {
		return MCDoom.modResource("animations/armor_animation.json");
	}
}