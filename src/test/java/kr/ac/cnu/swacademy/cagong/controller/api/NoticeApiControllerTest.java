package kr.ac.cnu.swacademy.cagong.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.ac.cnu.swacademy.cagong.dto.NoticeSaveRequestDto;
import kr.ac.cnu.swacademy.cagong.entity.Notice;
import kr.ac.cnu.swacademy.cagong.repository.NoticeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@DisplayName("View 컨트롤러 - 공지사항")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NoticeApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    void Posts_등록된다() throws Exception {
        //given
        String title = "title";
        String content = "content";

        NoticeSaveRequestDto requestDto = NoticeSaveRequestDto.builder()
                .title(title)
                .content(content)
                .build();
        String url = "http://localhost:" + port + "/api/v1/notice";

        //when
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
        List<Notice> postsList = noticeRepository.findAll();
        assertThat(postsList.get(0).getTitle()).isEqualTo(title);
        assertThat(postsList.get(0).getContent()).isEqualTo(content);
    }
}
