package xyz.ultrapixelmon.pepefab.upspongerouge;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.text.Text;

import xyz.ultrapixelmon.pepefab.upspongerouge.Event.EventListeners;
import xyz.ultrapixelmon.pepefab.upspongerouge.Event.LimitHauteur260;
import xyz.ultrapixelmon.pepefab.upspongerouge.Executor.*;

import java.util.EventListener;

@Plugin(
        id = "upspongerouge",
        name = "UPSpongeRouge",
        version = "1.3"
)
public class UPSpongeRouge {

    @Inject
    private Logger logger;

    public UPSpongeRouge() {
    }

    Task.Builder taskBuilder = Task.builder();

    @Listener
    public void onInit(GameInitializationEvent event){

        logger.info("[UPSpongeRouge] Plugin initialise avec succes.");

        // Register Listener
        Sponge.getEventManager().registerListeners(this, new EventListeners());
        Sponge.getEventManager().registerListeners(this, new LimitHauteur260());

        //Register command
        CommandSpec OpenLigue = CommandSpec.builder()
                .description(Text.of("Command conseil ouverture d'arène"))
                .permission("openconseil.openconseil")
                .arguments(GenericArguments.onlyOne(GenericArguments.string(Text.of("type du conseil"))))
                .executor (new OpenConseilExecutor())
                .build();
        Sponge.getCommandManager().register(this, OpenLigue, "openconseil");

        CommandSpec LigueNormal = CommandSpec.builder()
                .description(Text.of("Command a éxécuter après qu'un joueur est finit la ligue normal"))
                .permission("liguenormal.liguenormal")
                .arguments(GenericArguments.onlyOne(GenericArguments.player(Text.of("pseudo"))))
                .executor (new LigueNormalExecutor())
                .build();
        Sponge.getCommandManager().register(this, LigueNormal, "liguenormalgivebadge");

        CommandSpec LigueHeroique = CommandSpec.builder()
                .description(Text.of("Command a éxécuter après qu'un joueur est finit la ligue heroique"))
                .permission("ligueheroique.ligueheroique")
                .arguments(GenericArguments.onlyOne(GenericArguments.player(Text.of("pseudo"))))
                .executor (new LigueHeroiqueExecutor())
                .build();
        Sponge.getCommandManager().register(this, LigueHeroique, "ligueheroiquegivebadge");

        CommandSpec CloseLigue = CommandSpec.builder()
                .description(Text.of("Command conseil fermeture d'arène"))
                .permission("closeconseil.closeconseil")
                .arguments(GenericArguments.onlyOne(GenericArguments.string(Text.of("type du conseil"))))
                .executor (new CloseConseilExecutor())
                .build();
        Sponge.getCommandManager().register(this, CloseLigue, "closeconseil");

        Sponge.getEventManager().registerListeners(this, EventListener.class);

        CommandSpec CustomBroadcast = CommandSpec.builder()
                .description(Text.of("Commande pour envoyer un broadcast custom"))
                .permission("custombroadcast.custombroadcast")
                .arguments(GenericArguments.onlyOne(GenericArguments.remainingJoinedStrings(Text.of("message"))))
                .executor (new CustomBroadcastExecutor())
                .build();
        Sponge.getCommandManager().register(this, CustomBroadcast, "custombroadcast");

        CommandSpec RegardeTonChat = CommandSpec.builder()
                .description(Text.of("Envoi un title aux joueurs lui demandant de regarder son chat"))
                .permission("esttuafk.esttuafk")
                .arguments(GenericArguments.onlyOne(GenericArguments.player(Text.of("pseudo"))))
                .executor (new RegardeTonChat())
                .build();
        Sponge.getCommandManager().register(this, RegardeTonChat, "mategetvoulaitpoke", "regardetonchat");

        // Register TaskBuilder
        Task.builder().execute(() -> {
            for (Player player : Sponge.getServer().getOnlinePlayers()) {
                if (player.hasPermission("vippluslockfood.vippluslockfood")){
                    player.offer(Keys.FOOD_LEVEL, 30);
                }
            }
        }).intervalTicks(100).submit(this);

    }
}
