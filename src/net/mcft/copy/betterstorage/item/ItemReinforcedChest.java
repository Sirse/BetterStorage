package net.mcft.copy.betterstorage.item;

import net.mcft.copy.betterstorage.block.ContainerMaterial;
import net.mcft.copy.betterstorage.misc.Constants;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class ItemReinforcedChest extends ItemBlock {
	
	public ItemReinforcedChest(int id) {
		super(id);
		setHasSubtypes(true);
		setMaxDamage(0);
	}
	
	@Override
	public int getMetadata(int damage) { return damage; }
	
	@Override
	public String getItemDisplayName(ItemStack stack) {
		String name = StatCollector.translateToLocal(getUnlocalizedName(stack) + ".name.full");
		String material = "material." + Constants.modId + "." + ContainerMaterial.getMaterial(stack).name;
		String materialName = StatCollector.translateToLocal(material);
		return name.replace("%MATERIAL%", materialName);
	}
	
}
