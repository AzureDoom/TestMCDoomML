package mod.azure.doom.blocks;

import mod.azure.doom.blocks.blockentities.GunBlockEntity;
import mod.azure.doom.platform.Services;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.ToIntFunction;

public class GunTableBlock extends Block implements EntityBlock {

    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    private static final VoxelShape XBASE1 = Block.box(0, 0, -16, 16, 9, 32);
    private static final VoxelShape XBASE2 = Block.box(2, 9, -14, 13, 25, 30);
    private static final VoxelShape YBASE1 = Block.box(-16, 0, 0, 32, 9, 16);
    private static final VoxelShape YBASE2 = Block.box(-14, 9, 2, 30, 25, 13);
    private static final VoxelShape X_AXIS_AABB = Shapes.or(XBASE1, XBASE2);
    private static final VoxelShape Z_AXIS_AABB = Shapes.or(YBASE1, YBASE2);
    public static final BooleanProperty light = RedstoneTorchBlock.LIT;

    public static final IntegerProperty LIGHT_LEVEL = BlockStateProperties.AGE_15;

    public GunTableBlock() {
        super(Properties.of().sound(SoundType.METAL).explosionResistance(30).strength(4.0f).noOcclusion());
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.WEST));
    }

    private static ToIntFunction<BlockState> litBlockEmission(int p_50760_) {
        return p_50763_ -> BlockStateProperties.MAX_LEVEL_15;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return Services.ENTITIES_HELPER.getGunTableEntity().create(pos, state);
    }

    @Override
    public RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!world.isClientSide) {
            var screenHandlerFactory = state.getMenuProvider(world, pos);
            if (screenHandlerFactory != null)
                player.openMenu(screenHandlerFactory);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public MenuProvider getMenuProvider(BlockState state, Level world, BlockPos pos) {
        return (MenuProvider) world.getBlockEntity(pos);
    }

    @Override
    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            var blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof GunBlockEntity) {
                Containers.dropContents(world, pos, (GunBlockEntity) blockEntity);
                world.updateNeighbourForOutputSignal(pos, this);
            }
            super.onRemove(state, world, pos, newState, moved);
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, light);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        var direction = state.getValue(FACING);
        return direction.getAxis() == Direction.Axis.X ? X_AXIS_AABB : Z_AXIS_AABB;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        var direction = (Direction) state.getValue(FACING);
        return direction.getAxis() == Direction.Axis.X ? world.getBlockState(pos.south()).isAir() && world.getBlockState(pos.north()).isAir() : world.getBlockState(pos.east()).isAir() && world.getBlockState(pos.west()).isAir();
    }

}