package orca.more_anvils;


import net.fabricmc.api.ModInitializer;

public class MoreAnvils implements ModInitializer {


    public static final String MOD_ID = "more-anvils";

    @Override
	public void onInitialize () {
		AbstractAnvil.initialize();
	}

}
