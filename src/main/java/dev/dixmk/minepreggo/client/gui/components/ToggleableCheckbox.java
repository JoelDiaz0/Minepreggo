package dev.dixmk.minepreggo.client.gui.components;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.network.chat.Component;

public class ToggleableCheckbox extends Checkbox {
	
	private final List<ToggleableCheckbox> group;
    private final Optional<Runnable> onSelect;
       
    public ToggleableCheckbox(ToggleableCheckbox.Builder builder) {
    	super(builder.x, builder.y, builder.width, builder.height, builder.label, builder.checked);
    	this.group = builder.group;
    	this.onSelect = Optional.ofNullable(builder.onSelect);
    }
       
	@Override
    public void onPress() {
        for (ToggleableCheckbox cb : group) {
            if (cb.selected()) {
            	cb.selected = false;
            }
        }
        this.selected = true;               
        onSelect.ifPresent(r -> r.run());     
    }

	public static ToggleableCheckbox.Builder builder(int x, int y, int width, int height, Component label, boolean checked) {
		return new ToggleableCheckbox.Builder(x, y, width, height, label, checked);
	}
    
    public static class Builder {
    	List<ToggleableCheckbox> group;
    	@Nullable Runnable onSelect;
    	int x;
    	int y; 
    	int width; 
    	int height; 
    	Component label;
    	boolean checked;
    	 		
    	protected Builder(int x, int y, int width, int height, Component label, boolean checked) {
    		this.x = x;
    		this.y = y;
    		this.width = width;
    		this.height = height;
    		this.label = label;
    		this.checked = checked;
    	}
    	
    	public Builder group(List<ToggleableCheckbox> group) {
    		this.group = group;
    		return this;
    	}
    		
    	public Builder onSelect(@Nullable Runnable onSelect) {
    		this.onSelect = onSelect;
    		return this;
    	}
    	
    	public ToggleableCheckbox build() {
    		return new ToggleableCheckbox(this);
    	}
    	
    }
}