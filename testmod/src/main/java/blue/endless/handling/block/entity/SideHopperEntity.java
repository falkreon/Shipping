package blue.endless.handling.block.entity;

import blue.endless.handling.HandlingBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SideHopperEntity extends BlockEntity {

	public SideHopperEntity(BlockPos pos, BlockState state) {
		super(HandlingBlocks.ENTITYTYPE_SIDE_HOPPER, pos, state);
	}
	
	public static void tick(World world, BlockPos pos, BlockState state, SideHopperEntity entity) {
		
	}
	
}
