package blue.endless.handling;

import net.fabricmc.api.ModInitializer;

public class Handling implements ModInitializer {

	@Override
	public void onInitialize() {
		HandlingBlocks.init();
	}
	
}
