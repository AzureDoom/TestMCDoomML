package mod.azure.doom.client.models.armor;

import mod.azure.azurelib.model.GeoModel;
import mod.azure.doom.MCDoom;
import mod.azure.doom.items.armor.HotrodDoomArmor;
import net.minecraft.resources.ResourceLocation;

public class HotrodModel extends GeoModel<HotrodDoomArmor> {
	@Override
	public ResourceLocation getModelResource(HotrodDoomArmor object) {
		return MCDoom.modResource("geo/doomarmor.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(HotrodDoomArmor object) {
		return MCDoom.modResource("textures/models/armor/hotrod_armor_layer_1.png");
	}

	@Override
	public ResourceLocation getAnimationResource(HotrodDoomArmor animatable) {
		return MCDoom.modResource("animations/armor_animation.json");
	}
}