package orca.more_anvils;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import java.util.concurrent.CompletableFuture;

public class MoreAnvilsEnglishLangProvider extends FabricLanguageProvider {
    protected MoreAnvilsEnglishLangProvider (FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        // Specifying en_us is optional, as it's the default language code
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider wrapperLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add("block.more-anvils.copper_anvil", "Copper Anvil");
        translationBuilder.add("block.more-anvils.exposed_copper_anvil", "Exposed Copper Anvil");
        translationBuilder.add("block.more-anvils.weathered_copper_anvil", "Weathered Copper Anvil");
        translationBuilder.add("block.more-anvils.oxidized_copper_anvil", "Oxidized Copper Anvil");

        translationBuilder.add("block.more-anvils.waxed_copper_anvil", "Waxed Copper Anvil");
        translationBuilder.add("block.more-anvils.waxed_exposed_copper_anvil", "Waxed Exposed Copper Anvil");
        translationBuilder.add("block.more-anvils.waxed_weathered_copper_anvil", "Waxed Weathered Copper Anvil");
        translationBuilder.add("block.more-anvils.waxed_oxidized_copper_anvil", "Waxed Oxidized Copper Anvil");

        translationBuilder.add("block.more-anvils.gold_anvil", "Gold Anvil");

        translationBuilder.add("block.more-anvils.netherite_anvil", "Netherite Anvil");


    }
}
