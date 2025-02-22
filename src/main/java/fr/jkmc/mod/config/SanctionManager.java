package fr.jkmc.mod.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SanctionManager {
    private File configFile;
    private FileConfiguration config;

    public SanctionManager(File dataFolder) {
        configFile = new File(dataFolder, "config.yml");

        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public void loadSanctions() {
        List<Map<?, ?>> sanctions = config.getMapList("sanctions");

        for (Map<?, ?> sanction : sanctions) {
            String type = (String) sanction.get("type");
            String time = (String) sanction.get("time");
            System.out.println("Sanction: Type=" + type + ", Durée=" + time);
        }
    }
}

