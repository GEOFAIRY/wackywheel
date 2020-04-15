/*
   Program by: Kyran Stagg with partial help from Bryan Lim
   Slob on me knob
   mo bamba

 */

package com.gmail.kyrans19.WackyWheel;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Command Executor class, checks input command and does what it needs to.
 */
public class WackyWheelCommandExecutor implements CommandExecutor {
    private WackyWheel wackyWheel;
    public WackyWheelModel wheelInstance;

    WackyWheelCommandExecutor(WackyWheel plugin) {
        this.wackyWheel = plugin;
    }


    /**
     * method that is executed when a given command is executed by a command sender type
     *
     * @param sender CommandSender the entity that executed the command
     * @param cmd    Command the command type
     * @param label  String god knows what
     * @param args   String the command arguments after the command
     * @return Boolean if the command was found
     */
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("setWheelLocation")){
            return setWheelLocation(sender, args);
        } else if (cmd.getName().equalsIgnoreCase("version")) {
            return version(sender, args);
        } else {
            return false;
        }
    }



    private boolean setWheelLocation(CommandSender sender, String[] args) {
        Location newWheelLocation;
        if (args.length == 3) {
            Float x = Float.parseFloat(args[1]);  
            Float y = Float.parseFloat(args[2]);  
            Float z = Float.parseFloat(args[3]);  
            newWheelLocation = new Location(sender.getServer().getWorlds().get(0), x, y, z);
        } else if (sender instanceof Player) {
            newWheelLocation = ((Player)sender).getLocation();
        } else {
            return false;
        }

        try {
            WackyWheelReadWrite.writeLocationToJson(newWheelLocation);
        } catch (Exception e) {
            sender.sendMessage("WackyWheel unable to save new location");    
        }

        wheelInstance = new WackyWheelModel(newWheelLocation);
        
        sender.sendMessage("New Wheel Created");
        return true;
    }

    /**
     * method to return the version number to the sender
     * 
     * @param sender CommandSender the player who executed the command
     * @param args   String[] the command arguments
     * @return boolean command success or failure
     */
    private boolean version(CommandSender sender, String[] args) {
        sender.sendMessage(String.format("WackyWheel current version: %s", wackyWheel.getVersion()));
        return true;
    }
}