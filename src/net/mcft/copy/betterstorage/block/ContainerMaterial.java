package net.mcft.copy.betterstorage.block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.mcft.copy.betterstorage.misc.BetterStorageResource;
import net.mcft.copy.betterstorage.utils.StackUtils;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ContainerMaterial {
	
	public static final String TAG_NAME = "Material";
	
	private static Map<String, ContainerMaterial> materialMap = new HashMap<String, ContainerMaterial>();
	private static Map<Integer, ContainerMaterial> materialMapOld = new HashMap<Integer, ContainerMaterial>();
	private static List<ContainerMaterial> materials = new ArrayList<ContainerMaterial>();
	
	// Vanilla materials
	public static ContainerMaterial iron    = new ContainerMaterial(0, "iron",    Item.ingotIron, Block.blockIron);
	public static ContainerMaterial gold    = new ContainerMaterial(1, "gold",    Item.ingotGold, Block.blockGold);
	public static ContainerMaterial diamond = new ContainerMaterial(2, "diamond", Item.diamond,   Block.blockDiamond);
	public static ContainerMaterial emerald = new ContainerMaterial(3, "emerald", Item.emerald,   Block.blockEmerald);
	
	// Mod materials
	public static ContainerMaterial copper = new ContainerMaterial(5, "copper", "ingotCopper", "blockCopper");
	public static ContainerMaterial tin    = new ContainerMaterial(6, "tin",    "ingotTin",    "blockTin");
	public static ContainerMaterial silver = new ContainerMaterial(7, "silver", "ingotSilver", "blockSilver");
	public static ContainerMaterial zinc   = new ContainerMaterial(8, "zinc",   "ingotZinc",   "blockZinc");
	
	public static List<ContainerMaterial> getMaterials() { return materials; }
	
	public static ContainerMaterial get(String name) { return materialMap.get(name); }
	public static ContainerMaterial get(int id) { return materialMapOld.get(id); }
	
	
	public final String name;
	
	private final Object ingot;
	private final Object block;
	
	private ContainerMaterial(String name, Object ingot, Object block) {
		this.name = name;
		this.ingot = ingot;
		this.block = block;
		materialMap.put(name, this);
		materials.add(this);
	}
	private ContainerMaterial(String name) { this(name, null, null); }
	
	private ContainerMaterial(int id, String name, Object ingot, Object block) {
		this(name, ingot, block);
		materialMapOld.put(id, this);
	}
	
	public ShapedOreRecipe getChestRecipe(Block result) {
		if ((ingot == null) || (block == null)) return null;
		return new ShapedOreRecipe(setMaterial(new ItemStack(result)),
				"o#o",
				"#C#",
				"oOo", 'C', Block.chest,
				       '#', "logWood",
				       'o', ingot,
				       'O', block);
	}
	
	public ResourceLocation getResource(boolean large) {
		return new BetterStorageResource("textures/models/chest" + (large ? "_large/" : "/") + name + ".png");
	}
	
	public ItemStack setMaterial(ItemStack stack) {
		StackUtils.set(stack, name, TAG_NAME);
		return stack;
	}
	
	/** Gets the material of the stack, either using the new method,
	 *  the old ID lookup or if everything fails, it'll default to iron. */
	public static ContainerMaterial getMaterial(ItemStack stack) {
		String name = StackUtils.get(stack, (String)null, TAG_NAME);
		ContainerMaterial material = ((name != null) ? get(name) : get(stack.getItemDamage()));
		return ((material != null) ? material : iron);
	}
	
}
