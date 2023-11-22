package mod.azure.doom.items.weapons;

import mod.azure.azurelib.Keybindings;
import mod.azure.doom.helper.CommonUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import java.util.List;

public abstract class BaseSwordItem extends SwordItem {
    public BaseSwordItem(Tier $$0, int $$1, float $$2, Properties $$3) {
        super($$0, $$1, $$2, $$3);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(Component.translatable("Ammo: " + (stack.getMaxDamage() - stack.getDamageValue() - 1) + " / " + (stack.getMaxDamage() - 1)).withStyle(ChatFormatting.ITALIC));
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }
}
