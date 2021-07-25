package br.com.raveline.myapplication.data.api

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EventApiServiceTest {
    private lateinit var service: EventsApiService
    private lateinit var mockServer: MockWebServer

    @Before
    fun setUp() {
        mockServer = MockWebServer()
        service = Retrofit
            .Builder()
            .baseUrl(mockServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EventsApiService::class.java)
    }

    @After
    fun tearDown() {
        mockServer.shutdown()
    }

    @Test
    fun getEvents_sentRequest_receivedExpected() {
        runBlocking {
            enqueueMockResponse("eventResponse.json")
            val responseBody = service.getEventsRequest()

            println("Response: ${responseBody.body()}")

            val request = mockServer.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/events")
        }
    }

    @Test
    fun getEventById_sentRequest_receivedExpected() {
        runBlocking {
            enqueueMockResponse("eventById.json")
            val responseBody = service.getEventById(1)

            println("Response: ${responseBody.body()}")

            val request = mockServer.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/events/1")

            val title = responseBody.body()?.title
            assertThat(title).contains("Feira de adoção")
        }
    }

    private fun enqueueMockResponse(
        fileName: String
    ) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()

        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))

        mockServer.enqueue(mockResponse)
    }
}