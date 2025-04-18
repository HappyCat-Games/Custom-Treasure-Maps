package games.happycat.customtreasuremaps;

import games.happycat.customtreasuremaps.config.ModServerConfig;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(CustomTreasureMaps.MODID)
public class CustomTreasureMaps
{
    public static final String MODID = "customtreasuremaps";

    public CustomTreasureMaps(IEventBus eventBus, ModContainer container)
    {
        container.registerConfig(ModConfig.Type.SERVER, ModServerConfig.SPEC);
    }
}
