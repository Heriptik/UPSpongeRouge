package xyz.ultrapixelmon.pepefab.upspongerouge.Event;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.MoveEntityEvent;
import org.spongepowered.api.event.filter.cause.Root;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

public class LimitHauteur260 {

    @Listener
    public void onPlayerMove(MoveEntityEvent.Position event, @Root Player player){

        Location<World> playerlocation = event.getTargetEntity().getLocation();

        if(playerlocation.getY() >= 280){
            player.setLocation(playerlocation.add(0,-20,0));
            Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "pokefaint " + player.getName());
            player.sendMessage(Text.of(TextColors.GRAY, "Hey! Vous ne pouvez pas monter à une hauteur supérieur à 260 blocks."));
        }

    }
}
