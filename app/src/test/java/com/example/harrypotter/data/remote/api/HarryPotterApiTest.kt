package com.example.harrypotter.data.remote.api

import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HarryPotterApiTest {

    lateinit var mockWebServer: MockWebServer
    lateinit var harryPotterApi: HarryPotterApi

    @Before
    fun setup(){
        mockWebServer = MockWebServer()
        harryPotterApi = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HarryPotterApi::class.java)
    }

    @Test
    fun testGetAllCharacters() = runBlocking{
        val mockResponse = MockResponse()
        mockResponse.setBody("[]")
        mockWebServer.enqueue(mockResponse)

        val response = harryPotterApi.getAllCharacters()
        mockWebServer.takeRequest()

        Assert.assertEquals(true, response.body()!!.isEmpty())
    }

    @Test
    fun testGetAllCharacters_returnCharacters() = runBlocking{
        val mockResponse = MockResponse()
        val content = Helper.readFileResource("/response.json")
        mockResponse.setResponseCode(200)
        mockResponse.setBody(content)
        mockWebServer.enqueue(mockResponse)

        val response = harryPotterApi.getAllCharacters()
        mockWebServer.takeRequest()

        Assert.assertEquals(false, response.body()!!.isEmpty())
        Assert.assertEquals(2, response.body()!!.size)
    }

    @Test
    fun testGetAllCharacters_returnError() = runBlocking{
        val mockResponse = MockResponse()
        mockResponse.setResponseCode(404)
        mockResponse.setBody("Something went wrong")
        mockWebServer.enqueue(mockResponse)

        val response = harryPotterApi.getAllCharacters()
        mockWebServer.takeRequest()

        Assert.assertEquals(false, response.isSuccessful)
        Assert.assertEquals(404, response.code())
    }

    @Test
    fun testGetAllCharactersByHouse() = runBlocking{
        val mockResponse = MockResponse()
        mockResponse.setBody("[]")
        mockWebServer.enqueue(mockResponse)

        val response = harryPotterApi.getCharacterByHouseName("house")
        mockWebServer.takeRequest()

        Assert.assertEquals(true, response.body()!!.isEmpty())
    }

    @Test
    fun testGetAllCharactersByHouse_returnCharacters() = runBlocking{
        val mockResponse = MockResponse()
        val content = Helper.readFileResource("/response.json")
        mockResponse.setResponseCode(200)
        mockResponse.setBody(content)
        mockWebServer.enqueue(mockResponse)

        val response = harryPotterApi.getCharacterByHouseName("house")
        mockWebServer.takeRequest()

        Assert.assertEquals(false, response.body()!!.isEmpty())
        Assert.assertEquals(2, response.body()!!.size)
    }

    @Test
    fun testGetAllCharactersByHouse_returnError() = runBlocking{
        val mockResponse = MockResponse()
        mockResponse.setResponseCode(404)
        mockResponse.setBody("Something went wrong")
        mockWebServer.enqueue(mockResponse)

        val response = harryPotterApi.getAllCharacters()
        mockWebServer.takeRequest()

        Assert.assertEquals(false, response.isSuccessful)
        Assert.assertEquals(404, response.code())
    }

    @After
    fun tearDown(){
        mockWebServer.shutdown()
    }
}