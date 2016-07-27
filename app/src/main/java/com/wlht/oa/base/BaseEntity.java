package com.wlht.oa.base;

import com.wlht.oa.bean.Result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import rx.Observable;

/**
 * Created by baixiaokang on 16/4/26.
 */
public interface BaseEntity {
    class BaseBean implements Serializable {
        public long id;
        public String objectId;
        public Map<String, String> param;
    }

    interface IListBean {
        default Observable getPageAt(int page){
            return Observable.empty();
        }
        void setParam(Map<String, String> param);

    }

    abstract class ListBean extends BaseBean implements IListBean {
        @Override
        public  void setParam(Map<String, String> param) {
            this.param=param;
        }
    }
}
