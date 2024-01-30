package yeatware;

import net.fabricmc.api.ModInitializer;

import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Yeat implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("yeat");
	public static MinecraftClient mc = MinecraftClient.getInstance();
	@Override
	public void onInitialize() {


		LOGGER.info("splash x bennyx x yeat!");
	}
}