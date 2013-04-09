package me.chaseoes.supercraftbrothers.classes;

import me.chaseoes.supercraftbrothers.CraftBrother;
import me.chaseoes.supercraftbrothers.SuperCraftBrothers;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SCBClass {

    String name;
    ConfigurationSection config;

    public SCBClass(String n) {
        name = n;
        config = SuperCraftBrothers.getInstance().getConfig().getConfigurationSection("classes." + name);
    }

    // Apply the class to a player (returns true if it was successful).
    @SuppressWarnings("deprecation")
    public boolean apply(CraftBrother player) {
        // Check that the class exists.
        if (config == null) {
            player.getPlayer().sendMessage(ChatColor.RED + "That class does not exist.");
            clearInventory(player.getPlayer());
            return false;
        }

        try {

            // Clear their inventory.
            clearInventory(player.getPlayer());

            // Loop through potion effects.
            for (String effect : SuperCraftBrothers.getInstance().getConfig().getStringList("classes." + name + ".potion-effects")) {
                String[] effects = effect.split("\\.");
                PotionEffectType et = PotionEffectType.getByName(effects[0]);
                int amplifier = Integer.parseInt(effects[1]) - 1;
                int duration = 0;
                if (effects[2].equalsIgnoreCase("forever")) {
                    duration = Integer.MAX_VALUE;
                } else {
                    duration = Integer.parseInt(effects[2]) * 20;
                }
                PotionEffect e = new PotionEffect(et, duration, amplifier);
                player.getPlayer().addPotionEffect(e);
            }

            // Loop through armor items.
            ItemStack[] armor = new ItemStack[4];
            int armorindex = 0;
            for (String armortype : SuperCraftBrothers.getInstance().getConfig().getConfigurationSection("classes." + name + ".armor").getKeys(false)) {
                String[] items = config.getString("armor." + armortype).split("\\.");
                ItemStack i = new ItemStack(Material.getMaterial(Integer.parseInt(items[0])));

                if (Integer.parseInt(items[0]) == 0) {
                    i = new ItemStack(Material.AIR);
                }

                int enchantindex = 0;
                for (String enchant : items) {
                    if (enchantindex != 0) {
                        String[] enchantment = enchant.split("\\-");
                        Enchantment e = Enchantment.getByName(enchantment[0]);
                        int level = 1;
                        if (enchantment.length > 1) {
                            level = Integer.parseInt(enchantment[1]);
                        }

                        i.addUnsafeEnchantment(e, level);
                    }
                    enchantindex++;
                }

                armor[armorindex] = i;
                armorindex++;
            }

            // Add armor items.
            player.getPlayer().getInventory().setHelmet(armor[0]);
            player.getPlayer().getInventory().setChestplate(armor[1]);
            player.getPlayer().getInventory().setLeggings(armor[2]);
            player.getPlayer().getInventory().setBoots(armor[3]);

            // Loop through inventory items.
            for (String fullitem : SuperCraftBrothers.getInstance().getConfig().getStringList("classes." + name + ".inventory")) {
                String[] items = fullitem.split("\\.");
                String[] item = items[0].split("\\,");

                int id = Integer.parseInt(item[0]);
                byte data = 0;
                short damage = 0;

                if (item.length > 1) {
                    if (id != Material.POTION.getId()) {
                        data = (byte) Integer.parseInt(item[1]);
                    } else {
                        damage = Short.parseShort(item[1]);
                    }
                }
                if (item.length > 2) {
                    damage = Short.parseShort(item[2]);
                }

                MaterialData md = new MaterialData(id, data);
                ItemStack i = md.toItemStack();
                i.setAmount(Integer.parseInt(items[1]));
                i.setDurability(damage);

                int enchantindex = 0;
                for (String enchant : items) {
                    if (enchantindex > 1) {
                        String[] enchantment = enchant.split("\\-");
                        Enchantment e = Enchantment.getByName(enchantment[0]);
                        int level = 1;
                        if (enchantment.length > 1) {
                            level = Integer.parseInt(enchantment[1]);
                        }

                        i.addUnsafeEnchantment(e, level);
                    }
                    enchantindex++;
                }

                player.getPlayer().getInventory().addItem(i);
            }

            player.setCurrentClass(this);

            player.getPlayer().updateInventory();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            player.getPlayer().sendMessage(ChatColor.RED + "An error occoured while attempting to change your class.");
            clearInventory(player.getPlayer());
            return false;
        }
    }

    public String getName() {
        return name;
    }

    @SuppressWarnings("deprecation")
    public static void clearInventory(Player player) {
        player.getInventory().clear();
        player.getInventory().setHelmet(new ItemStack(Material.AIR));
        player.getInventory().setChestplate(new ItemStack(Material.AIR));
        player.getInventory().setLeggings(new ItemStack(Material.AIR));
        player.getInventory().setBoots(new ItemStack(Material.AIR));
        player.setItemOnCursor(new ItemStack(Material.AIR));

        for (PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
        }

        player.updateInventory();
    }

}
