package com.blalp.flyspeed;

import java.util.HashMap;
import java.util.UUID;

import com.flowpowered.math.vector.Vector3d;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.MoveEntityEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;

@Plugin(id = "flyspeed", name = "FlySpeed", version = "1.0", description = "Sets Flyspeed", authors = {
        "blalp" })
public class FlySpeedSponge implements CommandExecutor {
    private HashMap<UUID,Double> flyspeed = new HashMap<>();
    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        CommandSpec myCommandSpec = CommandSpec.builder().description(Text.of("Flyspeed")).permission("fly.speed")
                .executor(this)
                .arguments(GenericArguments.onlyOne(GenericArguments.doubleNum(Text.of("speed")))).build();
        Sponge.getCommandManager().register(this, myCommandSpec, "flyspeed");

    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        if (src instanceof Player) {
            if (args.hasAny("speed")){
                flyspeed.put(((Player)src).getUniqueId(),args.<Double>getOne("speed").get());
            } else {
                src.sendMessage(Text.of("Please include a speed (can be decimal)"));
            }
        } else {
            src.sendMessage(Text.of("not a player."));
        }
        return null;
    }

    @Listener
    public void onPlayerMove(MoveEntityEvent event) {
        if (flyspeed.containsKey(event.getTargetEntity().getUniqueId())) {
            if (event.getTargetEntity() instanceof Player) {
                if (event instanceof MoveEntityEvent.Position){
                    if(!((Player)event.getTargetEntity()).isOnGround()) {
                        if(event.getToTransform().getScale().distance(0, 0, 0)<flyspeed.get(event.getTargetEntity().getUniqueId())){
                            event.getToTransform().addScale(new Vector3d(flyspeed.get(event.getTargetEntity().getUniqueId()), flyspeed.get(event.getTargetEntity().getUniqueId()), flyspeed.get(event.getTargetEntity().getUniqueId())));
                        }
                    }
                }
            }
        }
    }
}