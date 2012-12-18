package com.c45y.mcexpsux;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author c45y
 */
public class ScoreHandler extends Thread {
    /* Our consumer thread.
     * Useds a blocking queue to pass jobs off the main bukkit thread,
     * this is where we should do math and sql stuff, but make sure to
     * finish all jobs before thread is allowed to die.
     */
    private Map<String, Score> scores = new HashMap<String, Score>();
    private LinkedBlockingQueue clq;

    public ScoreHandler(LinkedBlockingQueue clq) {
        this.clq = clq;
    }

    @Override
    public void run() {
        while (true) {
            try {
                SimpleEntry action = (SimpleEntry) this.clq.take();

                handleTask(action);

                this.sleep(3); /* Debug to slow down te queue */
            } catch (InterruptedException ignore) {
                /* Shutting down the thread.
                 * Drain our thread safe queue to a simple LinkedList
                 * and process it before we return
                 */
                if (this.clq.size() > 0) { 
                    LinkedList<SimpleEntry> queue = new LinkedList() {
                    };
                    this.clq.drainTo(queue);
                    for (int i = 0; i <= queue.size(); i++) {
                        handleTask((SimpleEntry) queue.pop());
                    }
                }
                return;
            }
        }
    }

    private void handleTask(SimpleEntry entry) {
        if (entry != null) {
            ScoreType event = (ScoreType) entry.getKey();
            String player = (String) entry.getValue();

            Score score = new Score();
            if (this.scores.containsKey(player)) {
                score = this.scores.get(player);
            }
            score.incrementScore(event);
            this.scores.put(player, score);
            System.out.println(String.format("Got points %d from player %s for %s- Now on: %d - Level: %d", event.getPoints(), player, event, score.getScore(), score.getLevel()));
        }
    }
}
