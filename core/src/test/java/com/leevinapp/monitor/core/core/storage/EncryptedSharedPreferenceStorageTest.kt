package com.leevinapp.monitor.core.core.storage

import android.content.SharedPreferences
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class EncryptedSharedPreferenceStorageTest {

    @Test
    fun setString() {
        val sharedPreferencesEditor: SharedPreferences.Editor = mockk(relaxed = true) {
            every { putString(any(), any()) } returns this
        }

        val sharedPreferences: SharedPreferences = mockk {
            every { edit() } returns sharedPreferencesEditor
        }

        val encryptedSharedPreferenceStorage = EncryptedSharedPreferenceStorage(sharedPreferences)
        encryptedSharedPreferenceStorage.setString("test_key", "test_value")
        verify { sharedPreferences.edit() }
        verify { sharedPreferencesEditor.putString("test_key", "test_value") }
        verify { sharedPreferencesEditor.apply() }
    }

    @Test
    fun getString() {
    }

    @Test
    fun remove() {
    }

    @Test
    fun removeAll() {
    }
}
