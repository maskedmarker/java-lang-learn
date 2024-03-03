# Selector

## three selection key set
A selector maintains three sets of selection keys:
The key set contains the keys representing the current channel registrations of this selector.
The selected-key set is the set of keys such that each key's channel was detected to be ready for at least one of the operations identified in the key's interest set during a prior selection operation.
The cancelled-key set is the set of keys that have been cancelled but whose channels have not yet been deregistered.This set is not directly accessible. The cancelled-key set is always a subset of the key set.

Selector内部维护的3个集合:registration key set,selected-key set,cancelled-key set.
selection operation指的就是对Selector.select()方法的调用.
cancelled-key set

## selection key set operation
All three sets are empty in a newly-created selector.
A key is added to a selector's key set as a side effect of registering a channel via the channel's register method. Cancelled keys are removed from the key set during selection operations. The key set itself is not directly modifiable.
A key is added to its selector's cancelled-key set when it is cancelled, whether by closing its channel or by invoking its cancel method. 
Cancelling a key will cause its channel to be deregistered during the next selection operation, at which time the key will removed from all of the selector's key sets.
Keys are added to the selected-key set by selection operations. 
A key may be removed directly from the selected-key set by invoking the set's remove method or by invoking the remove method of an iterator obtained from the set. 
Keys are never removed from the selected-key set in any other way; they are not, in particular, removed as a side effect of selection operations. Keys may not be added directly to the selected-key set.


java.nio.channels.SelectionKey.cancel
Requests that the registration of this key's channel with its selector be cancelled. Upon return the key will be invalid and will have been added to its selector's cancelled-key set. The key will be removed from all of the selector's key sets during the next selection operation.

registration key set只能通过SelectableChannel.register()添加,不能通过其他渠道添加;只能通过关闭channel或者调用SelectionKey.cancel()方法移除;
cancelled-key set只能通过SelectionKey.cancel()方法添加(此时还不会从registration key set中移除);在下次Selector.select()方法执行时,将对应的key从cancelled-key set和registration key set中移除;
selected-key set只能通过Selector.select()方法调用的副作用自动添加;只能通过Set.remove()或Iterator.remove()移除.


# SocketChannel
只有提前配置为non-blocking,调用SocketChannel.read()才会非阻塞,否则调用read方法时会被阻塞.
