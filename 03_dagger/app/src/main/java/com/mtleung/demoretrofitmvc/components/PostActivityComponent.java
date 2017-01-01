package com.mtleung.demoretrofitmvc.components;

import com.mtleung.demoretrofitmvc.adapter.PostAdapter;
import com.mtleung.demoretrofitmvc.interfaces.ActivityScope;
import com.mtleung.demoretrofitmvc.modules.PostActivityModule;
import com.mtleung.demoretrofitmvc.view.PostActivity;

import javax.inject.Named;

import dagger.Subcomponent;

/**
 * Created by Marco on 01/01/2017.
 */

@ActivityScope
@Subcomponent(modules = { PostActivityModule.class })
public interface PostActivityComponent {

    PostActivity inject(PostActivity activity);
    PostAdapter postAdapter();
}
