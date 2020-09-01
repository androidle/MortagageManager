package com.leevinapp.monitor.mine.ui

import androidx.lifecycle.ViewModel
import com.leevinapp.monitor.mine.repository.MineRepository
import javax.inject.Inject

class MineViewModel @Inject constructor(private val mineRepository: MineRepository) : ViewModel()
