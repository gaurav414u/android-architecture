/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.architecture.blueprints.todoapp;

import com.example.android.architecture.blueprints.todoapp.Navigator;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class NavigatingActivity<T extends Navigator> extends AppCompatActivity implements LifecycleRegistryOwner {
    LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);
    T mNavigator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNavigator = initNavigator();
    }

    @Nullable
    public abstract T initNavigator();

    public void setNavigator(@Nullable T navigator) {
        mNavigator = navigator;
    }

    @Nullable
    public T getNavigator() {
        return mNavigator;
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return mLifecycleRegistry;
    }
}
