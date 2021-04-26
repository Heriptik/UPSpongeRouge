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

public class OpenConseilExecutor implements CommandExecutor{

    @Override
    public CommandResult execute (CommandSource src, CommandContext args) throws CommandException{

        if (! (src instanceof Player)){
            src.sendMessage(Text.of(TextColors.RED, "Message envoyé par la console !"));
            return CommandResult.success();
        }

        Player player = (Player) src;
        String text = args.getOne("type du conseil").get().toString();
        Sponge.getServer().getBroadcastChannel().send(Text.of(TextColors.GRAY, "Conseil ", Text.of(TextColors.GREEN,  player.getName()), Text.of(TextColors.GRAY, " ouvre l'arène de type "), Text.of(TextColors.GREEN, text, Text.of(TextColors.GRAY, " /warp ligue"))));
        return CommandResult.success();
    }

}
