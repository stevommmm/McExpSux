package com.c45y.mcexpsux.listeners;

import com.c45y.mcexpsux.McExpSux;
import com.c45y.mcexpsux.ScoreType;
import java.util.AbstractMap;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 *
 * @author c45y
 */
public class BlockHandler extends McExpSux {

    private final McExpSux plugin;

    public BlockHandler(McExpSux instance) {
        plugin = instance;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {
        /* Point allocation for block type broken */
        String player = event.getPlayer().getName();
        int block = event.getBlock().getTypeId();
        switch (block) {
            case 56:
                this.enqueue(new AbstractMap.SimpleEntry(ScoreType.BLOCK_BREAK_DIAMOND, player));
                break;
            case 14:
                this.enqueue(new AbstractMap.SimpleEntry(ScoreType.BLOCK_BREAK_GOLD, player));
                break;
            case 15:
                this.enqueue(new AbstractMap.SimpleEntry(ScoreType.BLOCK_BREAK_IRON, player));
                break;
            case 16:
                this.enqueue(new AbstractMap.SimpleEntry(ScoreType.BLOCK_BREAK_COAL, player));
                break;
            case 81:
                this.enqueue(new AbstractMap.SimpleEntry(ScoreType.BLOCK_BREAK_EMERALD, player));
                break;
            default:
                this.enqueue(new AbstractMap.SimpleEntry(ScoreType.BLOCK_BREAK, player));
                break;
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBlockPlace(BlockPlaceEvent event) {
        this.enqueue(new AbstractMap.SimpleEntry(ScoreType.BLOCK_PLACE, event.getPlayer().getName()));
    }
}