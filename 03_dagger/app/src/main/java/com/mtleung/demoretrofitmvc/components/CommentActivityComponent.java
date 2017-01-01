package com.mtleung.demoretrofitmvc.components;

import com.mtleung.demoretrofitmvc.interfaces.ActivityScope;
import com.mtleung.demoretrofitmvc.modules.CommentActivityModule;
import com.mtleung.demoretrofitmvc.view.CommentActivity;

import dagger.Subcomponent;

/**
 * Created by Marco on 01/01/2017.
 */

@ActivityScope
@Subcomponent(modules = CommentActivityModule.class)
public interface CommentActivityComponent {
    CommentActivity inject(CommentActivity activity);
}
