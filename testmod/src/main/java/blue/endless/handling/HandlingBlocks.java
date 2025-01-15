package blue.endless.handling;

import java.util.ArrayList;

import blue.endless.handling.block.SideHopper;
import blue.endless.handling.block.entity.SideHopperEntity;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class HandlingBlocks {
	
	public static final SideHopper SIDE_HOPPER = new SideHopper(Block.Settings.copy(Blocks.IRON_BLOCK));
	public static final BlockEntityType<SideHopperEntity> ENTITYTYPE_SIDE_HOPPER = BlockEntityType.Builder.create(SideHopperEntity::new, SIDE_HOPPER).build();
	
	
	
	
	
	public static ArrayList<Block> creativeBlocks = new ArrayList<>();
	public static ItemGroup ITEM_GROUP = FabricItemGroup.builder()
			.displayName(Text.literal("Handling"))
			.entries((ctx, entries) -> {
				for(Block b : creativeBlocks) {
					entries.add(b);
				}
			})
			.icon(() -> new ItemStack(SIDE_HOPPER))
			.build();
	
	
	public static void init() {
		blockWithEntity("side_hopper", SIDE_HOPPER, ENTITYTYPE_SIDE_HOPPER);
	}
	
	public static void blockWithEntity(String id, Block block, BlockEntityType<?> eType) {
		Identifier identifier = Identifier.of("handling", id);
		Registry.register(Registries.BLOCK, identifier, block);
		Registry.register(Registries.BLOCK_ENTITY_TYPE, identifier, eType);
		Registry.register(Registries.ITEM, identifier, new BlockItem(block, new Item.Settings()));
		
	}
}
