/*
 * Created by Itzik Braun on 12/3/2015.
 * Copyright (c) 2015 deluge. All rights reserved.
 *
 * Last Modification at: 3/12/15 4:35 PM
 */

package com.braunster.androidchatsdk.firebaseplugin.firebase.wrappers;

import com.google.firebase.database.DataSnapshot;

@Deprecated
public class UserAddedListener {

//    private static final boolean DEBUG = Debug.UserAddedToThreadListener;
//
//    private static List<String> usersIds = new ArrayList<String>();
//    private String threadID, observedUserId;
//    private Handler handler;
//    private FirebaseEventCombo combo;
//    private FirebaseGeneralEvent userDetailsChangeListener;
//
//    public static UserAddedListener getNewInstance(String observedUserId, String threadID, Handler handler) {
//        UserAddedListener userAddedToThreadListener = new UserAddedListener(observedUserId, threadID,  handler);
//        return userAddedToThreadListener;
//    }
//
//    public UserAddedListener(String obeservedUserId, String threadID, Handler handler){
//        super(ChildEvent);
//        this.observedUserId = obeservedUserId;
//        this.handler = handler;
//        this.threadID = threadID;
//    }
//
//
//
//    @Override
//    public void onChildAdded(final DataSnapshot dataSnapshot, String s) {
//        if (DEBUG) Timber.v("CoreUser Added to thread, Alive: %s", isAlive());
//        doLogic(dataSnapshot);
//    }
//
//    @Override
//    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//        super.onChildChanged(dataSnapshot, s);
//        doLogic(dataSnapshot);
//    }

    private void doLogic(final DataSnapshot dataSnapshot){
//        if (isAlive())
//            Executor.getInstance().execute(new Runnable() {
//                @Override
//                public void run() {
//                    android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
//
//                    BUser currentUser = NetworkManager.shared().a.core.currentUserModel();
//
//                    // If listener is old return,
//                    // We can check if he is old if the observed user id does not match the current user id.
//                    if (!currentUser.getEntityID().equals(observedUserId))
//                        return;
//
//                    // Find the user entity id.
//                    BPath path = BPath.pathWithPath(dataSnapshot.getRef().toString());
//                    final String userFirebaseID = path.idForIndex(1);
//
//                    BThread thread = DaoCore.fetchOrCreateEntityWithEntityID(BThread.class, threadID);
//
//                    BUser bUser = DaoCore.fetchOrCreateEntityWithEntityID(BUser.class, userFirebaseID);
//
//                    // Attaching the user to the thread if needed.
//                    if (!thread.hasUser(bUser))
//                        DaoCore.connectUserAndThread(bUser, thread);
//
//                    if (DEBUG) Timber.d("OnUserAdded: %s", bUser);
//
//                    Map<String, Object> values = null;
//                    try {
//                        values = (Map<String, Object>) dataSnapshot.getValue();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                    // Dont notify the system for the current user added.
//                    if (userFirebaseID.equals(currentUser.getEntityID())) {
//
//                        // Check to see if the thread was deleted from the user db.
//                        if (values != null && values.containsKey(DaoDefines.Keys.Deleted))
//                        {
//                            thread.setDeleted(true);
//                            DaoCore.updateEntity(thread);
//                        }
//                        return;
//                    }
//
//                    if (thread.getTypeSafely() != BThread.Type.Public) {
//
//                        // Check to see if the user has left this thread. If so we unlink it from the thread.
//                        if (values != null &&  values.containsKey(DaoDefines.Keys.Leaved))
//                        {
//                            UserThreadLink data =
//                                    DaoCore.fetchEntityWithProperties(UserThreadLink.class,
//                                            new Property[]{UserThreadLinkDao.Properties.ThreadId, UserThreadLinkDao.Properties.UserId}, thread.getId(), bUser.getId());
//
//                            if (data != null)
//                            {
//                                DaoCore.deleteEntity(data);
//                            }
//                        }
//                        else
//                        {
//                            // Users that are members of threads are shown in contacts
//                            currentUser.addContact(bUser);
//                        }
//                    }
//
//                    // Starting to listen to the user details changes.
//                    UserWrapper.initWithModel(bUser).metaOn();
//
//                    CoreMessage message = new CoreMessage();
//                    message.what = AppEvents.USER_ADDED_TO_THREAD;
//                    Bundle data = new Bundle();
//                    data.putString(FirebaseEventsManager.THREAD_ID, threadID);
//                    data.putString(FirebaseEventsManager.USER_ID, userFirebaseID);
//                    message.setData(data);
//                    handler.sendMessage(message);
//                }
//            });
    }
}
