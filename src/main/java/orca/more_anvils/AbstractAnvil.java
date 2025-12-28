package orca.more_anvils;

import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.HoneycombItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;

import java.util.Map;
import java.util.function.Function;

public abstract class AbstractAnvil {
	public static final Block COPPER_ANVIL			 = registerAnvil("copper_anvil",                CopperAnvil::new,   25f, MapColor.COLOR_ORANGE,         true);
	public static final Block EXPOSED_COPPER_ANVIL	 = registerAnvil("exposed_copper_anvil",        CopperAnvil::new,   25f, MapColor.TERRACOTTA_LIGHT_GRAY,true);
	public static final Block WEATHERED_COPPER_ANVIL = registerAnvil("weathered_copper_anvil",      CopperAnvil::new,   25f, MapColor.WARPED_STEM,          true);
	public static final Block OXIDIZED_COPPER_ANVIL	 = registerAnvil("oxidized_copper_anvil",       CopperAnvil::new,   25f, MapColor.WARPED_NYLIUM,        true);

    public static final Block WAXED_COPPER_ANVIL			 = registerAnvil("waxed_copper_anvil",            WaxedAnvil::new,   25f, MapColor.COLOR_ORANGE,         true);
    public static final Block WAXED_EXPOSED_COPPER_ANVIL	 = registerAnvil("waxed_exposed_copper_anvil",    WaxedAnvil::new,   25f, MapColor.TERRACOTTA_LIGHT_GRAY,true);
    public static final Block WAXED_WEATHERED_COPPER_ANVIL   = registerAnvil("waxed_weathered_copper_anvil",  WaxedAnvil::new,   25f, MapColor.WARPED_STEM,          true);
    public static final Block WAXED_OXIDIZED_COPPER_ANVIL	 = registerAnvil("waxed_oxidized_copper_anvil",   WaxedAnvil::new,   25f, MapColor.WARPED_NYLIUM,        true);


    public static final Block GOLD_ANVIL			 = registerAnvil("gold_anvil",              GoldAnvil::new,     25f, MapColor.GOLD,                 false);
	
