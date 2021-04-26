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

public class LigueNormalExecutor implements CommandExecutor{

    @Override
    public CommandResult execute (CommandSource src, CommandContext args) throws CommandException{

        if (! (src instanceof Player)){
            src.sendMessage(Text.of(TextColors.RED, "Message envoyé par la console !"));
            return CommandResult.success();
        }

        Player player = (Player) src;
        String pseudotarget = args.<Player>getOne("pseudo").get().getName();
        Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "lp user " + pseudotarget + " meta addsuffix 10 &e?");
        Sponge.getCommandManager().process(player, "givebadge " + pseudotarget + " Ligue");
        player.sendMessage(Text.of(TextColors.GREEN, "Commande envoyée"));
        return CommandResult.success();
    }

}
