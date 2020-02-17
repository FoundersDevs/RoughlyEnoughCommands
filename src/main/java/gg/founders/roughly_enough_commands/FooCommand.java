package gg.founders.roughly_enough_commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;

import io.github.cottonmc.clientcommands.*;

import net.minecraft.text.LiteralText;

public class FooCommand implements ClientCommandPlugin {
    @Override
    public void registerCommands(CommandDispatcher<CottonClientCommandSource> dispatcher) {
        dispatcher
        .register(ArgumentBuilders.literal("foo")
        .then(ArgumentBuilders.argument("arg", StringArgumentType.greedyString())
        .executes(context -> {
            String message = StringArgumentType.getString(context, "arg");
            context.getSource().sendFeedback(new LiteralText(message));
            return 1;
        }
        )));
    }
}