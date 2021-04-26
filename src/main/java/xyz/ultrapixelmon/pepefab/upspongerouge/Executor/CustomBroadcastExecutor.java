package xyz.ultrapixelmon.pepefab.upspongerouge.Executor;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;

public class CustomBroadcastExecutor implements CommandExecutor {

    @Override
    public CommandResult execute (CommandSource src, CommandContext args) throws CommandException{

        String text = args.getOne("message").get().toString();
        Sponge.getServer().getBroadcastChannel().send(Text.of(text));
        return CommandResult.success();

    }

}
