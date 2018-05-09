package com.ordinefacile.root.ordinefacile.data.db.push_history;


import java.util.List;
import rx.Observable;

/**
 * Created by user on 4/26/2018.
 */

public interface PushHistoryOperations {

    Observable insertPush(PushHistory p);

    Observable<List<PushHistory>> read();
}
