package com.example.dummyproducts.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.dummyproducts.getOrAwaitValueTest
import com.example.dummyproducts.models.ProductsResponseItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest   //this annotation defines that these are the unit tests
class ProductsDaoTest {

    @get:Rule
    var instantTaskExecutorRule=InstantTaskExecutorRule()

    private lateinit var database: ProductsDatabase
    private lateinit var dao: ProductsDao

    @Before  // executes before every test case in this class
    fun setup() {

        //inMemoryDatabase defines that the data will persist in RAM only
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ProductsDatabase::class.java
        ).allowMainThreadQueries()
            .build()
        dao = database.getProductsDao()

    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun upsert() = runBlockingTest {
        val productItem = ProductsResponseItem(
            1, "category", "description",
            "url", 10.0, "title"
        )
        dao.upsert(productItem)

        //Best way to handle live data for testing
        val allProducts = dao.getProductsFromCart().getOrAwaitValueTest()
        assert(allProducts.contains(productItem))
    }




}