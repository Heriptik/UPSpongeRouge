package xyz.ultrapixelmon.pepefab.upspongerouge.Event;

import com.flowpowered.math.vector.Vector3d;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.type.HandTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.action.InteractEvent;
import org.spongepowered.api.event.cause.entity.damage.DamageTypes;
import org.spongepowered.api.event.cause.entity.damage.source.DamageSource;
import org.spongepowered.api.event.entity.DamageEntityEvent;
import org.spongepowered.api.event.filter.cause.Root;
import org.spongepowered.api.event.item.inventory.ChangeInventoryEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;
import org.spongepowered.api.item.inventory.transaction.SlotTransaction;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.Optional;

public class EventListeners {

    @Listener
    public void onFall(DamageEntityEvent event, @Root DamageSource source) {
        event.setCancelled(source.getType().equals(DamageTypes.FALL));
    }

    @Listener
    public void onPlayerJoin(ClientConnectionEvent.Join playerJoinEvent){

        Player player = playerJoinEvent.getTargetEntity();

        if (player.getWorld().getName().equals("Event")){
            Location<World> spawnTeleport = Sponge.getGame().getServer().getWorld("PokeSpawn").get().getSpawnLocation();
            player.transferToWorld(spawnTeleport.getExtent().getName(), new Vector3d(spawnTeleport.getX(), spawnTeleport.getY(), spawnTeleport.getZ()));
        }

    }

    @SuppressWarnings("deprecation")
    @Listener
    public void onItemInHand(InteractEvent event, @Root Player player){

        Optional<ItemStack> optionalStack = player.getItemInHand(HandTypes.MAIN_HAND);

        if(optionalStack.isPresent()){
            ItemStack item;
            item = optionalStack.get();

            if(item.getItem().getName().contains("pixelmon:gold_bottle_cap") || item.getItem().getName().contains("pixelmon:silver_bottle_cap"))
                if(!item.get(Keys.ITEM_LORE).isPresent()){
                    item.setQuantity(0);
                    player.sendMessage(Text.of(TextColors.GRAY, "Les capsules d'or et d'argent sans nom ne sont pas utilisables sur le serveur."));
                }
        }
    }

    @Listener
    public void onInventoryClick(ChangeInventoryEvent event, @Root Player player) {

        for(SlotTransaction transaction : event.getTransactions()){
            ItemStack itemStack = transaction.getFinal().createStack();

            if(itemStack.getType().getName().contains("pixelmon:gold_bottle_cap") || itemStack.getType().getName().contains("pixelmon:silver_bottle_cap"))
                if(!itemStack.get(Keys.ITEM_LORE).isPresent()){
                    transaction.setCustom(ItemStackSnapshot.NONE);
                    System.out.println("Silver ou bottle supprimé " + player.getName() + player.getLocation());
                    player.sendMessage(Text.of(TextColors.GRAY, "Les capsules d'or et d'argent sans nom ne sont pas utilisables sur le serveur."));
                }
        }
    }


}
