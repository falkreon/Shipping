# Shipping

Transfer and Logistics API

![Mod icon](icon.png)<br>
(Icon by Aquaholic)




## Motivations

Similar to Transfer API, a thorny problem comes up when dealing with vanilla itemstacks: Components make copying an itemstack extremely expensive. We no longer have an effectively-sealed set of NBT classes which bound complexity on this operation, we just have to trust that modders make component initialization cheap, which they won't. Heck, Mojang is bound to make some expensive components. Mojang solves part of this issue by splitting components into a "default" ComponentMap (`Item::getComponents`) and a per-stack ComponentChanges (`ItemStack::getComponentChanges`). For some items, a stack's component changes will tend to be empty (e.g. `COOKED_PORKCHOP`). But other items, we will rarely see an unmodified stack (e.g. `PLAYER_HEAD`), and copying those items means creating arbitrary domain objects instead of NBT. So, especially in modded, copies are doing a fair bit more work than they used to.


The original Fabric Transfer API has a good intuition here: Most ItemStack copies aren't component changes, they're size changes. So you can split the "Item plus ComponentChanges" off from the count, which they now call ItemVariant, and structure your API around two-argument calls of ItemVariant + count. The limitation here is ItemVariant. There is a `TransferVariant<T>` generic, but it's not fully set free to do its work because there are some key inconsistencies in how Variants of different substances (items, fluids, etc.) work. The most egregious of these is the empty stack, which is a different singleton for each substance. Because of this, each variant kind (ItemVariant, FluidVariant, etc.) needs to implement its own stack merging and splitting behavior, and lots of error-prone code is repeated.


If you love your generics, though, you should set them free. Shipping defines a `Resource<T>` generic which can be used directly to represent any object-plus-component-changes. This is possible because there is no valid Resource that stands for an "empty" sentinel value: every `Resource<Item>` is "full". If it says it's `Items.AIR`, *it is affirmatively an air block Item*. If you really want to say "This is an item-plus-component-changes OR it's empty", then we have a better tool for that: `Optional<Resource<Item>>`. To smooth over some of the awkwardness of dealing with counts and components for `Optional<ResourceStack<Item>>`, Shipping provides `OptionalStack<Item>` which can, for example, "read-through" a count, reporting a zero count if the stack is empty. There are methods for merging ResourceStacks and OptionalStacks that work regardless of the resource.


The other thing about ItemStacks that's strange to me is a deep and pervasive need for defensive copies. Because ItemStacks are mutable, when you receive an ItemStack for an API call or lift it from a container, you need to copy it out, otherwise the object that gave the stack to you might just change the count in *your* stack. This is undesirable. All of Shipping's key transfer primitives, including Resource, ResourceStack, and OptionalStack, are immutable records. Once you have them, they're your copies, and they can be safely serialized or used off-thread.


Imagine if we were both mods, and we touched the same item, and our hands touched &U1F633;


## Usage

TODO

```java

//TODO

```
