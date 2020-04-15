package com.gmail.kyrans19.WackyWheel;

import java.util.UUID;
import org.bukkit.Location;

public class WackyWheelLocationSupport {
    UUID world;
    double x;
    double y;
    double z;

    WackyWheelLocationSupport(UUID worldInput, double xInput, double yInput, double zInput) {
        this.world = worldInput;
        this.x = xInput;
        this.y = yInput;
        this.z = zInput;
    }

    WackyWheelLocationSupport(Location locationInput) {
        this.world = locationInput.getWorld().getUID();
        this.x = locationInput.getX();
        this.y = locationInput.getY();
        this.z = locationInput.getZ();
    }
}