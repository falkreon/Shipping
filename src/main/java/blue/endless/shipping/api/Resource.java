package blue.endless.shipping.api;

import net.minecraft.component.ComponentChanges;

public record Resource<T>(T object, ComponentChanges components) {
	
	public Resource(T object) {
		this(object, ComponentChanges.EMPTY);
	}
	
	public boolean hasComponents() {
		return !components.isEmpty();
	}
}
