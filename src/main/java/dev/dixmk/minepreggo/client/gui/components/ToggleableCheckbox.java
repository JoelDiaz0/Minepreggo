package dev.dixmk.minepreggo.client.gui.components;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class ToggleableCheckbox extends Checkbox {
	
	private final List<ToggleableCheckbox> group;
    private final Runnable onSelect;
    
    public ToggleableCheckbox(int x, int y, int width, int height, Component label, boolean checked, List<ToggleableCheckbox> group, @Nullable Runnable onSelect) {
        super(x, y, width, height, label, checked);
        this.group = group;
		this.onSelect = onSelect;

    }
    
    public ToggleableCheckbox(int x, int y, int width, int height, MutableComponent label, boolean checked, List<ToggleableCheckbox> group, @Nullable Runnable onSelect) {
        super(x, y, width, height, label, checked);
        this.group = group;
		this.onSelect = onSelect;
    }
    
     
	@Override
    public void onPress() {
        // Deselect all in the group (including this one)
        for (ToggleableCheckbox cb : group) {
            if (cb.selected()) {
                cb.forceSet(false);
            }
        }

        // Select only this one
        this.forceSet(true);
        
        if (onSelect != null) {
            onSelect.run();
        }
    }

    // Helper: because Checkbox doesn’t expose setSelected
    private void forceSet(boolean value) {
        if (this.selected() != value) {
            super.onPress(); // toggles state
        }
    }
    
    public static class Builder {
    	
    }
    
}