package com.koens.parkour.util;

public class TimeUtils {
    public String secondsToHHMMSS(int seconds) {
        if (seconds < 60) {
            return "00.00." + seconds;
        }
        else if (seconds >= 3600) {
            int hours = seconds / 3600;
            int remainingseconds = seconds % 3600;
            int minutes = remainingseconds / 60;
            int finalseconds = remainingseconds % 60;
            if (minutes == 0)
                return hours + ".00." + finalseconds;
            if (seconds == 0)
                return hours + "." + minutes + ".00";
            return hours + "." + minutes + "." + finalseconds;
        }
        else if (seconds < 3600 && seconds >= 60) {
            int minutes = seconds / 60;
            int remain = seconds % 60;
            if (remain == 0)
                return "00." + minutes + ".00";
            return "00." + minutes + "." + remain;
        }
        return "00.00.00";
    }
}
