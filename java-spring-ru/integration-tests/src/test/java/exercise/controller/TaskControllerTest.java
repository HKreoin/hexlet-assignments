package exercise.controller;

import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.description;
import static org.mockito.Mockito.timeout;

import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

// BEGIN
@SpringBootTest
@AutoConfigureMockMvc
// END
class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;


    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }


    // BEGIN
    @Test
    public void testPost() throws Exception {
        var task = createTask();
        taskRepository.save(task);
        
        var request = post("/tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(om.writeValueAsString(task));

        var result = mockMvc.perform(request)
            .andExpect(status().isCreated())
            .andReturn();
        var body = result.getResponse().getContentAsString();

        assertThatJson(body).and(
            a -> a.node("title").isEqualTo("Default task name"),
            a -> a.node("description").isEqualTo("Default task body for tests. More words for reading.")
        );
    }

    @Test
    public void testUpdate() throws Exception {
        var task = createTask();

        taskRepository.save(task);

        var data = new HashMap<>();
        var title = "New Task";
        var description = "Some todo";
        data.put("title", title);
        data.put("description", description);

        var request = put("/tasks/" + task.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(om.writeValueAsString(data));

        var result = mockMvc.perform(request)
            .andExpect(status().isOk())
            .andReturn();
            
        var body = result.getResponse().getContentAsString();

        assertThatJson(body).and(
            a -> a.node("title").isEqualTo(title),
            a -> a.node("description").isEqualTo(description)
        );
    }

    public Task createTask() {
        return Instancio.of(Task.class)
            .ignore(Select.field(Task::getId))
            .supply(Select.field(Task::getTitle), () -> "Default task name")
            .supply(Select.field(Task::getDescription), () -> "Default task body for tests. More words for reading.")
            .create();
    }
    // END
}
