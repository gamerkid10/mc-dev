package net.minecraft.server;

import java.util.List;

public class InventorySubcontainer implements IInventory {

    private String a;
    private int b;
    private ItemStack[] items;
    private List d;

    public InventorySubcontainer(String s, int i) {
        this.a = s;
        this.b = i;
        this.items = new ItemStack[i];
    }

    public ItemStack getItem(int i) {
        return this.items[i];
    }

    public ItemStack splitStack(int i, int j) {
        if (this.items[i] != null) {
            ItemStack itemstack;

            if (this.items[i].count <= j) {
                itemstack = this.items[i];
                this.items[i] = null;
                this.update();
                return itemstack;
            } else {
                itemstack = this.items[i].a(j);
                if (this.items[i].count == 0) {
                    this.items[i] = null;
                }

                this.update();
                return itemstack;
            }
        } else {
            return null;
        }
    }

    public ItemStack splitWithoutUpdate(int i) {
        if (this.items[i] != null) {
            ItemStack itemstack = this.items[i];

            this.items[i] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    public void setItem(int i, ItemStack itemstack) {
        this.items[i] = itemstack;
        if (itemstack != null && itemstack.count > this.getMaxStackSize()) {
            itemstack.count = this.getMaxStackSize();
        }

        this.update();
    }

    public int getSize() {
        return this.b;
    }

    public String getName() {
        return this.a;
    }

    public int getMaxStackSize() {
        return 64;
    }

    public void update() {
        if (this.d != null) {
            for (int i = 0; i < this.d.size(); ++i) {
                ((IInventoryListener) this.d.get(i)).a(this);
            }
        }
    }

    public boolean a_(EntityHuman entityhuman) {
        return true;
    }

    public void startOpen() {}

    public void f() {}
}
