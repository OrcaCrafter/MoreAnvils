package orca.more_anvils;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class MoreAnvilsDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(MoreAnvilsBlockLootTableProvider::new);

        pack.addProvider(MoreAnvilsRecipeProvider::new);

        pack.addProvider(MoreAnvilsEnglishLangProvider::new);

        pack.addProvider(MoreAnvilsBlockTagProvider::new);
	}
}
