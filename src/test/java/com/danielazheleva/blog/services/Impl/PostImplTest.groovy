package com.danielazheleva.blog.services.Impl


import com.danielazheleva.blog.repository.DayRepository
import com.danielazheleva.blog.repository.TripRepository
import com.danielazheleva.blog.services.TripService
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class PostImplTest {

    @Autowired
    private TripService postsService;

    @Mock
    private DayRepository dayRepoMock;

    @Mock
    private TripRepository tripRepoMock;

    @Test
    @Ignore
    void testSavePost() {
    }
}
