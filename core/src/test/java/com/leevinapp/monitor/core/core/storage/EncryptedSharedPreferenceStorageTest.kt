package com.leevinapp.monitor.core.core.storage

import android.content.SharedPreferences
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class EncryptedSharedPreferenceStorageTest {

    @MockK
    private lateinit var sharedPreferences: SharedPreferences

    @MockK
    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor

    private lateinit var encryptedSharedPreferenceStorage: EncryptedSharedPreferenceStorage

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        every { sharedPreferences.edit() } returns sharedPreferencesEditor
        every { sharedPreferencesEditor.putString(any(), any()) } returns sharedPreferencesEditor
        every { sharedPreferencesEditor.remove(any()) } returns sharedPreferencesEditor
        every { sharedPreferencesEditor.clear() } returns sharedPreferencesEditor

        encryptedSharedPreferenceStorage = EncryptedSharedPreferenceStorage(sharedPreferences)
    }

    @Test
    fun setString() {
        encryptedSharedPreferenceStorage.setString("test_key", "test_value")
        verify(exactly = 1) { sharedPreferences.edit() }
        verify(exactly = 1) { sharedPreferencesEditor.putString("test_key", "test_value") }
        verify(exactly = 1) { sharedPreferencesEditor.apply() }
    }

    @Test
    fun getString() {
        encryptedSharedPreferenceStorage.getString("test_key", "")
        verify(exactly = 1) { sharedPreferences.getString("test_key", "") }
    }

    @Test
    fun remove() {
        encryptedSharedPreferenceStorage.remove("test_key")
        verify { sharedPreferences.edit() }
        verify { sharedPreferencesEditor.remove("test_key") }
        verify { sharedPreferencesEditor.apply() }
    }

    @Test
    fun removeAll() {
        encryptedSharedPreferenceStorage.removeAll()
        verify { sharedPreferences.edit() }
        verify { sharedPreferencesEditor.clear() }
        verify { sharedPreferencesEditor.apply() }
    }
}
