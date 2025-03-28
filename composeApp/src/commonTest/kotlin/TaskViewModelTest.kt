import dev.jordond.compass.Coordinates
import dev.jordond.compass.Location
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.ascarafia.onetreetasks.application.location.LocationProvider
import org.ascarafia.onetreetasks.domain.model.Task
import org.ascarafia.onetreetasks.domain.repository.TaskRepository
import org.ascarafia.onetreetasks.ui.task_list.TaskListViewModel
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class TaskViewModelTest {

//    @ExperimentalCoroutinesApi
//    @get:
//    var mainCoroutineRule = MainCoroutineRule()

    // Usamos el `TestDispatcher` para tests
    private val testDispatcher = StandardTestDispatcher()

    private val mockDatabaseRepository: TaskRepository = MockTaskRepository()

    private val mockLocationProvider: LocationProvider = object : LocationProvider {
        override suspend fun getCurrentLocation(): Location? {
            return Location(
                Coordinates(1.0, 2.0),
                timestampMillis = 1,
                speed = null,
                accuracy = 1.0,
                altitude = null,
                azimuth = null
            )
        }
    }

    private lateinit var taskViewModel: TaskListViewModel

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(testDispatcher) // Mockea Dispatchers.Main

        taskViewModel = TaskListViewModel(mockDatabaseRepository, mockLocationProvider)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain() // Resetea Dispatchers.Main después del test
    }

    @Test
    fun testAddNewTask() = runTest {
        val newTask = Task(
            id = "0",
            title = "newTask",
            body = "taskBody",
            isCompleted = false,
            latitude = null,
            longitude = null
        )
        taskViewModel.addTask(newTask)
        advanceUntilIdle()
        taskViewModel.getDatabaseTasks()

        advanceUntilIdle()
        val repositoryTask = taskViewModel.getTaskById("0")

        assertTrue { repositoryTask != null }
    }
}

class MockTaskRepository: TaskRepository {
    private val taskList = MutableStateFlow<List<Task>>(emptyList())

    override fun getTasks(): Flow<List<Task>> {
        return taskList
    }

    override suspend fun addTask(newTask: Task) {
        val mutableList = taskList.value.toMutableList()
        mutableList.add(newTask)
        taskList.value = mutableList
    }

    override suspend fun deleteTask(task: Task) {
        val mutableList = taskList.value.toMutableList()
        mutableList.remove(task)
        taskList.value = mutableList
    }

    override suspend fun getTaskById(taskId: String): Task? {
        return taskList.value.firstOrNull { it.id == taskId }
    }
}


//@ExperimentalCoroutinesApi
//class MainCoroutineRule(private val dispatcher: TestDispatcher = StandardTestDispatcher()) :
//    TestWatcher() {
//
//    override fun starting(description: Description?) {
//        super.starting(description)
//        Dispatchers.setMain(dispatcher)
//    }
//
//    override fun finished(description: Description?) {
//        super.finished(description)
//        Dispatchers.resetMain()
//    }
//}