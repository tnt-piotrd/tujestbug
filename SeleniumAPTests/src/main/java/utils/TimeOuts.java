package utils;

import java.time.Duration;

public enum TimeOuts {
    _30_SECONDS(30);

    private final int inSeconds;

    TimeOuts(int inSeconds) {
        this.inSeconds = inSeconds;
    }

    public Duration getDuration() {
        return Duration.ofSeconds(inSeconds);
    }
}
