package orca.more_anvils;


import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;

import java.util.concurrent.CompletableFuture;

public class MoreAnvilsBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public MoreAnvilsBlockTagProvider (FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {

        this.valueLookupBuilder(BlockTags.ANVIL).add(AbstractAnvil.COPPER_ANVIL);
        this.valueLookupBuilder(BlockTags.ANVIL).add(AbstractAnvil.EXPOSED_COPPER_ANVIL);
        this.valueLookupBuilder(BlockTags.ANVIL).add(AbstractAnvil.WEATHERED_COPPER_ANVIL);
        this.valueLookupBuilder(BlockTags.ANVIL).add(AbstractAnvil.OXIDIZED_COPPER_ANVIL);

        this.valueLookupBuilder(BlockTags.ANVIL).add(AbstractAnvil.GOLD_ANVIL);

        this.valueLookupBuilder(BlockTags.ANVIL).add(AbstractAnvil.NETHERITE_ANVIL);

        this.valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE).add(AbstractAnvil.COPPER_ANVIL);
        this.valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE).add(AbstractAnvil.EXPOSED_COPPER_ANVIL);
        this.valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE).add(AbstractAnvil.WEATHERED_COPPER_ANVIL);
        this.valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE).add(AbstractAnvil.OXIDIZED_COPPER_ANVIL);

        this.valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE).add(AbstractAnvil.WAXED_COPPER_ANVIL);
        this.valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE).add(AbstractAnvil.WAXED_EXPOSED_COPPER_ANVIL);
        this.valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE).add(AbstractAnvil.WAXED_WEATHERED_COPPER_ANVIL);
        this.valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE).add(AbstractAnvil.WAXED_OXIDIZED_COPPER_ANVIL);

        this.valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE).add(AbstractAnvil.GOLD_ANVIL);

        this.valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE).add(AbstractAnvil.NETHERITE_ANVIL);


    }
}
