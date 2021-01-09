package it.filippocavallari.keybindings;

import it.filippocavallari.keybindings.utils.Pair;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ClickManager {

    private static ClickManager clickManager;

    private final Map<Integer, Pair<Long,Integer>> clicks = new HashMap<>();

    private ClickManager(){}

    public static ClickManager get() {
        if(clickManager == null){
            clickManager = new ClickManager();
        }
        return clickManager;
    }

    public void registerKeyPressed(final int keyCode){
        final Pair<Long, Integer> count = clicks.getOrDefault(keyCode, new Pair<>(Date.from(Instant.now()).getTime(),0));
        count.setSecond(count.getSecond() + 1);
        clicks.put(keyCode, count);
    }

    public int getClicks(final int keyCode){
        updateClicks(keyCode);
        return clicks.getOrDefault(keyCode, new Pair<>(Date.from(Instant.now()).getTime(),0)).getSecond();
    }

    private void updateClicks(final int keyCode){
        final Pair<Long, Integer> count = clicks.getOrDefault(keyCode, new Pair<>(Date.from(Instant.now()).getTime(),0));
        final long currentTime = Date.from(Instant.now()).getTime();
        final double millisecondsDiff = (double)(currentTime-count.getFirst());
        if(count.getSecond() > 0){
            final double millisecondsPerClick = 1000.0/count.getSecond();
            if(millisecondsDiff >= millisecondsPerClick){
                count.setSecond(count.getSecond() - (int)(Math.max(1, millisecondsDiff / millisecondsPerClick / 1000)));
                if(count.getSecond() < 0 ){
                    count.setSecond(0);
                }
                clicks.put(keyCode, count);
                count.setFirst(currentTime);
            }
        }
    }

}
