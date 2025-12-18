package orca.more_anvils.mixin;

import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.state.BlockState;
import orca.more_anvils.AbstractAnvil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.world.level.block.AnvilBlock.FACING;

@Mixin(AnvilBlock.class)
public class AnvilDamageMixin {

	@Inject(at = @At("RETURN"), method = "damage", cancellable = true)
	private static void init (BlockState blockState, CallbackInfoReturnable<BlockState> cir) {
        // Replace the default functionality if the block is a custom anvil

        if (blockState.is(AbstractAnvil.COPPER_ANVIL)) {
			//Oxidize one level
            cir.setReturnValue(AbstractAnvil.EXPOSED_COPPER_ANVIL.defaultBlockState().setValue(FACING, blockState.getValue(FACING)));
			
        } else if (blockState.is(AbstractAnvil.EXPOSED_COPPER_ANVIL)) {
			//Oxidize one level
            cir.setReturnValue(AbstractAnvil.WEATHERED_COPPER_ANVIL.defaultBlockState().setValue(FACING, blockState.getValue(FACING)));
			
        } else if (blockState.is(AbstractAnvil.WEATHERED_COPPER_ANVIL)) {
			//Oxidize one level
            cir.setReturnValue(AbstractAnvil.OXIDIZED_COPPER_ANVIL.defaultBlockState().setValue(FACING, blockState.getValue(FACING)));
			
        } else if (blockState.is(AbstractAnvil.OXIDIZED_COPPER_ANVIL)) {
			//Break
            cir.setReturnValue(null);
			
        } else if (blockState.is(AbstractAnvil.GOLD_ANVIL)) {
			//Break
            cir.setReturnValue(null);
			
        } else if (blockState.is(AbstractAnvil.NETHERITE_ANVIL)) {
			//Don't change
            cir.setReturnValue(AbstractAnvil.NETHERITE_ANVIL.defaultBlockState().setValue(FACING, blockState.getValue(FACING)));
			
        }

    }
}