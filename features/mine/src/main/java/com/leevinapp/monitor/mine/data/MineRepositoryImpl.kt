package com.leevinapp.monitor.mine.data

import com.leevinapp.monitor.mine.data.api.MineService
import com.leevinapp.monitor.mine.domain.MineRepository

class MineRepositoryImpl(private val mineService: MineService) : MineRepository
