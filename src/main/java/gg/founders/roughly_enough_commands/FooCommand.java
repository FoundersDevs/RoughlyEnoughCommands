package gg.founders.roughly_enough_commands;

import com.mojang.brigadier.CommandDispatcher;
import io.github.cottonmc.clientcommands.*;
import net.minecraft.text.LiteralText;

public class FooCommand implements ClientCommandPlugin {
    @Override
    public void registerCommands(CommandDispatcher<CottonClientCommandSource> dispatcher) {
        dispatcher.register(ArgumentBuilders.literal("foo").executes(
            source -> {
                source.getSource().sendFeedback(new LiteralText("bar"));
                return 1;
            }
        ));
    }
}