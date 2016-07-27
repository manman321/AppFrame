package com.wlht.oa.base.net;

import com.wlht.oa.base.BaseFragment;
import com.wlht.oa.base.exception.ApiException;

public class RxSubscriber<T> extends ErrorSubscriber<T> {
        private BaseFragment fragment;
        public RxSubscriber(BaseFragment baseFragment)
        {
            fragment = baseFragment;
        }

        @Override
        public void onStart() {
            super.onStart();
            fragment.onReqStart();
        }

        @Override
        public void onCompleted() {
            fragment.onReqCompleted();
        }

        @Override
        protected void onError(ApiException ex) {
            fragment.onReqError(ex);
        }


        @Override
        public void cancel() {
            super.cancel();
            fragment.onReqCompleted();
        }

    @Override
        public void onNext(T t) {

        }




    }


