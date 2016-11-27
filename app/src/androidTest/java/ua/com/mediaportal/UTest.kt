package ua.com.mediaportal

import org.junit.Before
import org.junit.Test
import ua.com.mediaportal.modules.RxTest


class UTest {

    lateinit var test: RxTest

    @Before
    fun initTest(){
        test=ua.com.mediaportal.modules.RxTest.instance
    }

    @Test
    fun testRxRetro() {
        test.getGithubResponce()
    }

}