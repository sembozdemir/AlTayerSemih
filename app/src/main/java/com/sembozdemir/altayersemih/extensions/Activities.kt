package com.sembozdemir.altayersemih.extensions

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction

val Fragment.fragmentTag: String
    get() = javaClass.simpleName

inline fun <reified T : Fragment> FragmentManager.findFragment(): T? = findFragmentByTag(T::class.java.simpleName) as? T
