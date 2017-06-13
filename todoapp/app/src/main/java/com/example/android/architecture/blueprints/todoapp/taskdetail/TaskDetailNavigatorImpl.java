package com.example.android.architecture.blueprints.todoapp.taskdetail;

import com.example.android.architecture.blueprints.todoapp.NavigatingActivity;
import com.example.android.architecture.blueprints.todoapp.addedittask.AddEditTaskActivity;
import com.example.android.architecture.blueprints.todoapp.addedittask.AddEditTaskFragment;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Intent;

import static android.arch.lifecycle.Lifecycle.State.CREATED;
import static android.arch.lifecycle.Lifecycle.State.DESTROYED;
import static com.example.android.architecture.blueprints.todoapp.taskdetail.TaskDetailActivity.DELETE_RESULT_OK;
import static com.example.android.architecture.blueprints.todoapp.taskdetail.TaskDetailActivity.EXTRA_TASK_ID;
import static com.example.android.architecture.blueprints.todoapp.taskdetail.TaskDetailFragment.REQUEST_EDIT_TASK;

/**
 * Created by gauravbhola on 13/06/17.
 */

public class TaskDetailNavigatorImpl implements TaskDetailNavigator, LifecycleObserver {

    public NavigatingActivity mActivity;

    public  TaskDetailNavigatorImpl(NavigatingActivity activity) {
        mActivity = activity;
        activity.getLifecycle().addObserver(this);
    }

    @Override
    public void onTaskDeleted() {
        mActivity.setResult(DELETE_RESULT_OK);
        // If the task was deleted successfully, go back to the list.
        mActivity.finish();
    }

    @Override
    public void onStartEditTask() {
        String taskId = mActivity.getIntent().getStringExtra(EXTRA_TASK_ID);
        Intent intent = new Intent(mActivity, AddEditTaskActivity.class);
        intent.putExtra(AddEditTaskFragment.ARGUMENT_EDIT_TASK_ID, taskId);
        mActivity.startActivityForResult(intent, REQUEST_EDIT_TASK);
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onOwnerDestroyed() {
        if (mActivity.getLifecycle().getCurrentState() == DESTROYED) {
            mActivity.setNavigator(null);
            mActivity.getLifecycle().removeObserver(this);
            return;
        }
    }
}
