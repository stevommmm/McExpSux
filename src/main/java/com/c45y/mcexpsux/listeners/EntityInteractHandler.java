package com.c45y.mcexpsux.listeners;

import com.c45y.mcexpsux.McExpSux;
import com.c45y.mcexpsux.ScoreType;
import java.util.AbstractMap;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 *
 * @author c45y
 */
public class EntityInteractHandler extends McExpSux {

    private final McExpSux plugin;

    public EntityInteractHandler(McExpSux instance) {
        plugin = instance;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        /* Check if pvp kill */
        if ((player.getKiller() instanceof Player)) {
            this.enqueue(new AbstractMap.SimpleEntry(ScoreType.PLAYER_KILL, player.getKiller().getName()));
        }
    }
    
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onEntityDeath(EntityDeathEvent event) {
        if(event.getEntity() instanceof Player) {
            return; // We handle player deaths elsewhere
        }
        if (event.getEntity().getKiller() instanceof Player) {
            String player = event.getEntity().getKiller().getName();
            EntityType entity =  event.getEntity().getType();
            if(entity == EntityType.GIANT) {
                 this.enqueue(new AbstractMap.SimpleEntry(ScoreType.MOB_KILL_GIANT, player));
                 return;
            }
            if(entity == EntityType.GHAST) {
                 this.enqueue(new AbstractMap.SimpleEntry(ScoreType.MOB_KILL_GHAST, player));
                 return;
            }
            this.enqueue(new AbstractMap.SimpleEntry(ScoreType.MOB_KILL, player));
        }
    }
}