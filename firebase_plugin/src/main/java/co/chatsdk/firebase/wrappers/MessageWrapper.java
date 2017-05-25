/*
 * Created by Itzik Braun on 12/3/2015.
 * Copyright (c) 2015 deluge. All rights reserved.
 *
 * Last Modification at: 3/12/15 4:35 PM
 */

package co.chatsdk.firebase.wrappers;

import co.chatsdk.firebase.FirebasePaths;

import co.chatsdk.core.StorageManager;
import co.chatsdk.core.dao.core.BMessage;
import co.chatsdk.core.dao.core.BUser;
import co.chatsdk.core.dao.core.DaoCore;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ChildEventListener;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.Map;

import co.chatsdk.core.dao.core.DaoDefines;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import timber.log.Timber;

public class MessageWrapper  {

    private final String TAG = this.getClass().getSimpleName();
    private static boolean DEBUG = true;
    private ChildEventListener readReceiptListener;
    private BMessage model;

    public MessageWrapper(BMessage model){
        this.model = model;
    }

    public MessageWrapper(DataSnapshot snapshot){
        this.model = StorageManager.shared().fetchOrCreateEntityWithEntityID(BMessage.class, snapshot.getKey());
        deserialize(snapshot);
    }

    Map<String, Object> serialize(){
        Map<String, Object> values = new HashMap<String, Object>();

        values.put(DaoDefines.Keys.Payload, model.getText());
        values.put(DaoDefines.Keys.Date, ServerValue.TIMESTAMP);
        values.put(DaoDefines.Keys.Type, model.getType());
        values.put(DaoDefines.Keys.UserFirebaseId, model.getSender().getEntityID());


        return values;
    }

    @SuppressWarnings("all") void deserialize(DataSnapshot snapshot) {

        Map<String, Object> value = (Map<String, Object>) snapshot.getValue();
        if (DEBUG) Timber.v("deserialize, Value: %s", value);
        if (value == null) return;
        if (value.containsKey(DaoDefines.Keys.Payload) && !value.get(DaoDefines.Keys.Payload).equals(""))
        {
            model.setText((String) value.get(DaoDefines.Keys.Payload));
        }

        if (value.containsKey(DaoDefines.Keys.Type) && !value.get(DaoDefines.Keys.Type).equals(""))
        {
            if (value.get(DaoDefines.Keys.Type) instanceof Integer)
                model.setType((Integer) value.get(DaoDefines.Keys.Type));
            else
                if (value.get(DaoDefines.Keys.Type) instanceof Long)
                    model.setType( ((Long) value.get(DaoDefines.Keys.Type)).intValue() );
        }

        if (value.containsKey(DaoDefines.Keys.Date) && !value.get(DaoDefines.Keys.Date).equals(""))
            model.setDate( new DateTime( (Long) value.get(DaoDefines.Keys.Date) ) );

        if (value.containsKey(DaoDefines.Keys.UserFirebaseId) && !value.get(DaoDefines.Keys.UserFirebaseId).equals(""))
        {
            String userEntityId = (String) value.get(DaoDefines.Keys.UserFirebaseId);
            BUser user = DaoCore.fetchEntityWithEntityID(BUser.class, userEntityId);

            // If there is no user saved in the db for this entity id,
            // Create a new one and do a once on it to get all the details.
            if (user == null)
            {
                user = StorageManager.shared().fetchOrCreateEntityWithEntityID(BUser.class, userEntityId);

                UserWrapper.initWithModel(user).once();
            }

            model.setSender(user);
        }

        // Updating the db
        DaoCore.updateEntity(model);
    }

    public Completable push() {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(final CompletableEmitter e) throws Exception {

                // Getting the message ref. Will be created if not exist.
                final DatabaseReference ref = ref();
                model.setEntityID(ref.getKey());

                DaoCore.updateEntity(model);

                ref.setValue(serialize(), ServerValue.TIMESTAMP, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError firebaseError, DatabaseReference firebase) {
                        if (firebaseError == null) {
                            e.onComplete();
                        } else {
                            e.onError(firebaseError.toException());
                        }
                    }
                });
            }
        });
    }
    
    public Completable send() {
        if(model.getThread() != null) {
            return push();
        }
        else {
            return null;
        }
    }
    
    /**
     * The message model will be updated after this call.
     **/
    public void setDelivered(int delivered){
        model.setDelivered(delivered);
    }
    
    private DatabaseReference ref(){
        if (StringUtils.isNotEmpty(model.getEntityID()))
        {
            return FirebasePaths.threadMessagesRef(model.getThread().getEntityID()).child(model.getEntityID());
        }
        else
        {
            return FirebasePaths.threadMessagesRef(model.getThread().getEntityID()).push();
        }
    }

    public BMessage getModel() {
        return model;
    }

}
