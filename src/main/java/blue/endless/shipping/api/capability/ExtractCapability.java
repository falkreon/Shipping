package blue.endless.shipping.api.capability;

import blue.endless.shipping.api.OptionalStack;
import blue.endless.shipping.api.Resource;

/**
 * Allows a consumer to extract items
 * @param <T> the type of Resource (e.g. Item, Fluid)
 */
public interface ExtractCapability<T> {
	/**
	 * Attempts to extract resources from a specific slot of this resource storage. Whatever is
	 * successfully extracted is returned. If the slot does not exist, a blank stack will be returned.
	 * @param slot the slot to extract from
	 * @param amount the maximum number of resources to extract
	 * @param simulate if true, no changes will be committed
	 * @return the resources that could be extracted
	 */
	public OptionalStack<T> extract(int slot, long amount, boolean simulate);
	
	/**
	 * Attempts to extract a specific resource from this resource storage. Whatever is successfully
	 * extracted is returned. If the resource does not exist, a blank stack will be returned.
	 * Resources may come from multiple source stacks or slots in the storage.
	 * 
	 * <p>This method is sensitive to ComponentChanges. Only resources which match the exact
	 * TransferVariant will be returned.
	 * 
	 * @param resource the exact resource to extract
	 * @param amount the maximum number of resources to extract
	 * @param simulate if true, no changes will be committed
	 * @return the resources that could be extracted
	 */
	public OptionalStack<T> extract(Resource<T> resource, long amount, boolean simulate);
	
	/**
	 * Attempts to extract a specific resource from this resource storage. his method is NOT
	 * sensitive to ComponentChanges, and will grab any TransferVariant that matches, but resources
	 * from only one variant will be returned.
	 * 
	 * <p>Let's say we have a fluid storage container with three slots/tanks arranged like so:
	 * <ul>
	 *   <li>3 droplets WATER (no component changes)
	 *   <li>2 droplets WATER (no component changes)
	 *   <li>7 droplets WATER (component changes)
	 * </ul>
	 * 
	 * If you ask an ExtractCapability of this container for 10 droplets of WATER, you may get 5 or
	 * you may get 7. No guarantee is made that the largest stack possible is returned, and it will
	 * frequently depend on the order the resources are arranged in.
	 * 
	 * @param resource the resource to extract
	 * @param amount the maximum number of resources to extract
	 * @param simulate if true, no changes will be committed
	 * @return the resources that could be extracted
	 */
	public OptionalStack<T> extract(T resource, long amount, boolean simulate);
}
