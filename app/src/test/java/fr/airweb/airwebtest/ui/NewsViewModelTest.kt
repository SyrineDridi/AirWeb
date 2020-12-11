package fr.airweb.airwebtest.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import fr.airweb.airwebtest.domain.models.News
import fr.airweb.airwebtest.domain.models.NewsDetails
import fr.airweb.airwebtest.domain.models.NewsDetailsTypeEnum
import fr.airweb.airwebtest.domain.usescases.FetchPsgNewsByTypeFromLocal
import fr.airweb.airwebtest.domain.usescases.FetchPsgNewsFromLocal
import fr.airweb.airwebtest.domain.usescases.FetchPsgNewsFromRemote
import fr.airweb.airwebtest.domain.usescases.SavePsgNewsInLocal
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import org.mockito.Answers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NewsViewModelTest : AutoCloseKoinTest() {

    private val viewModel: NewsViewModel by inject()

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private lateinit var fetchPsgNewsByTypeFromLocal: FetchPsgNewsByTypeFromLocal

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private lateinit var fetchPsgNewsFromLocal: FetchPsgNewsFromLocal

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private lateinit var fetchPsgNews: FetchPsgNewsFromRemote

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private lateinit var savePsgNewsInLocal: SavePsgNewsInLocal

    private val modules = module {
        viewModel {
            NewsViewModel(
                fetchPsgNews,
                fetchPsgNewsByTypeFromLocal,
                savePsgNewsInLocal,
                fetchPsgNewsFromLocal
            )
        }
    }

    @get:Rule
    val rule = InstantTaskExecutorRule()
    private val listNews = News(
        listOf(
            NewsDetails(
                1, NewsDetailsTypeEnum.NEWS, "20-10-2018", "news1", "picture1",
                "content1", "20/10/2018"
            ),
            NewsDetails(
                2, NewsDetailsTypeEnum.ACTUALITE, "20-05_2020", "actualite2", "picture2",
                "content2", "20/05/2020"
            ),
            NewsDetails(
                3, NewsDetailsTypeEnum.HOT, "20-05_2019", "hot3", "picture3",
                "content3", "20/05/2019"
            )
        )
    )

    private val listNewsSortedDate = listOf(
        NewsDetails(
            3, NewsDetailsTypeEnum.HOT, "20-05_2019", "hot3", "picture3",
            "content3", "20/05/2019"
        ),
        NewsDetails(
            2, NewsDetailsTypeEnum.ACTUALITE, "20-05_2020", "actualite2", "picture2",
            "content2", "20/05/2020"
        ),
        NewsDetails(
            1, NewsDetailsTypeEnum.NEWS, "20-10-2018", "news1", "picture1",
            "content1", "20/10/2018"
        )
    )

    private val listNewsSortedByTitle = listOf(
        NewsDetails(
            2, NewsDetailsTypeEnum.ACTUALITE, "20-05_2020", "actualite2", "picture2",
            "content2", "20/05/2020"
        ),
        NewsDetails(
            3, NewsDetailsTypeEnum.HOT, "20-05_2019", "hot3", "picture3",
            "content3", "20/05/2019"
        ),
        NewsDetails(
            1, NewsDetailsTypeEnum.NEWS, "20-10-2018", "news1", "picture1",
            "content1", "20/10/2018"
        )
    )

    private val listSorteByActualite = listOf(
        NewsDetails(
            2, NewsDetailsTypeEnum.ACTUALITE, "20-05_2020", "actualite2", "picture2",
            "content2", "20/05/2020"
        )
    )

    @Before
    fun setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        Mockito.`when`(fetchPsgNews.invoke()).thenReturn(Observable.error(Throwable()))
        Mockito.`when`(fetchPsgNewsFromLocal.invoke()).thenReturn(Observable.just(listNews.news))
        startKoin {
            modules(modules)
        }
    }

    @Test
    fun getListNewFromRemote() {
        assert(viewModel.uiNewsEvent.value == UiNewsEvent.DisplayAllNews(listNews.news))
    }

    @Test
    fun fetchAllNewsRemotes() {
        println(viewModel.uiNewsEvent.value)
        Mockito.`when`(fetchPsgNewsByTypeFromLocal.invoke(NewsDetailsTypeEnum.ACTUALITE))
            .thenReturn(Observable.just(listSorteByActualite))
        viewModel.fetchByType(NewsDetailsTypeEnum.ACTUALITE)
        assert(viewModel.uiNewsEvent.value == UiNewsEvent.DisplayNewsByType(listSorteByActualite))
    }

    @Test
    fun sortListNewsByDate() {
        viewModel.sortListByDate()
        assert(viewModel.uiNewsEvent.value == UiNewsEvent.DisplayNewsSortedByDate(listNewsSortedDate))
    }

    @Test
    fun sortListByTitle() {
        viewModel.sortListByTitle()
        assert(viewModel.uiNewsEvent.value == UiNewsEvent.DisplayNewsSortedByTitle(listNewsSortedByTitle))
    }
}