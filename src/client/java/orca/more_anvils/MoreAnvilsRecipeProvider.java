package orca.more_anvils;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class MoreAnvilsRecipeProvider extends FabricRecipeProvider {
    public MoreAnvilsRecipeProvider (FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }
	
	record CopperAnvilRecipeInfo(Item block, Item anvil) {
	}

    static final CopperAnvilRecipeInfo[] COPPER_ANVIL_INFO = new CopperAnvilRecipeInfo[] {
            new CopperAnvilRecipeInfo(Items.COPPER_BLOCK,               AbstractAnvil.COPPER_ANVIL.asItem()),
            new CopperAnvilRecipeInfo(Items.EXPOSED_COPPER,             AbstractAnvil.EXPOSED_COPPER_ANVIL.asItem()),
            new CopperAnvilRecipeInfo(Items.WEATHERED_COPPER,           AbstractAnvil.WEATHERED_COPPER_ANVIL.asItem()),
            new CopperAnvilRecipeInfo(Items.OXIDIZED_COPPER,            AbstractAnvil.OXIDIZED_COPPER_ANVIL.asItem()),
            new CopperAnvilRecipeInfo(Items.WAXED_COPPER_BLOCK,         AbstractAnvil.WAXED_COPPER_ANVIL.asItem()),
            new CopperAnvilRecipeInfo(Items.WAXED_EXPOSED_COPPER,       AbstractAnvil.WAXED_EXPOSED_COPPER_ANVIL.asItem()),
            new CopperAnvilRecipeInfo(Items.WAXED_WEATHERED_COPPER,     AbstractAnvil.WAXED_WEATHERED_COPPER_ANVIL.asItem()),
            new CopperAnvilRecipeInfo(Items.WAXED_OXIDIZED_COPPER,      AbstractAnvil.WAXED_OXIDIZED_COPPER_ANVIL.asItem()),
    };

    @Override
    protected @NotNull RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput exporter) {
        return new RecipeProvider(registryLookup, exporter) {
            @Override
            public void buildRecipes() {
                //TODO waxed copper blocks
                for (CopperAnvilRecipeInfo info : COPPER_ANVIL_INFO) {
                    Item block = info.block;
                    Item anvil = info.anvil;

                    shaped(RecipeCategory.MISC, anvil, 1)
                            .pattern("BBB")
                            .pattern(" I ")
                            .pattern("III")
                            .define('B', block)
                            .define('I', Items.COPPER_INGOT)
                            .unlockedBy(getHasName(anvil) + "_mats", has(block))
                            .group("craft_copper_anvil")
                            .save(output);
                }

                shapeless(RecipeCategory.MISC, AbstractAnvil.WAXED_COPPER_ANVIL, 1)
                        .requires(AbstractAnvil.COPPER_ANVIL)
                        .requires(Items.HONEYCOMB)
                        .unlockedBy(getHasName(Items.HONEYCOMB), has(Items.HONEYCOMB))
                        .group("wax_copper_anvil")
                        .save(output, "wax_copper_anvil");

                shapeless(RecipeCategory.MISC, AbstractAnvil.WAXED_EXPOSED_COPPER_ANVIL, 1)
                        .requires(AbstractAnvil.EXPOSED_COPPER_ANVIL)
                        .requires(Items.HONEYCOMB)
                        .unlockedBy(getHasName(Items.HONEYCOMB), has(Items.HONEYCOMB))
                        .group("wax_copper_anvil")
                        .save(output, "wax_exposed_copper_anvil");

                shapeless(RecipeCategory.MISC, AbstractAnvil.WAXED_WEATHERED_COPPER_ANVIL, 1)
                        .requires(AbstractAnvil.WEATHERED_COPPER_ANVIL)
                        .requires(Items.HONEYCOMB)
                        .unlockedBy(getHasName(Items.HONEYCOMB), has(Items.HONEYCOMB))
                        .group("wax_copper_anvil")
                        .save(output, "wax_weathered_copper_anvil");

                shapeless(RecipeCategory.MISC, AbstractAnvil.WAXED_OXIDIZED_COPPER_ANVIL, 1)
                        .requires(AbstractAnvil.OXIDIZED_COPPER_ANVIL)
                        .requires(Items.HONEYCOMB)
                        .unlockedBy(getHasName(Items.HONEYCOMB), has(Items.HONEYCOMB))
                        .group("wax_copper_anvil")
                        .save(output, "wax_oxidized_copper_anvil");

                shaped(RecipeCategory.MISC, AbstractAnvil.GOLD_ANVIL, 1)
                        .pattern("BBB")
                        .pattern(" I ")
                        .pattern("III")
                        .define('B', Items.GOLD_BLOCK)
                        .define('I', Items.GOLD_INGOT)
                        .unlockedBy(getHasName(Items.GOLD_BLOCK), has(Items.GOLD_BLOCK))
                        .save(output);

                netheriteSmithing(AbstractAnvil.GOLD_ANVIL.asItem(), RecipeCategory.MISC, AbstractAnvil.NETHERITE_ANVIL.asItem());
            }
        };
    }

    @Override
    public @NotNull String getName() {
        return "MoreAnvilsRecipeProvider";
    }
}
