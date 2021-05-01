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
import org.spongepowered.api.text.serializer.TextSerializers;

public class BanGTSExecutor implements CommandExecutor {

    @Override
    public CommandResult execute (CommandSource src, CommandContext args) throws CommandException {
        if (! (src instanceof Player)){
            src.sendMessage(Text.of(TextColors.RED, "Message envoyé par la console !"));
            return CommandResult.success();
        }

        Player player = (Player) src;
        Player target = args.<Player>getOne("pseudo").get();
        String duree = args.<String>getOne("durée").get(); // Durée séléctionné (1m, 1h, 1d etc ...)
        String raison = args.<String>getOne("raison").get(); // Raison du bannissement

        if(duree.endsWith("s") || duree.endsWith("m") || duree.endsWith("h") || duree.endsWith("d") || duree.endsWith("w")){
            Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "lp user " + target.getName() + " permission settemp gts.command.gts.base false " + duree);

            target.sendMessage(this.fromLegacy("&eGTS &c» Vous n'avez plus accès à la GTS pour une durée de &e" + duree + " &craison: &e" + raison));
            this.notifyGTSBanStaff(this.fromLegacy("&eGTS &c» Le joueur &e" + target.getName() + " &cvient d'être banni de la GTS pour une durée de &e" + duree + " &cpar &e" + player.getName() + " &craison: &e" + raison));
            return CommandResult.success();
        }else{
            player.sendMessage(this.fromLegacy("&eGTS &c» La durée indiqué n'est pas conforme. (Ex : 15m)"));
            return CommandResult.success();
        }
    }

    public void notifyGTSBanStaff(Text message) {
        for (Player player : Sponge.getServer().getOnlinePlayers()) {
            if (player.hasPermission("gtsban.notify")) {
                Text.Builder send = Text.builder();
                send.append(message);
                player.sendMessage(send.build());
            }
        }
    }

    public Text fromLegacy(String legacy) {
        return TextSerializers.FORMATTING_CODE.deserializeUnchecked(legacy);
    }
}
