package com.gmail.kyrans19.WackyWheel;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.FaceAttachable;


public class WackyWheelModel {
    Location location;
    final Location centerLocation;

    WackyWheelModel(Location locationInput) {
        this.location = locationInput;
        this.centerLocation = locationInput;
        buildWheel();
    }

    public void buildWheel() {
        Block buttonLocation = this.location.getBlock();
        buttonLocation.setType(Material.getMaterial("STONE_BUTTON"));
        if (buttonLocation.getBlockData() instanceof FaceAttachable) {
            FaceAttachable data = (FaceAttachable) buttonLocation.getBlockData();
            data.setAttachedFace(FaceAttachable.AttachedFace.FLOOR);
        }
        this.location.add(0, -1, 0).getBlock().setType(Material.getMaterial("BEDROCK"));
    }
}