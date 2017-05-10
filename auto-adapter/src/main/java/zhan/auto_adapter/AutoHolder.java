package zhan.auto_adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import java.util.Map;

/**
 * Created by ruzhan on 2017/5/1.
 */

public abstract class AutoHolder<M> extends RecyclerView.ViewHolder {

    public static final String LISTENER = "OnAutoHolderListener";

    private Map<String, Object> dataMap;

    public AutoHolder(View itemView, Map<String, Object> dataMap) {
        super(itemView);
        this.dataMap = dataMap;
    }

    protected OnAutoHolderListener getOnAutoHolderListener() {
        if (dataMap != null) {
            return (OnAutoHolderListener) dataMap.get(LISTENER);
        }
        return null;
    }

    @SuppressWarnings("unchecked") protected <T> T get(String key) {
        if (dataMap != null) {
            return (T) dataMap.get(key);
        }
        return null;
    }

    public abstract void bind(int position, M bean);
}
