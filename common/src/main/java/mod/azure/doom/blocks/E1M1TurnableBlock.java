package mod.azure.doom.blocks;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

import java.util.function.ToIntFunction;

public class E1M1TurnableBlock extends Block {

	public static final DirectionProperty direction = HorizontalDirectionalBlock.FACING;
	public static final BooleanProperty light = RedstoneTorchBlock.LIT;

	public static final IntegerProperty LIGHT_LEVEL = BlockStateProperties.AGE_15;

	public E1M1TurnableBlock() {
		super(Properties.of().sound(SoundType.METAL).lightLevel(litBlockEmission(15)));
		this.registerDefaultState(this.stateDefinition.any().setValue(direction, Direction.NORTH).setValue(light, Boolean.valueOf(true)));
	}

	private static ToIntFunction<BlockState> litBlockEmission(int p_50760_) {
		return p_50763_ -> BlockStateProperties.MAX_LEVEL_15;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(direction, context.getHorizontalDirection());
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(direction, rot.rotate(state.getValue(direction)));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(direction)));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(direction, light);
	}

}