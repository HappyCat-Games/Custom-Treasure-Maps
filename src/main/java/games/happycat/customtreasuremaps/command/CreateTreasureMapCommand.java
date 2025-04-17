package games.happycat.customtreasuremaps.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.coordinates.Coordinates;
import net.minecraft.commands.arguments.coordinates.Vec3Argument;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.item.component.MapDecorations;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.maps.MapDecorationTypes;
import net.minecraft.world.phys.Vec3;

public class CreateTreasureMapCommand {
    public CreateTreasureMapCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("createtreasuremap")
                        .requires(p -> p.hasPermission(2))
                .then(Commands.argument("location", Vec3Argument.vec3())
                        .executes(this::execute)));

    }

    private int execute(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        ServerPlayer player = source.getPlayer();
        Level level = player.level();

        Coordinates location = Vec3Argument.getCoordinates(context, "location");
        Vec3 pos = location.getPosition(source);

        ItemStack map = MapItem.create(level, (int)pos.x, (int)pos.z, (byte)1, true, true);

        // Add red X to map at passed location
        DataComponentMap componentMap = map.getComponents();
        MapDecorations decorations = componentMap.get(DataComponents.MAP_DECORATIONS);
        MapDecorations.Entry redX = new MapDecorations.Entry(MapDecorationTypes.RED_X, pos.x, pos.z, 0.0f);
        decorations = decorations.withDecoration("red_x", redX);
        map.set(DataComponents.MAP_DECORATIONS, decorations);

        map.set(DataComponents.ITEM_NAME, Component.translatable("filled_map.buried_treasure"));

        boolean added = player.getInventory().add(map);
        if (!added) {
            // If inventory is full, drop the item at the player's location
            player.drop(map, false);
        }

        source.sendSuccess(() -> Component.literal("Treasure map created"), false);
        return 1;
    }
}
