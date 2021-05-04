package xyz.ultrapixelmon.pepefab.upspongerouge.Executor;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.title.Title;

public class RegardeTonChatExecutor implements CommandExecutor {

    @Override
    public CommandResult execute (CommandSource src, CommandContext args) throws CommandException {
        if (! (src instanceof Player)){
            src.sendMessage(Text.of(TextColors.RED, "Message envoyé par la console !"));
            return CommandResult.success();
        }
        Player player = (Player) src;
        Player target = args.<Player>getOne("pseudo").get(); // Pseudo qui est entréé lors de l'execution de la commande

        Title title = Title.builder().title(Text.of(TextColors.RED, "Regarde ton chat !")).stay(200).build();
        target.sendTitle(title);
        player.sendMessage(Text.of(TextColors.GREEN, "Message envoyé avec succès."));

        return CommandResult.success();
    }
}
