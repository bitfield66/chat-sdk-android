/*
 * Created by Itzik Braun on 12/3/2015.
 * Copyright (c) 2015 deluge. All rights reserved.
 *
 * Last Modification at: 3/12/15 4:35 PM
 */

package com.braunster.androidchatsdk.firebaseplugin.firebase.wrappers;

@Deprecated
public class InMessagesListener {

//    private static final String TAG = InMessagesListener.class.getSimpleName();
//    private static final boolean DEBUG = Debug.IncomingMessagesListener;
//    private Handler handler;
//
//    private boolean isNew = false;
//    private long creationTime;
//    private String threadEntityId;
//
//    private Deferred<BThread, Void, Void> deferred;
//
//    public InMessagesListener(Handler handler, String threadEntiyId, Deferred<BThread, Void, Void>  deferred){
//        super(ChildEvent);
//        this.deferred = deferred;
//        this.threadEntityId = threadEntiyId;
//        this.handler = handler;
//        creationTime = System.currentTimeMillis();
//    }
//
//    @Override
//    public void onChildAdded(final DataSnapshot dataSnapshot, String s) {
//        if (DEBUG) Timber.v("CoreMessage has arrived, Alive: %s", isAlive());
//        if (isAlive())
//            Executor.getInstance().execute(new Runnable() {
//                @Override
//                public void run() {
//                    if (DEBUG) Timber.v("CoreMessage has arrived - execute, Alive: %s", isAlive());
//                    Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
//
//                    // Rejecting no value messages.
//                    if (dataSnapshot.getValue() == null)
//                    {
//                        if (deferred != null  &&  deferred.isPending())
//                            deferred.reject(null);
//                        return;
//                    }
//
//                    BThread thread = DaoCore.fetchOrCreateEntityWithEntityID(BThread.class, threadEntityId);
//                    MessageWrapper wrapper = new MessageWrapper(dataSnapshot);
//                    wrapper.getModel().setThread(thread);
//
//                    wrapper.setDelivered(BMessage.Delivered.Yes);
//
//                    // Checking for null sender and that the sender isn't the current user.
//                    // This will make sure we wont notify user for his own messages.
//                    if (wrapper.model.getSender() != null &&
//                            !wrapper.model.getSender().isMe())
//                    {
//                        // Set the message as new if was told from creator,
//                        // Or if the date of the message is later then the creation of this object.
//                        if (isNew)
//                            wrapper.model.setIsRead(false);
//                        else if (creationTime < wrapper.model.getDate().toDate().getTime())
//                            wrapper.model.setIsRead(false);
//                    }
//
//
//
//                    // Checking to see if this thread was deleted.
//                    if (thread.isDeleted())
//                    {
//                        if (DEBUG) Timber.v("CoreThread was Deleted");
//                        return;
//                    }
//
//                    // Mark the thread as having unread messages if this message
//                    // doesn't already exist on the thread
//                    if (wrapper.model.getThread() == null)
//                        thread.setHasUnreadMessages(true);
//
//                    // Update the thread
//                    DaoCore.updateEntity(thread);
//
//                    // Update the message.
//                    wrapper.model.setThread(thread);
//                    DaoCore.updateEntity(wrapper.model);
//
//                    if (deferred != null &&  deferred.isPending())
//                        deferred.resolve(thread);
//
//                    CoreMessage message = new CoreMessage();
//                    message.what = 1;
//                    message.obj = wrapper.model;
//                    handler.sendMessageAtFrontOfQueue(message);
//                }
//            });
//    }
//
//    public void setNew(boolean isNew) {
//        this.isNew = isNew;
//    }
}
