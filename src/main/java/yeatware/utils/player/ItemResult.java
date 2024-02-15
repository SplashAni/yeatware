package yeatware.utils.player;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import yeatware.utils.constants.ItemType;

import java.util.stream.IntStream;

import static yeatware.Main.mc;

public class ItemResult {
    private Item item;

    public ItemResult(Item item) {
        this.item = item;
    }

    public ItemResult(ItemType itemType) {
        item = IntStream.range(0, 9).mapToObj(i -> mc.player.getInventory().getStack(i)).filter(is -> isType(itemType, is.getItem())).findFirst().map(ItemStack::getItem).orElse(this.item);
    }

    public boolean isHotbar() {
        if (item == null) return false;
        return IntStream.range(0, 9).mapToObj(i -> mc.player.getInventory().getStack(i)).anyMatch(stackInSlot -> stackInSlot.getItem() == item);
    }

    public int slot() {
        if (item == null) return -1;
        for (int i = 0; i < mc.player.getInventory().size(); i++) {
            if (mc.player.getInventory().getStack(i).getItem() == item) return i;
        }
        return -1;
    }

    public boolean isType(ItemType type, Item item) {
        switch (type) {
            case Block -> {
                return item instanceof BlockItem;
            }
        }
        return false;
    }

}
