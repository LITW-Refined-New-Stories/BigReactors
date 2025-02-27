package erogenousbeef.bigreactors.common.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemIngot extends ItemBase {

    public static final String[] TYPES = { "ingotYellorium", "ingotCyanite", "ingotGraphite", "ingotBlutonium",
        "dustYellorium", "dustCyanite", "dustGraphite", "dustBlutonium", "ingotLudicrite", "dustLudicrite" };

    public static final String[] MATERIALS = { "Yellorium", "Cyanite", "Graphite", "Blutonium", "Ludicrite" };

    public ItemIngot() {
        super("ingot");
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }

    @Override
    protected int getNumberOfSubItems() {
        return TYPES.length;
    }

    @Override
    protected String[] getSubItemNames() {
        return TYPES;
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        int idx = Math.min(TYPES.length - 1, itemStack.getItemDamage());
        return "item." + TYPES[idx];
    }

    @Override
    public void getSubItems(Item item, CreativeTabs creativeTabs, List list) {
        for (int i = 0; i < TYPES.length; i++) {
            list.add(new ItemStack(this, 1, i));
        }
    }

    public static boolean isFuel(int itemDamage) {
        return itemDamage == 0 || itemDamage == 3;
    }

    public static boolean isWaste(int itemDamage) {
        return itemDamage == 1;
    }

    public static boolean isGraphite(int itemDamage) {
        return itemDamage == 2;
    }

    public ItemStack getItemStackForType(String typeName) {
        for (int i = 0; i < TYPES.length; i++) {
            if (TYPES[i].equals(typeName)) {
                return new ItemStack(this, 1, i);
            }
        }

        return null;
    }

    public ItemStack getIngotItem(String name) {
        return getItemStackForType("ingot" + name);
    }

    public ItemStack getDustItem(String name) {
        return getItemStackForType("dust" + name);
    }

}
