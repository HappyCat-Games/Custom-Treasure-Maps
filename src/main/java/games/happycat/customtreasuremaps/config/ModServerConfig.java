package games.happycat.customtreasuremaps.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ModServerConfig {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;

    public static ModConfigSpec.IntValue createPermissionLevel;

    static {
        BUILDER.push("Permissions");

        createPermissionLevel = BUILDER
                .comment("The required permission level to use custom map features (0-4).")
                .defineInRange("createPermissionLevel", 2, 0, 4);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
