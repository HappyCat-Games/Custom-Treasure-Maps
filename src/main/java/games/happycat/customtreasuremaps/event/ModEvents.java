package games.happycat.customtreasuremaps.event;

import games.happycat.customtreasuremaps.CustomTreasureMaps;
import games.happycat.customtreasuremaps.command.CreateTreasureMapCommand;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.server.command.ConfigCommand;

@EventBusSubscriber(modid = CustomTreasureMaps.MODID, bus = EventBusSubscriber.Bus.GAME)
public class ModEvents {
    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        new CreateTreasureMapCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }
}