	public static final Block NETHERITE_ANVIL		 = registerAnvil("netherite_anvil",         NetheriteAnvil::new,250, MapColor.COLOR_BLACK,          false);

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS)
                .register((itemGroup) -> itemGroup.accept(COPPER_ANVIL));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS)
                .register((itemGroup) -> itemGroup.accept(EXPOSED_COPPER_ANVIL));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS)
                .register((itemGroup) -> itemGroup.accept(WEATHERED_COPPER_ANVIL));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS)
                .register((itemGroup) -> itemGroup.accept(OXIDIZED_COPPER_ANVIL));

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS)
                .register((itemGroup) -> itemGroup.accept(WAXED_COPPER_ANVIL));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS)
                .register((itemGroup) -> itemGroup.accept(WAXED_EXPOSED_COPPER_ANVIL));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS)
                .register((itemGroup) -> itemGroup.accept(WAXED_WEATHERED_COPPER_ANVIL));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS)
                .register((itemGroup) -> itemGroup.accept(WAXED_OXIDIZED_COPPER_ANVIL));

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS)
                .register((itemGroup) -> itemGroup.accept(GOLD_ANVIL));

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS)
                .register((itemGroup) -> itemGroup.accept(NETHERITE_ANVIL));

        //waxing from clicked
        HoneycombItem.WAXABLES.get().put(COPPER_ANVIL, WAXED_COPPER_ANVIL);
        HoneycombItem.WAXABLES.get().put(EXPOSED_COPPER_ANVIL, WAXED_EXPOSED_COPPER_ANVIL);
        HoneycombItem.WAXABLES.get().put(WEATHERED_COPPER_ANVIL, WAXED_WEATHERED_COPPER_ANVIL);
        HoneycombItem.WAXABLES.get().put(OXIDIZED_COPPER_ANVIL, WAXED_OXIDIZED_COPPER_ANVIL);
    }

	private static Block registerAnvil (String name, Function<BlockBehaviour.Properties, Block> blockFactory, float destroyTime, MapColor mapColor, boolean randomTick) {
        BlockBehaviour.Properties settings = BlockBehaviour.Properties
                .ofFullCopy(Blocks.ANVIL)
                .requiresCorrectToolForDrops()
                .destroyTime(destroyTime)
                .mapColor(mapColor);

        if (randomTick) {
            settings = settings.randomTicks();
        }

        // Create a registry key for the block
		ResourceKey<Block> blockKey = keyOfBlock(name);
		// Create the block instance
		Block block = blockFactory.apply(settings.setId(blockKey));

        // Items need to be registered with a different type of registry key, but the ID
        // can be the same.
        ResourceKey<Item> itemKey = keyOfItem(name);

        BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix());
        Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);

		return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
	}

	private static ResourceKey<Block> keyOfBlock(String name) {
		return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreAnvils.MOD_ID, name));
	}

	private static ResourceKey<Item> keyOfItem(String name) {
		return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MoreAnvils.MOD_ID, name));
	}


    public static class CopperAnvil extends AnvilBlock {
        @Override
        public @NotNull MapCodec<AnvilBlock> codec() {
            return CopperAnvil.CODEC;
        }

        public CopperAnvil (BlockBehaviour.Properties properties) {
            super(properties);
        }

        @Override
        protected void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
            //Slow down the random oxidization to take about 80 minutes
            if (randomSource.nextInt(15) != 0) return;

            if (blockState.is(AbstractAnvil.COPPER_ANVIL)) {
                serverLevel.setBlockAndUpdate(blockPos, AbstractAnvil.EXPOSED_COPPER_ANVIL.defaultBlockState().setValue(FACING, blockState.getValue(FACING)));

            } else if (blockState.is(AbstractAnvil.EXPOSED_COPPER_ANVIL)) {
                serverLevel.setBlockAndUpdate(blockPos, AbstractAnvil.WEATHERED_COPPER_ANVIL.defaultBlockState().setValue(FACING, blockState.getValue(FACING)));


            } else if (blockState.is(AbstractAnvil.WEATHERED_COPPER_ANVIL)) {
                serverLevel.setBlockAndUpdate(blockPos, AbstractAnvil.OXIDIZED_COPPER_ANVIL.defaultBlockState().setValue(FACING, blockState.getValue(FACING)));

            } else if (blockState.is(AbstractAnvil.OXIDIZED_COPPER_ANVIL)) {
                serverLevel.setBlockAndUpdate(blockPos, Blocks.AIR.defaultBlockState());

                //Add break sound
                serverLevel.levelEvent(1029, blockPos, 0);
            }
        }
		
		protected void falling(FallingBlockEntity fallingBlockEntity) {
			fallingBlockEntity.setHurtsEntities(1.0f, 20);
		}
    }

    public static class WaxedAnvil extends FallingBlock {
        public static final MapCodec<WaxedAnvil> CODEC = simpleCodec(WaxedAnvil::new);
        public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
        private static final Map<Direction.Axis, VoxelShape> SHAPES = Shapes.rotateHorizontalAxis(
                Shapes.or(Block.column(12.0, 0.0, 4.0), Block.column(8.0, 10.0, 4.0, 5.0), Block.column(4.0, 8.0, 5.0, 10.0), Block.column(10.0, 16.0, 10.0, 16.0))
        );

        public WaxedAnvil(Properties properties) {
            super(properties);
            this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
        }

        @Override
        public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
            return this.defaultBlockState().setValue(FACING, blockPlaceContext.getHorizontalDirection().getClockWise());
        }

        @Override
        protected @NotNull VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
            return SHAPES.get((blockState.getValue(FACING)).getAxis());
        }

        @Override
        protected @NotNull BlockState rotate(BlockState blockState, Rotation rotation) {
            return blockState.setValue(FACING, rotation.rotate(blockState.getValue(FACING)));
        }

        @Override
        protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
            builder.add(FACING);
        }

        @Override
        protected boolean isPathfindable(BlockState blockState, PathComputationType pathComputationType) {
            return false;
        }

        @Override
        protected @NotNull MapCodec<? extends FallingBlock> codec () {
            return CODEC;
        }

        @Override
        public int getDustColor(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
            return blockState.getMapColor(blockGetter, blockPos).col;
        }
		
		protected void falling(FallingBlockEntity fallingBlockEntity) {
			fallingBlockEntity.setHurtsEntities(1.0f, 20);
		}
    }

    public static class GoldAnvil extends AnvilBlock {
        public GoldAnvil (Properties properties) {
            super(properties);
        }

        protected void falling(FallingBlockEntity fallingBlockEntity) {
            fallingBlockEntity.setHurtsEntities(4.0f, 80);
        }

    }

    public static class NetheriteAnvil extends AnvilBlock {
        public NetheriteAnvil (Properties properties) {
            super(properties);
        }

        protected void falling(FallingBlockEntity fallingBlockEntity) {
            fallingBlockEntity.setHurtsEntities(6.0f, 120);
        }

    }

}
