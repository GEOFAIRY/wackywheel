package com.gmail.kyrans19.WackyWheel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.json.simple.parser.JSONParser;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

/**
 * class to handle reading and writing of json to an external file
 */
public class WackyWheelReadWrite {
    /**
     * method to write the home list to a json file
     * 
     * @throws Exception needed for writing to a file
     */
    public static void writeLocationToJson(Location location)
            throws Exception {
                Gson gson = new Gson();

                WackyWheelLocationSupport locationSupport = new WackyWheelLocationSupport(location);
                String json = gson.toJson(locationSupport);
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("plugins\\WackyWheel\\locations.json"));
                    writer.write(json);
                    writer.close();
                } catch (IOException e) {
                    new File("plugins\\WackyWheel\\").mkdirs();
                    BufferedWriter writer = new BufferedWriter(new FileWriter("plugins\\WackyWheel\\locations.json"));
                    writer.write(json);
                    writer.close();
                }
    }

    /**
     * method to read homes from a json and place in the home support array
     * 
     * @throws Exception need for reading a file
     */
    public static Location readHomeFromJson() throws Exception {
        FileReader reader = new FileReader("plugins\\TestPlugin\\locations.json");
        JSONParser jsonParser = new JSONParser();
        String output = jsonParser.parse(reader).toString();

        Type collectionType = new TypeToken<WackyWheelLocationSupport>(){
            private static final long serialVersionUID = 1L;
        }.getType();
        Gson gson = new Gson();
        WackyWheelLocationSupport locationSup = gson.fromJson(output, collectionType);
        Location location = new Location(Bukkit.getServer().getWorld(locationSup.world), locationSup.x, locationSup.y, locationSup.z);
        return location;
    }
}
