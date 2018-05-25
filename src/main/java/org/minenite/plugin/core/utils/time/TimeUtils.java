package org.minenite.plugin.core.utils.time;

import com.google.inject.Inject;
import org.minenite.plugin.MineNite;
import org.minenite.plugin.core.objects.interfaces.CountdownFunction;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

// ------------------------------
// Copyright (c) PiggyPiglet & AndrewAubury 2018
// https://www.piggypiglet.me
// https://www.andrewa.pw
// ------------------------------
public final class TimeUtils {
    private MineNite mineNite = MineNite.getPlugin(MineNite.class);

    public void countdown(long seconds, CountdownFunction countdownFunction) {
        long time = seconds / 20;
        mineNite.getScheduler().runTaskLater(mineNite, countdownFunction, time);
    }
}
