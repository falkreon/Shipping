package blue.endless.handling.block;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.MapCodec;

import blue.endless.handling.HandlingBlocks;
import blue.endless.handling.block.entity.SideHopperEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class SideHopper extends BlockWithEntity {
	public static final DirectionProperty DIRECTION = Properties.HORIZONTAL_FACING;

	public SideHopper(Block.Settings settings) {
		super(settings);
		this.setDefaultState(getDefaultState().with(DIRECTION, Direction.NORTH));
	}
	
	@Override
	protected void appendProperties(Builder<Block, BlockState> builder) {
		super.appendProperties(builder);
		
		builder.add(DIRECTION);
	}

	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new SideHopperEntity(pos, state);
	}

	@Override
	@Nullable
	protected MapCodec<SideHopper> getCodec() {
		return null;
	}
	
	
	@Override
	@Nullable
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
		return validateTicker(type, HandlingBlocks.ENTITYTYPE_SIDE_HOPPER, SideHopperEntity::tick);
	}
}
