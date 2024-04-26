package com.company.macrobenchmark

import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.StartupTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * This is an example startup benchmark.
 *
 * It navigates to the device's home screen, and launches the default activity.
 *
 * Before running this benchmark:
 * 1) switch your app's active build variant in the Studio (affects Studio runs only)
 * 2) add `<profileable android:shell="true" />` to your app's manifest, within the `<application>` tag
 *
 * Run this benchmark from Studio to see startup measurements, and captured system traces
 * for investigating your app's performance.
 */
@RunWith(AndroidJUnit4::class)
class ExampleStartupBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun startup() = benchmarkRule.measureRepeated(
        packageName = "com.company.dolshop",
        metrics = listOf(StartupTimingMetric()),
        iterations = 5,
        startupMode = StartupMode.COLD
    ) {
        pressHome()
        startActivityAndWait()
    }

    @Test
    fun measureProductItemScreenRendering() = benchmarkRule.measureRepeated(
        packageName = "com.company.dolshop",
        metrics = listOf(FrameTimingMetric()), // 프레임 시간 측정을 위한 메트릭
        iterations = 10,
        startupMode = StartupMode.COLD
    ) {
        // 데이터 로딩 및 컴포저블 렌더링 시뮬레이션
//        benchmarkRule.measureRepeated  {
            // 데이터 로딩 시뮬레이션
            simulateDataLoading()
//        }

        // Composable 렌더링
        startActivityAndWait { intent ->
            intent.putExtra("EXTRA_COMPOSABLE_NAME", "ProductItemScreen")
        }

        // 데이터가 로드되고 나면, 실제 렌더링 타이밍 측정
        pressHome()
        startActivityAndWait()
    }

    private fun simulateDataLoading() {
        // 서버 또는 로컬 데이터베이스에서 데이터를 로드하는 로직 구현
        Thread.sleep(1000) // 1초간 데이터 로딩 지연을 가정
    }


}