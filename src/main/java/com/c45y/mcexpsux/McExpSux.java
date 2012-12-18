package com.c45y.mcexpsux;

import com.c45y.mcexpsux.listeners.BlockHandler;
import com.c45y.mcexpsux.listeners.EntityInteractHandler;
import java.util.AbstractMap.SimpleEntry;
import java.util.concurrent.LinkedBlockingQueue;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class McExpSux extends JavaPlugin implements Listener {

    LinkedBlockingQueue clq = new LinkedBlockingQueue();
    ScoreHandler sc = new ScoreHandler(clq);

    @Override
    public void onDisable() {
        try {
            sc.interrupt();
            sc.join();
            System.out.println("ScoreHandler joined.");
        } catch (InterruptedException iex) {
            iex.printStackTrace();
        }
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        /* Manage what we will be listening to */
        if (this.getConfig().getBoolean("score.pvp.enabled", true)) {
            new EntityInteractHandler(this);
        }
        if (this.getConfig().getBoolean("score.block.enabled", true)) {
            new BlockHandler(this);
        }

        sc.start();
        if (sc.isAlive()) {
            System.out.println("ScoreHandler started.");
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        this.enqueue(new SimpleEntry(ScoreType.PLAYER_JOIN, event.getPlayer().getName()));
    }

    public void enqueue(SimpleEntry toAdd) {
        try {
            this.clq.put(toAdd);
        } catch (InterruptedException iex) {
            iex.printStackTrace();
        }
    }
}
