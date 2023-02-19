package io.github.devriesl.raptormark.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.json.JSONObject
import java.io.IOException

abstract class BenchmarkTest(
    val testCase: TestCases,
    val settingSharedPrefs: SettingSharedPrefs
) {
    private val nativeListener = object : NativeListener {
        override fun onTestResult(result: String) {
            nativeResult = result
            mutableTestResult.value = parseTestResult(result)
        }
    }

    private val mutableTestResult = MutableStateFlow(TestResult())
    var nativeResult: String? = null

    val testResult: StateFlow<TestResult>
        get() = mutableTestResult

    abstract fun nativeTest(jsonCommand: String): Int

    abstract fun testOptionsBuilder(): String

    open fun runTest(): String? {
        nativeResult = null

        NativeHandler.registerListener(nativeListener)
        val options = testOptionsBuilder()
        val ret = nativeTest(options)
        NativeHandler.unregisterListener(nativeListener)

        if (ret != 0) {
            throw IOException("$ret")
        }

        return nativeResult
    }

    fun createOption(name: String, value: String): JSONObject {
        val jsonOption = JSONObject()
        jsonOption.put("name", name)
        jsonOption.put("value", value)
        return jsonOption
    }
}
