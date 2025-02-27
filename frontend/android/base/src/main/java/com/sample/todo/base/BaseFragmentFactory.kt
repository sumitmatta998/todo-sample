package com.sample.todo.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.sample.todo.base.di.FragmentComponent
import javax.inject.Provider

class BaseFragmentFactory(
    private val providers: Map<Class<out Fragment>, @JvmSuppressWildcards Provider<FragmentComponent.Factory<*>>>
) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        val fragmentClass = classLoader.loadClass(className)
        return providers.entries.find { it.key.isAssignableFrom(fragmentClass) }
            ?.value?.get()?.create()?.fragment() ?: super.instantiate(classLoader, className)
    }
}
