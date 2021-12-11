package com.example.mynuntium.di.component

import androidx.fragment.app.Fragment
import com.example.mynuntium.MainActivity
import com.example.mynuntium.di.module.DatabaseModule
import com.example.mynuntium.di.module.NetworkModule
import com.example.mynuntium.ui.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class,DatabaseModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    fun injectHomepage(homepageFragment: HomepageFragment)

    fun injectBookmarks(bookmarksFragment: BookmarksFragment)

    fun injectRecommendAllFragment(recommendAllFragment: RecommendAllFragment)

    fun injectProfileFragment(profileFragment: ProfileFragment)


}