package xyz.ultrapixelmon.pepefab.upspongerouge.Executor;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.title.Title;

public class SetWarpEventExecutor implements CommandExecutor {

    @Override
    public CommandResult execute (CommandSource src, CommandContext args) throws CommandException {
        if (! (src instanceof Player)){
            src.sendMessage(Text.of(TextColors.RED, "Message envoyé par la console !"));
            return CommandResult.success();
        }
        Player player = (Player) src;

        if(player.getWorld().getName().equals("Event")){

            Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "lp user " + player.getName() + " permission settemp nucleus.warp.set.base true 3s");
            Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "warp delete event");
            Sponge.getCommandManager().process(src, "warp set event");

            Title title = Title.builder().title(Text.of(TextColors.GREEN, "Warp modifié !")).stay(50).build();
            player.sendTitle(title);

            return CommandResult.success();

        }else{
            player.sendMessage(Text.of(TextColors.RED, "Vous n'êtes pas dans le monde event."));
            return CommandResult.success();
        }
    }

}
