package gg.founders.roughly_enough_commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.arguments.IntegerArgumentType;

import io.github.cottonmc.clientcommands.*;

import net.minecraft.command.arguments.DimensionArgumentType;
import net.minecraft.text.LiteralText;
import net.minecraft.world.dimension.DimensionType;


public class CoordConverter implements ClientCommandPlugin {
    @Override
    public void registerCommands(CommandDispatcher<CottonClientCommandSource> dispatcher) {
        dispatcher
        .register(ArgumentBuilders.literal("rcoords")
        .then(RequiredArgumentBuilder.<CottonClientCommandSource, DimensionType>argument("dimension", DimensionArgumentType.dimension())
        .then(ArgumentBuilders.argument("x", IntegerArgumentType.integer())
        .then(ArgumentBuilders.argument("z", IntegerArgumentType.integer())
        .executes(context -> {
            DimensionType dimension = context.getArgument("dimension", DimensionType.class);
            Integer coordx = IntegerArgumentType.getInteger(context, "x");
            Integer coordz = IntegerArgumentType.getInteger(context, "z");

            if (dimension.toString().equals("minecraft:overworld")) {
                coordx = coordx / 8;
                coordz = coordz / 8;
                context.getSource().sendFeedback(new LiteralText(String.format("Overworld: %d, %d", coordx,coordz)));
                return 1;
            }
            else if (dimension.toString().equals("minecraft:the_nether")) {
                coordx = coordx * 8;
                coordz = coordz * 8;
                context.getSource().sendFeedback(new LiteralText(String.format("Nether: %d, %d", coordx,coordz)));
                return 1;
            }
            else {
                context.getSource().sendFeedback(new LiteralText("§eLooks like you entered something wrong."));
                if (dimension.toString().equals("minecraft:the_end")) {
                    context.getSource().sendFeedback(new LiteralText("§4The end dimension is not a valid coord conversion!"));
                }
                return 0;
            }
            
        }
        )))));
    }
}