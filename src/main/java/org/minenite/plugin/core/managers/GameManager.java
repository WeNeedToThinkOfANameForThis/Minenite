package org.minenite.plugin.core.managers;

// ------------------------------
// Copyright (c) PiggyPiglet & AndrewAubury 2018
// https://www.piggypiglet.me
// https://www.andrewa.pw
// ------------------------------

import com.google.inject.Singleton;
import lombok.Data;
import org.minenite.plugin.core.objects.enums.GameStatus;

@Data
@Singleton
public final class GameManager {
    GameStatus gameStatus;
}
