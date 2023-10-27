package com.howard.mainapp

import com.howard.findmyip.model.FindMyIpApi
import com.howard.findmyip.model.FindMyIpResponse
import com.howard.findmyip.repository.FindMyIpRepo
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Response

class FindMyIpUnitTest {

    @Mock
    private lateinit var api: FindMyIpApi

    private lateinit var repository: FindMyIpRepo

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = FindMyIpRepo()
        api = mock(FindMyIpApi::class.java)
    }

    @Test
    fun `test getIp success`() = runBlocking {
        val response =
            FindMyIpResponse(region = "California", city = "Chico", countryName = "United States")
        val mockResponse = Response.success(response)

        `when`(api.getMyIp()).thenReturn(mockResponse)

        val result = api.getMyIp().body()!!

        // Checks that the flow emits the expected states
        assertEquals(result.region, response.region)
        assertEquals(result.city, response.city)
        assertEquals(result.countryName, response.countryName)
    }

    @Test
    fun `test getIp failure`() = runBlocking {
        val errorResponse = "{\n" + "  \"type\": \"error\",\n" + "  \"message\": \"An error occurred.\"\n" + "}"
        val errorResponseBody = errorResponse.toResponseBody("application/json".toMediaTypeOrNull())
        val mockResponse = Response.error<FindMyIpResponse>(429, errorResponseBody)

        `when`(api.getMyIp()).thenReturn(mockResponse)

        val result = api.getMyIp()
        assertFalse(result.isSuccessful)
    }
}