package io.github.devriesl.raptormark.data

import io.github.devriesl.raptormark.Constants.LATENCY_TEST_ID
import io.github.devriesl.raptormark.R
import io.github.devriesl.raptormark.di.StringProvider

class LatencyTestRepo(
    stringProvider: StringProvider,
    settingDataSource: SettingDataSource,
    historyDatabase: HistoryDatabase
) : TestRepository(stringProvider, settingDataSource, historyDatabase) {
    override val testFileName = LATENCY_TEST_ID

    override fun getTestName(): String {
        return stringProvider.getString(R.string.latency_test_title)
    }

    override fun runTest() {
    }

    override fun onTestResult(vararg results: Int) {
    }
}