package com.danielazheleva.blog.services.Impl

import com.danielazheleva.blog.model.request.Day
import com.danielazheleva.blog.model.request.TripDetailRequestModel
import com.danielazheleva.blog.repository.DayRepository
import com.danielazheleva.blog.repository.TripRepository
import com.danielazheleva.blog.services.PostsService
import com.danielazheleva.blog.entity.DayEntity
import com.danielazheleva.blog.entity.TripEntity
import org.assertj.core.api.Assertions
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
    private PostsService postsService;

    @Mock
    private DayRepository dayRepoMock;

    @Mock
    private TripRepository tripRepoMock;

    @Test
    @Ignore
    void testSavePost() {
    }
}
