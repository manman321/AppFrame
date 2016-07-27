package com.wlht.oa.base.net;

import com.wlht.oa.base.exception.ApiException;

import rx.Subscriber;

public abstract class ErrorSubscriber<T> extends Subscriber<T> {
        @Override
        public void onError(Throwable e) {
            if(e instanceof ApiException){
                onError((ApiException)e);
            }else{
                onError(new ApiException(e,123));
            }
        }
        /**
         * 错误回调
         */
        protected abstract void onError(ApiException ex);


        public void cancel()
        {
            if (!this.isUnsubscribed())
            {
                this.unsubscribe();
            }
        }

        private void test()
        {

        }
    }