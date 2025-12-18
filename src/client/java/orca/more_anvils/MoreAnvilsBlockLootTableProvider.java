package orca.more_anvils;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class MoreAnvilsBlockLootTableProvider extends FabricBlockLootTableProvider {
    protected MoreAnvilsBlockLootTableProvider (FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        dropSelf(AbstractAnvil.COPPER_ANVIL);
        dropSelf(AbstractAnvil.EXPOSED_COPPER_ANVIL);
        dropSelf(AbstractAnvil.WEATHERED_COPPER_ANVIL);
        dropSelf(AbstractAnvil.OXIDIZED_COPPER_ANVIL);

        dropSelf(AbstractAnvil.WAXED_COPPER_ANVIL);
        dropSelf(AbstractAnvil.WAXED_EXPOSED_COPPER_ANVIL);
        dropSelf(AbstractAnvil.WAXED_WEATHERED_COPPER_ANVIL);
        dropSelf(AbstractAnvil.WAXED_OXIDIZED_COPPER_ANVIL);

        dropSelf(AbstractAnvil.GOLD_ANVIL);

        dropSelf(AbstractAnvil.NETHERITE_ANVIL);
    }
}
